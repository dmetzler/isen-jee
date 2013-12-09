package org.dmetzler.isen.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.joda.time.DateTime;

@Entity(name="Post")
@NamedQueries({ @NamedQuery(name = BlogEntryImpl.ALL_ENTRIES,
query = "FROM Post ORDER BY created DESC") })
public class BlogEntryImpl implements BlogEntry {

    public static final String ALL_ENTRIES = "ALL_ENTRIES";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private String author;
    private String content;
    private String title;
    private Date created;

    public BlogEntryImpl(String title) {
        this.title = title;
        this.created = new DateTime().toDate();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DateTime getDate() {
        return new DateTime(created);
    }

    public void setDate(DateTime date) {
        created = date.toDate();

    }
}
