package services;

import daos.UsuarioDAO;
import domain.model.Espectador;
import domain.model.Usuario;

public class UsuarioService {
	
	Espectador specter;
	
	public UsuarioService(Espectador e){
		
		specter = e;
	}
	
	public boolean crearUsuario(String cc, String nombre, String mail, String pass){
    	
    	Usuario usuario = new Usuario(cc, nombre, mail, pass);
    	
    	return save(usuario);
    }
    
    public void delete(Usuario value) {
       UsuarioDAO.eliminar(value);
    }
    
    public Usuario getUsuario(String username,String contraseña){
    	
    	Usuario usr = UsuarioDAO.buscar(username);
    	
    	if(usr==null){
    		
    		specter.notificar("Usuario Inexistente");
    		
    		return null;
    	}
    	
    	else if(!usr.getPassword().equals(contraseña)){
    		
    		specter.notificar("Contraseña Incorrecta");
    		
    		return null;
    	}
    	
    	specter.notificar("Ingreso Exitoso");
  
    	return usr;
    }
    
    public boolean save(Usuario entry) {
        
    	if(ChequearNombre(entry.getUserName())) {
    		
    		UsuarioDAO.guardar(entry);
    		
    		return true;
    	}
    	
    	else{
    		
    		specter.notificar("Usuario Ya Existente!!");
  
    		return false;
    	}
    }
    
    private boolean ChequearNombre(String userName) {

    	if(UsuarioDAO.buscar(userName)==null) return true;
    	
    	else return false;
	}
}
