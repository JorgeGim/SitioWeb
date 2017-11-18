package daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO<T> {
	
	protected static EntityManagerFactory entityManagerFactory = 
	Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

	public void guardar(T t){
	
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(t);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Guardado");
	}
	
	public void eliminar(T t){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public abstract List<T> traer();
}
