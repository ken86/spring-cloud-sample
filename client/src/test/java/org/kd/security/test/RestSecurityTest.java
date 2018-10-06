package org.kd.security.test;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestSecurityTest {

    public static final String REST_SERVICE_URI = "http://localhost:9001/";

    /*
     * Add HTTP Authorization header, using Basic-Authentication to send user-credentials.
     */
    private HttpHeaders getHeaders(){
        String plainCredentials="test:test";
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }


    public void listAllUsers(){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<>(getHeaders());
        ResponseEntity<String> response = restTemplate.exchange(REST_SERVICE_URI+"hello", HttpMethod.GET, request, String.class);
        String responseStr = response.getBody();
        System.out.println(responseStr);
    }
}
