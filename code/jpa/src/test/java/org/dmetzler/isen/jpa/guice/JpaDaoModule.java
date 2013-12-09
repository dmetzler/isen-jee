package org.dmetzler.isen.jpa.guice;

import org.dmetzler.courses.isen.blog.JPABlogDAO;
import org.dmetzler.courses.isen.blog.JPACategoryDAO;
import org.dmetzler.courses.isen.blog.api.BlogDAO;
import org.dmetzler.courses.isen.blog.api.CategoryDAO;

import com.google.inject.AbstractModule;

public class JpaDaoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BlogDAO.class).to(JPABlogDAO.class);
        bind(CategoryDAO.class).to(JPACategoryDAO.class);
    }

}
