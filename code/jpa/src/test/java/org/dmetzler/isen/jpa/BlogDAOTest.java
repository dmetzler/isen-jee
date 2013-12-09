package org.dmetzler.isen.jpa;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.inject.Inject;

public class BlogDAOTest {

    @Inject
    BlogDAO dao;

    @Test
    public void iCanCreateAndRetrieveABlogEntry() throws Exception {

        // On crée un billet de blog
        BlogEntry entry = dao.createEntry("Mon billet de blog");
        entry.setAuthor("Damien Metzler");
        entry.setContent("Le contenu de mon billet");
        checkBlogEntry(entry);

        // On le sauvegarde
        dao.saveEntry(entry);
        assertNotNull(entry.getId());
        Long id = entry.getId();

        // On le récupère
        entry = dao.getBlogEntry(id);
        checkBlogEntry(entry);

    }

    private void checkBlogEntry(BlogEntry entry) {
        assertThat(entry.getTitle()).isEqualTo("Mon billet de blog");
        assertThat(entry.getAuthor()).isEqualTo("Damien Metzler");
        assertThat(entry.getContent()).isEqualTo("Le contenu de mon billet");
        assertThat(entry.getDate().toString("YYYYmmdd")).isEqualTo(
                new DateTime().toString("YYYYmmdd"));
    }

    @Test
    public void shouldBeAbleToRetrieveAllBlogEntries() throws Exception {
        List<BlogEntry> blogEntries = dao.getBlogEntries();
        assertThat(blogEntries.size()).isEqualTo(1);
    }

    @Test
    public void lastEntriesShouldBeRetrievedFirst() throws Exception {
        createAndSaveEntry("last");
        List<BlogEntry> blogEntries = dao.getBlogEntries();
        assertThat(blogEntries.get(0).getTitle()).isEqualTo("last");
    }

    @Test
    public void shouldBeAbleToRetrieveLastEntries() throws Exception {
        createAndSaveEntry("un");
        createAndSaveEntry("deux");
        createAndSaveEntry("trois");

        List<BlogEntry> lastBlogEntries = dao.getLastBlogEntries(2);
        assertThat(lastBlogEntries.size()).isEqualTo(2);
    }

    @Test
    public void shouldBeAbleToRetrieveByTitle() throws Exception {
        List<BlogEntry> entries = dao.getBlogEntriesByTitle("un");
        assertThat(entries.size(), is(1));

        BlogEntry entry = entries.get(0);
        assertThat(entry.getTitle()).isEqualTo("un");
    }

    @Test
    public void shouldBeAbleToRetrieveEntriesForAPeriod() throws Exception {
        createAndSaveEntry("quatre", new DateTime(2010, 9, 6, 12, 42, 5, 0));
        createAndSaveEntry("cinq", new DateTime(2010, 9, 15, 15, 42, 5, 0));

        DateTime from = new DateTime(2010, 9, 10, 0, 0, 0, 0);
        DateTime to = new DateTime(2010, 9, 30, 0, 0, 0, 0);
        List<BlogEntry> entries = dao.getBlogEntries(from, to);
        assertThat(entries.size()).isEqualTo(1);
    }

    @Test
    public void shouldBeAbleToRetrieveEntriesForAGivenMonth() throws Exception {
        List<BlogEntry> entries = dao.getBlogEntriesForMonth(2010, 9);
        assertThat(entries.size()).isEqualTo(2);
    }

    @Test
    public void shouldBeAbleToRemoveAnEntry() throws Exception {
        BlogEntry entry = createAndSaveEntry("toBeRemoved");
        Long id = entry.getId();
        dao.removeEntry(entry);
        assertThat(dao.getBlogEntry(id)).isNull();
    }

    private BlogEntry createAndSaveEntry(String title, DateTime date) {
        BlogEntry entry = dao.createEntry(title);
        entry.setDate(date);
        dao.saveEntry(entry);
        return entry;
    }

    private BlogEntry createAndSaveEntry(String title) {
        return createAndSaveEntry(title, new DateTime());
    }

}
