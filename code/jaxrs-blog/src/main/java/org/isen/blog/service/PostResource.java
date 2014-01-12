package org.isen.blog.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Post;

public class PostResource {

    private PostDAO dao;
    private Post post;

    public PostResource(PostDAO dao, Post post) {
        this.dao = dao;
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

}
