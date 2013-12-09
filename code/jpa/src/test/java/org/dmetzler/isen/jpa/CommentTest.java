package org.dmetzler.isen.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Comment;

import com.google.inject.Inject;

public class CommentTest {

    @Inject
    BlogDAO dao;

    @Inject
    EntityManager em;

    @Test
    public void iCanAddAndRetrieveComment() throws Exception {
        BlogEntry entry = dao.createEntry("billet de test");
        Comment comment = new FakeComment("author", "un commentaire");

        entry.addComment(comment);
        dao.saveEntry(entry);

        entry = dao.getBlogEntry(entry.getId());
        List<? extends Comment> comments = entry.getComments();
        assertThat(comments.size(), is(1));
        comment = comments.get(0);
        assertThat(comment.getBlog()).is(entry);
        assertThat(comment.getDate().toString("YYYYmmdd")).isEqualTo(new DateTime().toString("YYYYmmdd"));
        assertThat(comment.getAuthor()).isEqualTo("author");
        assertThat(comment.getContent()).isEqualTo("un commentaire");

    }

    @Test
    public void commentCanBeRemoved() throws Exception {
        BlogEntry entry = dao.createEntry("billet de test2");
        Comment comment = new FakeComment("author", "un commentaire");
        comment = entry.addComment(comment);

        comment = new FakeComment("author", "un autre commentaire");
        comment = entry.addComment(comment);
        dao.saveEntry(entry);

        entry = dao.getBlogEntriesByTitle("billet de test2").get(0);
        assertThat(entry.getComments()).hasSize(2);

        entry.removeComment(comment);
        dao.saveEntry(entry);

        //To avoid that JPA keeps comment in its session
        em.clear();

        entry = dao.getBlogEntriesByTitle("billet de test2").get(0);
        assertThat(entry.getComments()).hasSize(1);
    }


}