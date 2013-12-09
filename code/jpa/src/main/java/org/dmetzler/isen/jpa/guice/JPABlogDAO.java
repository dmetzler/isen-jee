package org.dmetzler.isen.jpa.guice;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.dmetzler.isen.jpa.BlogDAO;
import org.dmetzler.isen.jpa.BlogEntry;
import org.dmetzler.isen.jpa.BlogEntryImpl;

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
        List<BlogEntry> resultList = em
                .createNamedQuery(BlogEntryImpl.ALL_ENTRIES).getResultList();
        return resultList;
    }

}
