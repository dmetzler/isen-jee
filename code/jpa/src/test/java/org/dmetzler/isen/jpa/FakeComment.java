package org.dmetzler.isen.jpa;

import java.util.Date;

import org.joda.time.DateTime;

public class FakeComment implements Comment {

    private String author;
    private String content;
    private Date date;

    public FakeComment(String author, String content) {
        this.author = author;
        this.content = content;
        this.date = new DateTime().toDate();
    }

    @Override
    public BlogEntry getBlog() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DateTime getDate() {
        return new DateTime(date);
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

}
