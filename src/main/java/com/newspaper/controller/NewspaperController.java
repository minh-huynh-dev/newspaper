package com.newspaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.newspaper.service.RedditService;

@RestController
public class NewspaperController {

    private final RedditService reddit;

    public NewspaperController(RedditService reddit){
        this.reddit = reddit;
    }

    @GetMapping("/test")
    public void test(){
        reddit.test();
    }

}
