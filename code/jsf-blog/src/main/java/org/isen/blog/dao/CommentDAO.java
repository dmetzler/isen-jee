package org.isen.blog.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.isen.blog.model.Comment;
import org.isen.blog.model.Post;

@Singleton
@Lock(LockType.READ)
public class CommentDAO {

    @Inject
    private DAO dao;

    public List<Comment> list(long postId) {
        Post post = dao.find(Post.class, postId);
        if (post == null) {
            throw new IllegalArgumentException("post with id " + postId + " not found");
        }
        return Collections.unmodifiableList(post.getComments());
    }

    public Comment create(String author, String content, long postId) {
        Post post = dao.find(Post.class, postId);
        if (post == null) {
            throw new IllegalArgumentException("post with id " + postId + " not found");
        }

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        dao.create(comment);
        comment.setPost(post);
        return comment;
    }

    public void delete(long id) {
        dao.delete(Comment.class, id);
    }

    public Comment update(long id, String author, String content) {
        Comment comment = dao.find(Comment.class, id);
        if (comment == null) {
            throw new IllegalArgumentException("comment with id " + id + " not found");
        }

        comment.setAuthor(author);
        comment.setContent(content);
        return dao.update(comment);
    }
}
