package qnu.edu.vn.MXH.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import qnu.edu.vn.MXH.model.Post;
import qnu.edu.vn.MXH.utils.JPAUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
@Repository
public class PostRepository {
	
	public void create(Post post) {
		 EntityManager em=JPAUtils.getEntityManager();
	    em.getTransaction().begin();

	    em.persist(post);

	    em.getTransaction().commit();
	    em.close();
	}
	public List<Post> getNewsFeed(int userId) {
		 EntityManager em=JPAUtils.getEntityManager();
	    List<Integer> followingIds = em.createQuery(
	            "SELECT f.followedUserId FROM Follow f WHERE f.followingUserId = :id",
	            Integer.class)
	        .setParameter("id", userId)
	        .getResultList();

	    followingIds.add(userId); // thêm chính mình

	    List<Post> posts = em.createQuery(
	            "SELECT p FROM Post p WHERE p.userId IN :ids ORDER BY p.createdAt DESC",
	            Post.class)
	        .setParameter("ids", followingIds)
	        .getResultList();

	    em.close();
	    return posts;
	}
	// Tìm theo title
    public List<Post> findByTitleContainingIgnoreCase(String keyword) {
    	EntityManager em=JPAUtils.getEntityManager();
        String jpql = "SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(:keyword)";
        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    // Tìm theo title OR body
    public List<Post> findByTitleOrBodyContainingIgnoreCase(String keyword) {
    	EntityManager em=JPAUtils.getEntityManager();
        String jpql = "SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(:kw) OR LOWER(p.body) LIKE LOWER(:kw)";
        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
        query.setParameter("kw", "%" + keyword + "%");
        return query.getResultList();
    }

}
