package org.ekang.webboard.dao;

import org.ekang.webboard.models.Posts;
import org.ekang.webboard.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostsDAOImpl implements PostsDAO{
    private static final Logger logger = LoggerFactory.getLogger(org.ekang.webboard.dao.UsersDAOImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void addPost(Posts p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Post added successfully : " + p);
    }

    @Override
    public void updatePost(Posts p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Post updated successfully :" + p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Posts> listPosts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Posts> postList = session.createQuery("from Posts").list();
        for (Posts p : postList) {
            logger.info("Post List: " + p);
        }
        return postList;
    }

    @Override
    public Posts getPostById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Posts p = (Posts) session.load(Posts.class, new Integer(id));
        logger.info("Posts loaded successfully : " + p);
        return p;
    }

    @Override
    public void removePost(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Posts p = (Posts) session.load(Posts.class, new Integer(id));
        if (p != null)
            session.delete(p);

        logger.info("User deleted successfully :" + p);
    }

    public List<Posts> getPostsByUserId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        String sql = "from Posts p inner join Users u where u.userid = :userid";
        List<Posts> postList = session.createQuery(sql).setParameter("userid", id).list();
        for (Posts p : postList) {
            logger.info("Post List by user " + id + ": " + p);
        }

        return postList;
    }


}
