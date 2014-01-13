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

public class PostServiceTest extends ContainerHarness {


    @Test
    public void itCanCreateAPost() throws NamingException {

        final PostDAO dao = getEjb("PostDAO", PostDAO.class);
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

        final PostDAO dao = getEjb("PostDAO", PostDAO.class);
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
        final PostDAO dao = getEjb("PostDAO", PostDAO.class);
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



}
