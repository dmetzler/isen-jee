package org.dmetzler.isen.jpa.guice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.dmetzler.isen.jpa.BlogDAO;
import org.dmetzler.isen.jpa.BlogEntry;
import org.dmetzler.isen.jpa.BlogEntryImpl;
import org.joda.time.DateTime;

import com.google.inject.Inject;

public class JPABlogDAO implements BlogDAO {

    @Inject
    EntityManager em;

    public BlogEntry createEntry(String title) {
        return new BlogEntryImpl(title);
    }

    public void saveEntry(BlogEntry entry) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.persist(entry);
        tx.commit();
    }

    public BlogEntry getBlogEntry(Long id) {
        return em.find(BlogEntryImpl.class, id);
    }

    public List<BlogEntry> getBlogEntries() {
        List<BlogEntry> resultList = em.createNamedQuery(
                BlogEntryImpl.ALL_ENTRIES).getResultList();
        return resultList;
    }

    public List<BlogEntry> getLastBlogEntries(int maxResults) {
        return em.createNamedQuery(BlogEntryImpl.ALL_ENTRIES)
                .setMaxResults(maxResults).getResultList();
    }

    public List<BlogEntry> getBlogEntriesByTitle(String title) {
        List<BlogEntry> resultList = em
                .createNamedQuery(BlogEntryImpl.ENTRIES_BY_TITLE)
                .setParameter("title", title).getResultList();
        return resultList;
    }

    @Override
    public List<BlogEntry> getBlogEntries(DateTime from, DateTime to) {
        List<BlogEntry> resultList = em
                .createNamedQuery(BlogEntryImpl.ENTRIES_BY_PERIOD)
                .setParameter("from", from.toDate())
                .setParameter("to", to.toDate())
                .getResultList();
        return resultList;
    }

    @Override
    public List<BlogEntry> getBlogEntriesForMonth(int year, int month) {
        DateTime startMonth = new DateTime(year,month, 1, 0,0,0,0);
        DateTime endMonth = startMonth.dayOfMonth().withMaximumValue();
        return getBlogEntries(startMonth, endMonth);
    }

    @Override
    public void removeEntry(BlogEntry entry) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(entry);
        tx.commit();

    }

}
