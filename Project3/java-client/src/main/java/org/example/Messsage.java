package org.example;

import java.io.Serializable;

public class Messsage implements Serializable {
    private int number;
    private String content;

    public Messsage() {
        this.number = 5;
        this.content = "some random content";
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}