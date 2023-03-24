package com.newspaper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedditService {

    private final OAuthService oAuth;
    public RedditService(OAuthService oAuth) {
        this.oAuth = oAuth;
    }

    public void test(){
        oAuth.getToken();
    }






}
