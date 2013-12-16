package org.dmetzler.isen.jpa.guice;

import org.dmetzler.isen.jpa.BlogDAO;

import com.google.inject.AbstractModule;

public class JpaDaoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BlogDAO.class).to(JPABlogDAO.class);
    }

}
