package org.ekang.webboard.dto;

public class PostDTO {
    private String username;
    private String passwords;
    private String title;
    private String postbody;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostbody() {
        return postbody;
    }

    public void setPostbody(String postbody) {
        this.postbody = postbody;
    }

    @Override
    public String toString() {
        return "PostDTO [username=" + username + ", passwords=" + passwords
                + ", title=" + title + ",+ postbody=" + postbody + "]";
    }
}
