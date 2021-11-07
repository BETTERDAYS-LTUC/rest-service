package com.example.restservice.Greeting;

import com.example.restservice.Model.Pledge;

import java.util.ArrayList;
import java.util.List;

public class Greeting {

    private final long id;
    private  String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}