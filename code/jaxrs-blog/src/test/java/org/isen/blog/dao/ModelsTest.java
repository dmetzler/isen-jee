package org.isen.blog.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.isen.blog.model.Post;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ModelsTest {

    private static EJBContainer container;

    @BeforeClass
    public static void start() {
        container = EJBContainer.createEJBContainer();
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
        final Post post = dao.create("A post", "A description", "jdoe");
        assertThat(dao.find(post.getId())).isNotNull();
    }

    @Test
    public void itCanAddACommentToAPost() throws Exception {
        final PostDAO dao = getPostDAO();
        final CommentDAO cdao = getCommentDao();
        final Post post = dao.create("A post", "A description", "jdoe");
        assertThat(dao.find(post.getId())).isNotNull();

        cdao.create("author", "content", post.getId());

        assertThat(dao.find(post.getId()).getComments()).hasSize(1);
    }



    private CommentDAO getCommentDao() throws NamingException {
        final CommentDAO cdao = (CommentDAO) container.getContext().lookup(
                "java:global/jaxrs-blog/CommentDAO");
        return cdao;
    }

    private PostDAO getPostDAO() throws NamingException {
        final PostDAO dao = (PostDAO) container.getContext().lookup(
                "java:global/jaxrs-blog/PostDAO");
        return dao;
    }

}
