package qnu.edu.vn.MXH.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import qnu.edu.vn.MXH.model.User;
import qnu.edu.vn.MXH.utils.JPAUtils;

import jakarta.persistence.EntityManager;

@Repository
public class UserRepository{
	// Mỗi lần thao tác DB phải có 1 EntityManager riêng
	
	

	// ceate
	public void save(User user) {
		EntityManager em=JPAUtils.getEntityManager();
		// Báo cho JPA biết: bắt đầu 1 giao dịch (transaction)
		em.getTransaction().begin();
		// persist = “thêm mới vào DB”
		em.persist(user);
		// commit = “bấm nút lưu chính thức”
		em.getTransaction().commit();
		em.close();
	}

	public User login(String username, String password) {
		EntityManager em=JPAUtils.getEntityManager();
	    try {
	        return em.createQuery(
	                "SELECT u FROM User u WHERE u.username = :u AND u.password = :p",
	                User.class)
	            .setParameter("u", username)
	            .setParameter("p", password)
	            .getSingleResult();
	    } catch (Exception e) {
	        return null;
	    } finally {
	        em.close();
	    }
	}

	public List<User> searchByUsername(String keyword) {
		EntityManager em=JPAUtils.getEntityManager();
		List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username LIKE :kw", User.class)
				.setParameter("kw", "%" + keyword + "%").getResultList();

		em.close();
		return users;
	}

}
