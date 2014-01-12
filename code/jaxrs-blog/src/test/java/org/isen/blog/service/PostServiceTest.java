package org.isen.blog.service;

import static org.apache.openejb.loader.JarLocation.jarLocation;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.tomee.embedded.EmbeddedTomEEContainer;
import org.apache.ziplock.Archive;
import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Post;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PostServiceTest {

    private static EJBContainer container;

    @BeforeClass
    public static void start() throws IOException {
        final File webApp = Archive.archive()
                .copyTo("WEB-INF/classes", jarLocation(PostDAO.class)).asDir();
        final Properties p = new Properties();
        p.setProperty(EJBContainer.APP_NAME, "jaxrs-blog");
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

    @Test
    public void itCanCreateAPost() throws NamingException {

        final PostDAO dao = getPostDAO();
        final PostServiceClientAPI client = JAXRSClientFactory.create(getServiceURI(),
                PostServiceClientAPI.class);



        Post post = new Post();
        post.setTitle("New title");
        post.setContent("content");
        post.setUser("jdoe");

        post = client.create(post);





        post = dao.find(post.getId());
        assertThat(post.getTitle()).isEqualTo("New title");
        assertThat(post.getContent()).isEqualTo("content");
        assertThat(post.getUser()).isEqualTo("jdoe");

        dao.delete(post.getId());


    }




    @Test
    public void itCanViewAPost() throws Exception {

        PostDAO dao = getPostDAO();
        Post post = dao.create("A new post", "Another Content", "jdoe");


        final PostServiceClientAPI client = JAXRSClientFactory.create(getServiceURI(),
                PostServiceClientAPI.class);

        post = client.show(post.getId()).doGet();
        assertThat(post.getTitle()).isEqualTo("A new post");
        assertThat(post.getContent()).isEqualTo("Another Content");
        assertThat(post.getUser()).isEqualTo("jdoe");

        dao.delete(post.getId());

    }

    @Test
    public void itCanDeleteAPost() throws Exception {
        PostDAO dao = getPostDAO();
        Post post = dao.create("A new post", "Another Content", "jdoe");


        final PostServiceClientAPI client = JAXRSClientFactory.create(getServiceURI(),
                PostServiceClientAPI.class);


        client.show(post.getId()).doDelete();

        assertThat(dao.find(post.getId())).isNull();
    }



    @Path("/api/post")
    @Produces({ "text/xml", "application/json" })
    public static interface PostServiceClientAPI {

        @POST
        public Post create(Post post);

        @GET
        public List<Post> list(
                @QueryParam("first") @DefaultValue("0") int first,
                @QueryParam("max") @DefaultValue("20") int max);

        @Path("{id}")
        public PostResourceAPI show(@PathParam("id") long id);
    }

    public static interface PostResourceAPI {
        @GET
        public Post doGet();

        @DELETE
        public void doDelete();


    }

    private String getServiceURI() {
        final String uri = "http://127.0.0.1:"
                + System.getProperty(EmbeddedTomEEContainer.TOMEE_EJBCONTAINER_HTTP_PORT)
                + "/jaxrs-blog";
        return uri;
    }


    private PostDAO getPostDAO() throws NamingException {
        final PostDAO dao = (PostDAO) container.getContext().lookup("java:global/jaxrs-blog/PostDAO");
        return dao;
    }

}
