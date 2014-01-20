package org.isen.blog.jsf;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.isen.blog.model.Post;

@SessionScoped
@Named("currentPost")
public class CurrentPost implements Serializable {

    @Inject
    BlogManager bm;

    private Post getPost() {
        return bm.getCurrent();
    }

    public String getTitle() {
        return getPost().getTitle();
    }

    public void setTitle(String title) {
        getPost().setTitle(title);
    }

    public String getContent() {
        return getPost().getContent();
    }

    public void setContent(String content) {
        getPost().setContent(content);
    }

    public String getUser() {
        return getPost().getUser();
    }

    public void setUser(String user) {
        getPost().setUser(user);
    }

    public Date getCreated() {
        return getPost().getCreated();
    }

}
