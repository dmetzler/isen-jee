package org.dmetzler.isen.jpa;

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

}
