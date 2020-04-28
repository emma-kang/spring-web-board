package org.ekang.webboard.service;

import org.ekang.webboard.models.Posts;

import java.util.List;

public interface PostService {
    public void addPost(Posts p);
    public void updatePost(Posts p);
    public List<Posts> listPosts();
    public Posts getPostById(int id);
    public void removePost(int id);
}