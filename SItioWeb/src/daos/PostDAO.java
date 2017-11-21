package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import domain.model.Post;

public class PostDAO extends DAO<Post>{

	public static List<Post> traer() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Post> result = entityManager.createQuery("from Post",Post.class).getResultList(); 
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}

	public static void actualizarMeGustas(Post post, int total) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
				
		String hql = "update Post set cantMeGusta = :total where id = :id";
		 
		Query query = entityManager.createQuery(hql);
		query.setParameter("total", total);
		query.setParameter("id", post.getId());
		query.executeUpdate();
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void actualizarNoMeGustas(Post post, int total) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
				
		String hql = "update Post set cantNoMeGusta = :total where id = :id";
		 
		Query query = entityManager.createQuery(hql);
		query.setParameter("total", total);
		query.setParameter("id", post.getId());
		query.executeUpdate();
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
