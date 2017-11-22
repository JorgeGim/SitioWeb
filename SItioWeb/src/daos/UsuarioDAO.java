package daos;

import javax.persistence.EntityManager;

import domain.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	public static Usuario buscar(String nombre){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuario result;
		
		try{
			
			result = entityManager.createQuery("from Usuario u where u.userName = '"+nombre+"'",Usuario.class).getSingleResult(); 
		}
		catch(javax.persistence.NoResultException e){
			
			result = null;
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}
	
	public static Usuario buscarPorEmail(String email){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Usuario result;
		
		try{
			
			result = entityManager.createQuery("from Usuario u where u.e_mail = '"+email+"'",Usuario.class).getSingleResult(); 
		}
		catch(javax.persistence.NoResultException e){
			
			result = null;
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}

}
