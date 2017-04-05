package com.lcw.one;

import com.lcw.one.sys.web.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OneApplicationTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LoginController loginController;


    @Test
    public void contextLoads() throws Exception {
        assertThat(loginController).isNotNull();
    }



}



