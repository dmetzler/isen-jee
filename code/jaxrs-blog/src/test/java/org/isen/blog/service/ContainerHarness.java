package org.isen.blog.service;

import static org.apache.openejb.loader.JarLocation.jarLocation;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.apache.tomee.embedded.EmbeddedTomEEContainer;
import org.apache.ziplock.Archive;
import org.isen.blog.dao.PostDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class ContainerHarness {
    private static final String APP_NAME = "jaxrs-blog";
    protected static EJBContainer container;

    @BeforeClass
    public static void start() throws IOException {
        final File webApp = Archive.archive()
                .copyTo("WEB-INF/classes", jarLocation(PostDAO.class)).asDir();
        final Properties p = new Properties();
        p.setProperty(EJBContainer.APP_NAME, APP_NAME);
        p.setProperty(EJBContainer.PROVIDER, "tomee-embedded"); // need web
                                                                // feature
        p.setProperty(EJBContainer.MODULES, webApp.getAbsolutePath());
        p.setProperty(EmbeddedTomEEContainer.TOMEE_EJBCONTAINER_HTTP_PORT, "-1"); // random
                                                                                    // port
        container = EJBContainer.createEJBContainer(p);
    }

    @AfterClass
    public static void stop() {
        if (container != null) {
            container.close();
        }
    }

    protected String getServiceURI() {
        final String uri = "http://127.0.0.1:"
                + System.getProperty(EmbeddedTomEEContainer.TOMEE_EJBCONTAINER_HTTP_PORT)
                + "/jaxrs-blog";
        return uri;
    }

    @SuppressWarnings("unchecked")
    protected <E> E getEjb(String name, Class<E> klass) throws NamingException {
        return (E) container.getContext().lookup(
                "java:global/" + APP_NAME + "/" + name);

    }
}
