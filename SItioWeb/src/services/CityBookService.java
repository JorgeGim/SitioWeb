package services;

import domain.model.Espectador;
import domain.model.Usuario;

public class CityBookService {
	
	Usuario usr;
	PostService postService;
	UsuarioService usrService;
	Espectador specterUsr;
	Espectador specterPost;
	
	public CityBookService(){
		
		specterUsr = new Espectador();
		specterPost = new Espectador();
		postService = new PostService();
		usrService = new UsuarioService(specterUsr);
	}
	
	
	public void Logueo(String usrName, String pass){
		
		Usuario usr = usrService.getUsuario(usrName, pass);
		
		if(usr!=null){
			
			if(usr.getPassword().equals(pass)){
				
				this.usr = usr;
			}
		}
		
		System.out.println(specterUsr.getInforme());
	}
}
