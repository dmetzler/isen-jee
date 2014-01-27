package org.isen.blog.jsf;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.isen.blog.dao.CommentDAO;
import org.isen.blog.dao.PostDAO;
import org.isen.blog.model.Post;

@Named("blogManager")
@SessionScoped
public class BlogManager implements Serializable {

    @Inject
    PostDAO dao;

    @Inject
    CommentDAO cdao;

    @Inject
    CommentBean comment;

    private Post currentPost;

    public List<Post> getAll() {
        return dao.list(0, 20);
    }

    public String view(Post post) {
        this.currentPost = post;
        return "view";
    }

    public String create() {
        this.currentPost = new Post();
        return "edit";
    }

    public String update() {
        if (currentPost.getId() == 0) {
            currentPost = dao.create(currentPost.getTitle(), currentPost.getContent(),
                    currentPost.getUser());
        } else {
            currentPost = dao.update(currentPost.getId(),
                    currentPost.getUser(), currentPost.getTitle(),
                    currentPost.getContent());
        }
        return "view";
    }

    public String delete() {
        dao.delete(currentPost.getId());
        return "index";
    }

    public String edit() {
        return "edit";
    }

    public String cancel() {
        return "view";
    }

    public Post getCurrent() {
        return currentPost;
    }

    public String addComment() {
        cdao.create(comment.getAuthor(), comment.getContent(),
                currentPost.getId());
        comment.setAuthor("");
        comment.setContent("");
        return "view";

    }
}
