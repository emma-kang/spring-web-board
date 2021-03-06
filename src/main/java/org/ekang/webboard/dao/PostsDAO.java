package org.ekang.webboard.dao;

import org.ekang.webboard.models.Posts;

import java.util.List;

public interface PostsDAO {
    void addPost(Posts p);
    void updatePost(Posts p);
    List<Posts> listPosts();
    Posts getPostById(int id);
    void removePost(int id);
}
