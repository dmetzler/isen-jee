package org.isen.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import org.isen.blog.dao.CommentDAO;
import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Comment;
import org.isen.blog.model.Post;
import org.junit.Test;

public class PostServiceTest extends ContainerHarness {

    @Test
    public void itCanCreateAPost() throws NamingException {

        final PostDAO dao = getEjb("PostDAO", PostDAO.class);
        final PostServiceClientAPI client = JAXRSClientFactory.create(
                getServiceURI(), PostServiceClientAPI.class);

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

        final PostServiceClientAPI client = JAXRSClientFactory.create(
                getServiceURI(), PostServiceClientAPI.class);

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

        final PostServiceClientAPI client = JAXRSClientFactory.create(
                getServiceURI(), PostServiceClientAPI.class);

        client.show(post.getId()).doDelete();

        assertThat(dao.find(post.getId())).isNull();
    }

    @Test
    public void itCanCreateAComment() throws Exception {
        final PostDAO pdao = getEjb("PostDAO", PostDAO.class);
        final CommentDAO cdao = getEjb("CommentDAO", CommentDAO.class);
        Post post = pdao.create("Un titre", "un contenu", "un user");

        final PostServiceClientAPI client = JAXRSClientFactory.create(
                getServiceURI(), PostServiceClientAPI.class);

        Comment comment = new Comment();
        comment.setAuthor("un commentateur");
        comment.setContent("un commentaire");

        client.show(post.getId()).createComment(comment);

        assertThat(cdao.list(post.getId())).hasSize(1);

    }

    @Test
    public void itListComments() throws Exception {
        final PostDAO pdao = getEjb("PostDAO", PostDAO.class);
        final CommentDAO cdao = getEjb("CommentDAO", CommentDAO.class);
        Post post = pdao.create("Un titre", "un contenu", "un user");
        cdao.create("un commentateur", "un commentaire", post.getId());

        final PostServiceClientAPI client = JAXRSClientFactory.create(
                getServiceURI(), PostServiceClientAPI.class);

        List<Comment> comments = client.show(post.getId()).getComments();

        assertThat(comments).hasSize(1);

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

        @Path("comments")
        @GET
        public List<Comment> getComments();

        @POST
        @Path("comments")
        public Comment createComment(Comment comment);

    }

}
