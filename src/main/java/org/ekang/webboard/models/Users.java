package org.ekang.webboard.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="userid")
    private int userid;

    @Column(name="passwords")
    private String passwords;

    @Column(name="username")
    private String username;

    @Column(name="createdate")
    private Date createdate;

    public Users() {}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "User [id=" + userid + ", password="
                + passwords + ", username=" + username + ", createDate=" + createdate + "]";
    }
}
