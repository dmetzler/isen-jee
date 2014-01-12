package org.isen.blog.dao;

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.isen.blog.model.Post;

@Singleton
@Lock(LockType.READ)
public class PostDAO {

    @Inject
    private DAO dao;

    public Post create(String title, String content, String user) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        return dao.create(post);
    }

    public Post find(long id) {
        return dao.find(Post.class, id);
    }

    public List<Post> list(int first, int max) {
        return dao.namedFind(Post.class, "post.list", first, max);
    }

    public void delete(long id) {
        dao.delete(Post.class, id);
    }

    public Post update(long id, String user, String title, String content) {
        Post post = dao.find(Post.class, id);
        if (post == null) {
            throw new IllegalArgumentException("post id " + id + " not found");
        }

        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        return dao.update(post);
    }
}
