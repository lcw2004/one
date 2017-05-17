package com.lcw.one.codegen;

import com.lcw.one.codegen.dao.TableMetaInfoDao;
import com.lcw.one.util.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lcw.one.codegen")
public class CodeGenApplication {

    private static final Logger logger = LoggerFactory.getLogger(CodeGenApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CodeGenApplication.class, args);
        SpringContextHolder.setApplicationContext(applicationContext);
        logger.info("Registry ApplicationContext");

        TableMetaInfoDao tableMetaInfoDao = applicationContext.getBean(TableMetaInfoDao.class);
        tableMetaInfoDao.getInfo();
        tableMetaInfoDao.getMeta();
    }

}
