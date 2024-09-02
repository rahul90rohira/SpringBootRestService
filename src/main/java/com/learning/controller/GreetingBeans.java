package com.learning.controller;

import org.springframework.stereotype.Component;

@Component
public class GreetingBeans {

    private long id ;
    private String content ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
