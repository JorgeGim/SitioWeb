package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

import daos.UsuarioDAO;
import domain.model.Usuario;

public class UsuarioService {
	
	private static UsuarioService instance;
	private static HashMap<Long, Usuario> usuarios = new HashMap<Long, Usuario>();
    private static long nextId = 0;
	
	public static UsuarioService uploadUsers(){
		
		 if (instance == null) {
			 
			 final UsuarioService usuarioService = new UsuarioService();
			 
			 
			 List<Usuario> listaUsuarios = UsuarioDAO.traer();
			 
			 for(Usuario usuario : listaUsuarios)
			 {
				 usuarioService.save(usuario);
			 }
			 
			 instance = usuarioService;
		 }
		 
		 return instance;
	}
	
	public synchronized long count() {
        return usuarios.size();
    }
    
    public synchronized void delete(Usuario value) {
        usuarios.remove(value.getId());
        //PostDAO.borrarPost(value);
    }
    
    public synchronized static void save(Usuario entry) {
//        if (entry.getId() == null) {
//            entry.setId(nextId++);
//        }
        try {
        	System.out.println("original: " + entry.toString());
            entry = (Usuario) BeanUtils.cloneBean(entry);
            System.out.println("copia: " + entry.toString());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        usuarios.put(entry.getId(), entry);
        System.out.println(entry.toString());
    }
    
    public static Usuario crearUsuario(String cc, String nombre, String mail, String pass){
    	
    	Usuario usuario = new Usuario(cc, nombre, mail, pass);
    	
    	save(usuario);
    	UsuarioDAO.guardarUsuario(usuario);
    	
    	return usuario;
    }
    
    public synchronized void suprUsuario(Usuario usuario){
    	
    	if(usuario != null){
    		delete(usuario);
    		System.out.println("BORRADO");
    	}
    }

}
