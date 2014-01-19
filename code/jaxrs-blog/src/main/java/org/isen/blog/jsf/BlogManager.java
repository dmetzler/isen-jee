package org.isen.blog.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Post;

@Named("blogManager")
@SessionScoped
public class BlogManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    PostDAO dao;

    Post currentPost = null;

    public List<Post> getAll() {
        return dao.list(0, 20);
    }

    public String create() {
        currentPost = new Post();
        return "edit";
    }

    public String view(Post post) {
        currentPost = post;
        return "view";
    }
    public String cancel() {
        if(currentPost.getId() > 0) {
            return "view";
        } else {
            return "home";
        }

    }

    public String delete() {
        dao.delete(currentPost.getId());
        currentPost = null;
        return "home";
    }

    public String edit() {
        return "edit";

    }

    public Post getCurrent() {
        return currentPost;
    }

    public String save() {
        if (currentPost.getId() > 0) {
            dao.update(currentPost.getId(), currentPost.getUser(),
                    currentPost.getTitle(), currentPost.getContent());
        } else {
            dao.create(currentPost.getTitle(), currentPost.getContent(), currentPost.getUser());
        }
        return "view";
    }

}
