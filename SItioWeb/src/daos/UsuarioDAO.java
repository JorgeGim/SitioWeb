package daos;

import domain.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {
	
	public UsuarioDAO() {
		super();
	}

	public Usuario buscar(String nombre){
		
		entityManager.getTransaction().begin();
		
		Usuario result;
		
		try{
			
			result = entityManager.createQuery("from Usuario u where u.userName = '"+nombre+"'",Usuario.class).getSingleResult(); 
		}
		catch(javax.persistence.NoResultException e){
			
			result = null;
		}
		
		entityManager.getTransaction().commit();
//		entityManager.close();
		
		return result;
	}
	
	public Usuario buscarPorEmail(String email){
		
		entityManager.getTransaction().begin();
		
		Usuario result;
		
		try{
			
			result = entityManager.createQuery("from Usuario u where u.e_mail = '"+email+"'",Usuario.class).getSingleResult(); 
		}
		catch(javax.persistence.NoResultException e){
			
			result = null;
		}
		
		entityManager.getTransaction().commit();
//		entityManager.close();
		
		return result;
	}

}
