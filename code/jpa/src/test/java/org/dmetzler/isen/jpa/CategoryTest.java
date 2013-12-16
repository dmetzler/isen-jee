package org.dmetzler.isen.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.dmetzler.courses.isen.blog.api.BlogDAO;
import org.dmetzler.courses.isen.blog.api.BlogEntry;
import org.dmetzler.courses.isen.blog.api.Category;
import org.dmetzler.courses.isen.blog.api.CategoryDAO;
import org.dmetzler.courses.isen.blog.entity.CategoryImpl;
import org.dmetzler.courses.isen.jpa.guice.GuiceRunner;
import org.dmetzler.courses.isen.jpa.guice.H2DBModule;
import org.dmetzler.courses.isen.jpa.guice.JpaDaoModule;
import org.dmetzler.courses.isen.jpa.guice.Modules;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@RunWith(GuiceRunner.class)
@Modules({H2DBModule.class, JpaDaoModule.class})
public class CategoryTest {

    @Inject
    EntityManager em;

    @Inject
    CategoryDAO catDao;

    @Inject
    BlogDAO blogDao;

    @Test
    public void categoryTestIsWellInjected() throws Exception {
        assertThat(catDao, is(notNullValue()));
    }

    @Test
    public void categoryIsComparable() throws Exception {
        Integer i = new Integer(1);
        Category cat = new CategoryImpl("test");
        assertThat(cat.equals(i), is(false));

        Category cat2 = new CategoryImpl("test");
        assertThat(cat.equals(cat2), is(true));
        assertThat(cat.hashCode(), is(cat2.hashCode()));

        Category cat3 = new CategoryImpl("test2");
        assertThat(cat.hashCode(), is(not(cat3.hashCode())));
    }


    @Test
    public void iCanCreateAndPersistACategory() throws Exception {
        catDao.getOrCreateCategory("categorie1");
        assertThat(catDao.getCategories().size(), is(1));

        catDao.getOrCreateCategory("categorie1");
        assertThat(catDao.getCategories().size(), is(1));

        Category cat = catDao.getOrCreateCategory("categorie2");
        assertThat(catDao.getCategories().size(), is(2));

        catDao.deleteCategory(cat);
        List<Category> categories = catDao.getCategories();
        assertThat(categories.size(), is(1));
        Category category = categories.get(0);
        assertThat(category.getName(), is("categorie1"));
    }

    @Test
    public void categoryCanBeAddedToBlogEntries() throws Exception {
        BlogEntry entry = blogDao.createEntry("entree1");
        Category cat = catDao.getOrCreateCategory("categorie1");

        entry.addCategory(cat);
        blogDao.saveEntry(entry);

        em.clear();
        entry = blogDao.getBlogEntriesByTitle("entree1").get(0);
        List<? extends Category> categories = entry.getCategories();
        assertThat(categories.size(),is(1));
        assertThat(categories.contains(cat), is(true));

        cat = catDao.getOrCreateCategory("categorie1");
        entry.addCategory(cat);
        assertThat(categories.size(),is(1));


    }

    @Test
    public void categoryCanBeRemovedFromBlogEntries() throws Exception {
        BlogEntry entry = blogDao.getBlogEntriesByTitle("entree1").get(0);
        Category cat = catDao.getOrCreateCategory("categorie2");
        entry.addCategory(cat);
        blogDao.saveEntry(entry);
        assertThat(entry.getCategories().size(),is(2));
        entry.removeCategory(cat);
        blogDao.saveEntry(entry);

        em.clear();

        entry = blogDao.getBlogEntriesByTitle("entree1").get(0);
        assertThat(entry.getCategories().size(),is(1));

    }

    @Test
    public void categoryShouldHaveBlogEntries() throws Exception {
        Category cat1 = catDao.getOrCreateCategory("categorie1");
        Category cat2 = catDao.getOrCreateCategory("categorie2");

        BlogEntry entry = blogDao.createEntry("entree2");
        blogDao.saveEntry(entry);

        entry.addCategory(cat1);
        entry.addCategory(cat2);
        blogDao.saveEntry(entry);

        em.clear();
        cat1 = catDao.getOrCreateCategory("categorie1");
        cat2 = catDao.getOrCreateCategory("categorie2");

        assertThat(cat1.getBlogEntries().size(), is(2));
        assertThat(cat2.getBlogEntries().size(), is(1));
    }

}
