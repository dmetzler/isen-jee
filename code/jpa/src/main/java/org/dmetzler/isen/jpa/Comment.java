package org.dmetzler.isen.jpa;

import org.joda.time.DateTime;

public interface Comment {

    BlogEntry getBlog();

    DateTime getDate();

    String getAuthor();

    String getContent();

    Long getId();

}
