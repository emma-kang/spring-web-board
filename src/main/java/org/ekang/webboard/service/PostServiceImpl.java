package org.ekang.webboard.service;

import org.ekang.webboard.dao.PostsDAO;
import org.ekang.webboard.models.Posts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
public class PostServiceImpl implements PostService{

    private PostsDAO postsDAO;

    public void setPostDAO(PostsDAO postDAO) {
        this.postsDAO = postDAO;
    }

    @Override
    @Transactional
    public void addPost(Posts p) {
        this.postsDAO.addPost(p);
    }

    @Override
    @Transactional
    public void updatePost(Posts p) {
        this.postsDAO.updatePost(p);
    }

    @Override
    @Transactional
    public List<Posts> listPosts() {
        return this.postsDAO.listPosts();
    }

    @Override
    @Transactional
    public Posts getPostById(int id) {
        return this.postsDAO.getPostById(id);
    }

    @Override
    @Transactional
    public void removePost(int id) {
        this.postsDAO.removePost(id);
    }
}
