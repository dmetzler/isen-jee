package org.dmetzler.isen.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

@Entity(name = "comment")
public class CommentImpl implements Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    Long id;
    private String author;
    private String content;
    private Date date;

    @ManyToOne
    private BlogEntryImpl blog;

    public CommentImpl() {

    }

    public CommentImpl(BlogEntryImpl blog, String author, String content) {
        this.author = author;
        this.content = content;
        this.date = new DateTime().toDate();
        this.blog = blog;
    }

    @Override
    public BlogEntry getBlog() {
        return this.blog;
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
    public boolean equals(Object obj) {
        if(!(obj instanceof Comment)) {
            return false;
        }

        return ((Comment) obj).getId() == getId();

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public Long getId() {
        return id;
    }

}
