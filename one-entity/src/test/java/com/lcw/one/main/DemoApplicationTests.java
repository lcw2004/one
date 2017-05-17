package com.lcw.one.main;

import com.lcw.one.sys.entity.SysMenuEO;
import com.lcw.one.util.persistence.entity.TreeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(DemoApplicationTests.class.getSimpleName());
        System.out.println(UUID.randomUUID().toString());
        System.out.println(new SysMenuEO() instanceof TreeEntity);
        System.out.println(SysMenuEO.class.isAssignableFrom(TreeEntity.class));
    }

}
