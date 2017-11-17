package daos;

import java.util.List;

import javax.persistence.EntityManager;

import domain.model.Post;

public class PostDAO extends DAO<Post>{
	@Override
	public List<Post> traer() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Post> result = entityManager.createQuery("from Post",Post.class).getResultList(); 
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}
}
