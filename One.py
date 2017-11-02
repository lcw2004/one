# -*- coding: UTF-8 -*-
# !/usr/bin/python

import os
import subprocess
import shutil
import time

BASE_PATH = "/root/Files/WorkSpace/one"
# BASE_PATH = "E:\WorkSpace\OneWorkSpace\one-extend"
GIT_USER_NAME = "test"
GIT_PASSWORD = "test"

ONE_UI = BASE_PATH + "/one-ui"
ONE_MAIN = BASE_PATH + "/one-main"
ONE_CMS_FRONT = BASE_PATH + "/one-cms-front"
ONE_MAIN_STATIC_PATH = ONE_MAIN + "/src/main/resources/static"
ONE_UI_DIST_PATH = ONE_UI + "/dist"

CMD_START_ONE_MAIN = "nohup java -jar {}/one-main/target/one-main-0.0.1-SNAPSHOT.jar --server.port=8090 --file.path=/root/Files/temp --logging.path=/var/log/one &".format(
    BASE_PATH)

def exe_cmd(cmd):
    start_time = time.time()
    print('*********************************************')
    print('执行命令：{}'.format(cmd))
    p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    return_code = p.poll()
    while return_code is None:
        line = p.stdout.readline()
        return_code = p.poll()
        line = line.strip()
        if line != '':
            print(line)
    end_time = time.time()
    if return_code == 0:
        print('执行完毕，耗时：{}秒'.format(int((end_time - start_time))))
    else:
        print('ERROR: 命令{}执行失败'.format(cmd))
    print('*********************************************')


def del_dir(path):
    print('Delete Dir:' + path)
    if os.path.exists(path):
        shutil.rmtree(path)


def make_dir(path):
    print('Make Dir:' + path)
    if not os.path.exists(path):
        os.makedirs(path)


def copy_dir(src, dst):
    print('Copy Dir: {} ==> {}'.format(src, dst))
    shutil.copytree(src, dst)


def print_line(src):
    print("====================================================")
    print(src)
    print("====================================================")


def kill_progress_by(arg):
    exe_cmd("ps -ef | grep \'{}\' | grep -v grep | awk \'{{print $2}}\' | xargs kill".format(arg))


def kill_progress():
    kill_progress_by("one/one-main/target/one-main-0.0.1-SNAPSHOT.jar")


def git_pull_origin():
    print_line("Step 1, Git Pull Origin, Start")

    # Git pull
    os.chdir(BASE_PATH)
    exe_cmd('git pull https://github.com/lcw2004/one.git master')
    print_line("Step 1, Git Pull Origin, End")


def build_one_ui():
    print_line("Step 2, Build One-ui, Start")

    # 编译one-ui
    os.chdir(ONE_UI)
    exe_cmd('npm install')
    exe_cmd('npm run dll')
    exe_cmd('npm run build')

    # 拷贝编译出的文件
    del_dir(ONE_MAIN_STATIC_PATH)
    copy_dir(ONE_UI_DIST_PATH, ONE_MAIN_STATIC_PATH)
    print_line("Step 2, Build One-ui, End")


def build_one_main():
    print_line("Step 3, Build One-main, Start")
    os.chdir(BASE_PATH)
    exe_cmd("mvn clean install -Dmaven.test.skip=true")
    print_line("Step 3, Build One-main, End")


def start_up():
    os.chdir(ONE_MAIN)
    exe_cmd(CMD_START_ONE_MAIN)


if __name__ == '__main__':
    git_pull_origin()
    kill_progress()
    build_one_ui()
    build_one_main()
    start_up()
