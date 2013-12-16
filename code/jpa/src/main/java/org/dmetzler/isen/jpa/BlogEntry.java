package org.dmetzler.isen.jpa;

import java.util.List;

import org.joda.time.DateTime;

public interface BlogEntry {

    void setAuthor(String string);

    void setContent(String string);

    Long getId();

    String getTitle();

    String getAuthor();

    String getContent();

    DateTime getDate();

    void setDate(DateTime date);

    Comment addComment(Comment comment);

    List<? extends Comment> getComments();

    void removeComment(Comment comment);


}
