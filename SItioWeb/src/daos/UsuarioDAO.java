package daos;

import java.util.List;

import javax.persistence.EntityManager;

import domain.model.Usuario;

public class UsuarioDAO extends DAO<Usuario> {

	@Override
	public List<Usuario> traer()
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Usuario> result = entityManager.createQuery("from Usuario", Usuario.class).getResultList(); 
		
		for(Usuario usuario : result)
			System.out.println(usuario.getId()  + " " + usuario.getUserName());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return result;
	}

	public static void guardarUsuario(Usuario usuario)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.merge(usuario);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public static void borrarUsuario(Usuario usuario)
	{
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.remove(usuario);
		
		entityManager.getTransaction().commit();
		entityManager.close();
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
