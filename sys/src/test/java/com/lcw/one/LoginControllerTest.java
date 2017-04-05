package com.lcw.one;

import com.lcw.one.sys.advice.ResponseMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @LocalServerPort
    private int port;

    public String getBaseUrl() {
        return TestUtils.getBaseUrl(port);
    }


    /**
     * http://stackoverflow.com/questions/35998790/resttemplate-how-to-send-url-parameters-and-query-parameters-together
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println(TestUtils.REST_TEMPLATE);

        // url
        String url = getBaseUrl() + "login";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("username", "admin")
                .queryParam("password", "admin");

        ResponseEntity<ResponseMessage> responseEntity = TestUtils.REST_TEMPLATE.postForEntity(builder.build().toUri(), TestUtils.jsonEntity(), ResponseMessage.class);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo("0");
    }

    @Test
    public void testGetUserInfo() throws Exception {
        System.out.println(TestUtils.REST_TEMPLATE);

        String url = getBaseUrl() + "userInfo";

        ResponseEntity<ResponseMessage> responseEntity = TestUtils.REST_TEMPLATE.exchange(url, HttpMethod.GET, TestUtils.jsonEntity(), ResponseMessage.class);

        System.out.println(responseEntity);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo("0");
    }
}
