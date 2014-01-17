package org.isen.blog.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.isen.blog.dao.CommentDAO;

import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Post;


@Path("post")
@Produces({"application/json","text/xml"})
public class PostService {

    @Inject
    private PostDAO dao;
    
    @Inject
    private CommentDAO cdao;


    @POST
    public Post create(Post post) {
        return dao.create(post.getTitle(), post.getContent(), post.getUser());
    }

    @GET
    public List<Post> list(@QueryParam("first") @DefaultValue("0") int first,
                           @QueryParam("max") @DefaultValue("20") int max) {
        return dao.list(first, max);
    }

    @Path("{id}")
    public Object show(@PathParam("id") long id) {
        return new PostResource(dao, cdao, dao.find(id));
    }


}
