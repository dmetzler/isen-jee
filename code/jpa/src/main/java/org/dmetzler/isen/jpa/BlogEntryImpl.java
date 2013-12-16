package org.dmetzler.isen.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.joda.time.DateTime;

@Entity(name = "Post")
@NamedQueries({ @NamedQuery(name = BlogEntryImpl.ALL_ENTRIES, //
query = "FROM Post ORDER BY created DESC"),//
        @NamedQuery(name = BlogEntryImpl.ENTRIES_BY_TITLE,//
        query = "FROM Post WHERE title = :title"),//
        @NamedQuery(name = BlogEntryImpl.ENTRIES_BY_PERIOD,//
        query = "FROM Post WHERE created >= :from and created <= :to") })
public class BlogEntryImpl implements BlogEntry {

    public static final String ALL_ENTRIES = "ALL_ENTRIES";

    public static final String ENTRIES_BY_TITLE = "ENTRIES_BY_TITLE";

    public static final String ENTRIES_BY_PERIOD = "ENTRIES_BY_PERIOD";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<CommentImpl> comments = new ArrayList<>();

    private String author;
    private String content;
    private String title;
    private Date created;

    public BlogEntryImpl() {

    }

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

    @Override
    public Comment addComment(Comment comment) {
        CommentImpl jpaComment = new CommentImpl(this, comment.getAuthor(),
                comment.getContent());
        comments.add(jpaComment);
        return jpaComment;


    }

    @Override
    public List<? extends Comment> getComments() {
        return comments;
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);

    }

}
