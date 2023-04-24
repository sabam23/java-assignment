package com.example.assigment.models;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String header;
    private String content;
    private String author;
    private java.sql.Timestamp date;


    public Post(int id, String header, String content, String author, java.sql.Timestamp date) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }
    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public java.sql.Timestamp date() {
        return date;
    }
}
