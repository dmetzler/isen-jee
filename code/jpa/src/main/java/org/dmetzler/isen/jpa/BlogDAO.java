package org.dmetzler.isen.jpa;

import java.util.List;

import org.joda.time.DateTime;

public interface BlogDAO {

    BlogEntry createEntry(String string);

    void saveEntry(BlogEntry entry);

    BlogEntry getBlogEntry(Long id);

    List<BlogEntry> getBlogEntries();

    List<BlogEntry> getLastBlogEntries(int maxResults);

    List<BlogEntry> getBlogEntriesByTitle(String title);

    List<BlogEntry> getBlogEntries(DateTime from, DateTime to);

    List<BlogEntry> getBlogEntriesForMonth(int year, int month);

    void removeEntry(BlogEntry entry);

}
