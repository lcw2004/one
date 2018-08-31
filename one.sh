#!/bin/bash

# set java environment
# JAVA_HOME=/usr/java/jdk1.8
# export JRE_HOME=/usr/java/jdk1.8/jre
# export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
# export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH

# set maven
export M2_HOME=/opt/apache-maven-3.5.2
export PATH=$PATH:$M2_HOME/bin

BASE_PATH="/home/one/one-extend"
ONE_EXTEND_PATH=$BASE_PATH/one-extend
ONE_MAIN_PATH=$ONE_EXTEND_PATH/one-main
ONE_CMS_FRONT_PATH=$ONE_EXTEND_PATH/one-cms-front
ONE_UI_PATH=$ONE_EXTEND_PATH/one-ui
ONE_MAIN_STATIC_PATH=$ONE_MAIN_PATH/src/main/resources/static
ONE_JAR_PATH=$BASE_PATH/jar
ONE_BAK_PATH=$ONE_JAR_PATH/bak
ONE_MAIN_JAR=$ONE_JAR_PATH/one-main-0.0.1-SNAPSHOT.jar
ONE_CMS_FRONT_JAR=$ONE_JAR_PATH/one-cms-front-0.0.1-SNAPSHOT.war
USER_EMAIL="lcw2004@163.com"
USER_NAME="lcw2004"
GIT_PASSWORD="0e1d329a06"

function print () {
    echo ""
    echo "***********************************************"
    echo "******** $1"
    echo "***********************************************"
}

# 获取最新代码
function pull_code () {
    print "1. pull code"
    cd  $ONE_EXTEND_PATH;
    git config core.filemode false
    git pull https://$USER_NAME:$GIT_PASSWORD@git.oschina.net/lcw2004/one-extend.git master;
}

# 编译前端工程
function build_front () {
    print "2. build front"
    cd $ONE_UI_PATH;
    rm -rf $ONE_UI_PATH/dist/*
    npm install;
    npm run dll;
    npm run build;

    # 判断是否编译成功，否则退出程序
    if [ ! -d "$ONE_UI_PATH/dist/static" ]; then
        print "build front fail"
        exit 1
    fi

    # copy front file
    cd $BASE_PATH;
    rm -rf $ONE_MAIN_STATIC_PATH;
    mkdir -p $ONE_MAIN_STATIC_PATH;
    cp -rf $ONE_UI_PATH/dist/* $ONE_MAIN_STATIC_PATH/;
}

# 编译后端工程
function build_maven () {
    print "3. build spring boot"
    cd $ONE_EXTEND_PATH;
    mvn clean package -Dmaven.test.skip=true;
}

# 备份数据
function backup (){
    print "4. backup"
    cp "$ONE_JAR_PATH/one-main-0.0.1-SNAPSHOT.jar" "$ONE_BAK_PATH/one-main-0.0.1-SNAPSHOT.jar.`date +%Y-%m-%d-%H-%M-%S`"
    cp "$ONE_JAR_PATH/one-cms-front-0.0.1-SNAPSHOT.war" "$ONE_BAK_PATH/one-cms-front-0.0.1-SNAPSHOT.war.`date +%Y-%m-%d-%H-%M-%S`"
}

# 拷贝Jar包
function copy_jar () {
    print "5. copy jar file"
    cp "$ONE_MAIN_PATH/target/one-main-0.0.1-SNAPSHOT.jar" "$ONE_MAIN_JAR"
    cp "$ONE_CMS_FRONT_PATH/target/one-cms-front-0.0.1-SNAPSHOT.war" "$ONE_CMS_FRONT_JAR"
}

# 杀死进程
function kill_progress () {
	echo "Stop $1"
    boot_id=`ps -ef | grep java | grep $1 | grep -v grep | awk '{print $2}'`
    count=`ps -ef | grep java | grep $1 | grep -v grep | wc -l`

    if [ $count != 0 ];then
        echo "$1 process pid is:$boot_id ."
        kill $boot_id

        # 如果没干掉进程，再干一遍
        sleep 2
        boot_id=`ps -ef | grep java | grep $1 | grep -v grep | awk '{print $2}'`
        count=`ps -ef | grep java | grep $1 | grep -v grep | wc -l`
        if [ $count != 0 ];then
            echo "$1 process pid is:$boot_id ."
            kill -9 $boot_id
        fi
    fi
}

# 杀死全部进程
function kill_all () {
    print "6. shut down"
    kill_progress "$ONE_MAIN_JAR"
    kill_progress "$ONE_CMS_FRONT_JAR"
}

# 启动程序
function start () {
    print "7. start one-extend"
    nohup /usr/bin/java -jar -Xms100m -Xmx500m $ONE_MAIN_JAR --spring.config.location=$BASE_PATH/one_main.yml --logging.path=$BASE_PATH/log/one-main > /dev/null 2>&1 &
    nohup /usr/bin/java -jar -Xms100m -Xmx100m $ONE_CMS_FRONT_JAR --spring.config.location=$BASE_PATH/one_cms_front.yml --logging.path=$BASE_PATH/log/one-cms-front > /dev/null 2>&1 &

    sleep 3
    status
}

# 检查状态
function check_status () {
    count=`ps -ef | grep java | grep $1 | grep -v grep|wc -l`
    if [ $count != 0 ];then
        boot_id=`ps -ef | grep java | grep $1 | grep -v grep | awk '{print $2}'`
        echo "$1 is running... process pid is:$boot_id "
    else
        echo "$1 is not running..."
    fi
}

# 检查两个工程的状态
function status(){
    print "check status"
    check_status "$ONE_MAIN_JAR"
    check_status "$ONE_CMS_FRONT_JAR"
}

# 更新代码并编译运行
function reload () {
    pull_code
    build_front
    build_maven
    backup
    copy_jar
    kill_all
    start
    print "8. deploy end"
}

# 停止
function stop () {
    kill_all
}

# 重启
function restart () {
    kill_all
    start
}

# 打印帮助手册
function print_usage () {
    echo -e "\033[0;31m Usage: \033[0m  \033[0;34m sh $0 {help|start|stop|restart|status|reload} \033[0m\033[0;31m Example: \033[0m\033[0;33m sh $0 start \033[0m"
}

case $1 in
    reload)
    reload;;
    start)
    start;;
    stop)
    stop;;
    restart)
    restart;;
    status)
    status;;
    help)
    print_usage;;
    *)
esac