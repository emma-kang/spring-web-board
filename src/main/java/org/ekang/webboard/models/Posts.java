package org.ekang.webboard.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts")
public class Posts {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="postid")
    private int postid;

    @Column(name="userid")
    private int userid;

    @Column(name="postingdate")
    private Date postingDate;

    @Column(name="title")
    private String title;

    @Column(name="body")
    private String body;

    public Posts() {}

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post [id=" + postid + ", userid=" + userid
                + ", postingDate=" + postingDate + ", title=" + title + ",body=" + body + "]";
    }
}
