package daos;

import java.util.List;

import javax.persistence.EntityManager;

import domain.model.Usuario;

public class UsuarioDAO extends DAO<Usuario>{

	@Override
	public List<Usuario> traer() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Usuario buscar(String nombre){
		
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

}
