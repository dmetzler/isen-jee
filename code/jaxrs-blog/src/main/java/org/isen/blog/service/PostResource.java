package org.isen.blog.service;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import org.isen.blog.dao.CommentDAO;

import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Comment;
import org.isen.blog.model.Post;

public class PostResource {

    private PostDAO dao;
    private CommentDAO cdao;
    private Post post;


    public PostResource() {

    }
    public PostResource(PostDAO dao, CommentDAO cdao, Post post) {
        this.dao = dao;
        this.cdao = cdao;
        this.post = post;
    }

    @GET
    public Post doGet() {
        return post;
    }

    @PUT
    public Post doUpdate(Post updatedPost) {
        return dao.update(post.getId(), updatedPost.getUser(), updatedPost.getTitle(),
                updatedPost.getContent());
    }

    @DELETE
    public void doDelete() {
        dao.delete(post.getId());
    }

    @Path("comments")
    @POST
    public Comment createComment(Comment newComment) {
        return cdao.create(newComment.getAuthor(), newComment.getContent(), post.getId());
    }
    
    @Path("comments")
    @GET
    public List<Comment> getComments() {
        return cdao.list(post.getId());
    }

}
