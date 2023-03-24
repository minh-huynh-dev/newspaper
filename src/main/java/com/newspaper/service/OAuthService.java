package com.newspaper.service;

import com.newspaper.enums.Reddit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OAuthService {

    Logger logger = LoggerFactory.getLogger(OAuthService.class);

    //TODO: move to database, require log in
    private final String secretKey = "EN4_-5l6M531uYtGodvDeHTbNIIq_w";
    private final String CLIENT_KEY = "scUFYsYPabPxrZgqm0seWw";
    private final String RESPONSE_TYPE = "code";
    private final String REDIRECT_URL = "https://localhost:8080";
    private final String DURATION = "permanent";
    private final String SCOPE = "read";
    private RestTemplate client;

    public OAuthService(RestTemplate client){
        this.client = client;
    }

    /**
     * https://www.reddit.com/api/v1/authorize?client_id=CLIENT_ID&response_type=TYPE&
     *     state=RANDOM_STRING&redirect_uri=URI&duration=DURATION&scope=SCOPE_STRING
     * @return
     */

    /**
     * https://www.reddit.com/api/v1/authorize?client_id=scUFYsYPabPxrZgqm0seWw&response_type=code&state=%EF%BF%BD%09%EF%BF%BDE%EF%BF%BD%EF%BF%BD9&redirect_uri=https://localhost:8080&duration=permanent&scope=read
     * @return
     */
    public String getToken(){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, "android:com.info.newspaper:v1.0.0 (by /u/redminhdit)");
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(Reddit.BASE + Reddit.AUTHORIZE)
                .queryParam("client_id", CLIENT_KEY)
                .queryParam("response_type", RESPONSE_TYPE)
                .queryParam("state", createRandomCode())
                .queryParam("redirect_uri", REDIRECT_URL)
                .queryParam("scope", SCOPE)
                .queryParam("duration", DURATION)
                .encode()
                .toUriString();

        logger.info(urlTemplate);

        client.exchange(urlTemplate, HttpMethod.GET, entity, String.class);

        ResponseEntity<String> response = client.getForEntity(urlTemplate, String.class);
        logger.info(response.getBody());

        return response.getBody();
    }

    private String createRandomCode(){
        byte[] array = new byte[3];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }

}
