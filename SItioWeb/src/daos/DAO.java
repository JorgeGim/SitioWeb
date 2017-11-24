package daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> {
	
	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");

	EntityManager entityManager;
	
	public DAO(){
		
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void guardar(T t){
	
		entityManager.getTransaction().begin();
		
		entityManager.persist(t);
		
		entityManager.getTransaction().commit();
//		entityManager.close();
		
		System.out.println("Guardado");
	}
	
	public void actualizar(T t){
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(t);
		
		entityManager.getTransaction().commit();
//		entityManager.close();
		
		System.out.println("Actualizado");
	}
	
	public void eliminar(T t){
		
		entityManager.getTransaction().begin();
		
		entityManager.remove(entityManager.merge(t));
		
		entityManager.getTransaction().commit();
//		entityManager.close();
		
		System.out.println("Borrado");
	}
}
