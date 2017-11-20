package services;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import daos.UsuarioDAO;
import domain.model.Usuario;

public class UsuarioService {
	
	public boolean crearUsuario(String cc, String nombre, String mail, String pass){
    	
    	Usuario usuario = new Usuario(cc, nombre, mail, pass);
    	
    	return save(usuario);
    }
    
    public static void delete(Usuario value) {
       UsuarioDAO.eliminar(value);
    }
    
    public static boolean save(Usuario entry) {
        
    	if(ChequearNombre(entry.getUserName())) {
    		
    		UsuarioDAO.guardar(entry);
    		
    		return true;
    	}
    	
    	else{
    		
    		System.out.println("usuario ya existente!!");
    		
    		return false;
    	}
    }
    
    private static boolean ChequearNombre(String userName) {

    	if(UsuarioDAO.buscar(userName)==null) return true;
    	
    	else return false;
	}
}
