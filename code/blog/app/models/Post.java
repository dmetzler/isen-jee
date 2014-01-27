package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Post extends Model {

    @ManyToOne
    public User author;

    public String title;

    @Lob
    public String content;
    public Date postedAt;

    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments ;

    public Post(User author, String title, String content) {
        comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }

    public Post addComment(String author, String content) {
        Comment comment = new Comment(this, author, content).save();
        comments .add(comment);
        this.save();
        return this;
    }

}
