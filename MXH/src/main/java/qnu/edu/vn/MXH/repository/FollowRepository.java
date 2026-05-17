package qnu.edu.vn.MXH.repository;

import org.springframework.stereotype.Repository;

import qnu.edu.vn.MXH.model.Follow;
import qnu.edu.vn.MXH.model.FollowId;
import qnu.edu.vn.MXH.utils.JPAUtils;

import jakarta.persistence.EntityManager;
@Repository
public class FollowRepository {
	public void follow(int followerId, int followedId) {
	    EntityManager em = JPAUtils.getEntityManager();
	    em.getTransaction().begin();

	    Follow f = new Follow();
	    FollowId id = new FollowId(followerId, followedId);
	    
	    f.setId(id);

	    em.persist(f);

	    em.getTransaction().commit();
	    em.close();
	}

}
