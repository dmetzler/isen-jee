package org.dmetzler.isen.jpa;

import java.util.List;

public interface BlogDAO {

    BlogEntry createEntry(String string);

    void saveEntry(BlogEntry entry);

    BlogEntry getBlogEntry(Long id);

    List<BlogEntry> getBlogEntries();

}
