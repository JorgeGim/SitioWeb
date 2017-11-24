package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

import daos.PostDAO;
import daos.UsuarioDAO;
import daos.VotoDAO;
import domain.model.Post;
import domain.model.Usuario;
import domain.model.Voto;

public class PostService {	

	private HashMap<Long, Post> posts;
    private long nextId;
		
	public PostService(){

		refreshPosts();
	}
	
	public void refreshPosts(){
		
		posts = new HashMap<Long, Post>();
		nextId = 0;
		
		List<Post> listaPosts =  PostDAO.traer();
		
		for(Post p : listaPosts){
			
			posts.put(p.getId(), p);
			
			if(p.getId()>=nextId) nextId = p.getId() + 1;
		}
	}
	
    
    public List<Post> findAll(String stringFilter) {
    	
    	refreshPosts();
    	
        ArrayList<Post> arrayList = new ArrayList<>();
        
        for (Post post : posts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter
                        .isEmpty())
                        || post.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(post.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PostService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Post>() {

            @Override
            public int compare(Post o1, Post o2) {
                return (int) (o2.getId() - o1.getId());
            }       
        });
        return arrayList;
    }
    
    public long count() {
        return posts.size();
    }
    
    public void delete(Post value) {
        posts.remove(value.getId());
        PostDAO.eliminar(value);
        PostDAO.eliminar(value);
    }
    
    public void save(Post entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Post) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        posts.put(entry.getId(), entry);
    }
    
    public void createPost(Usuario usr, String cont){
    	
    	Post post = new Post();
    	post.setUsuario(usr);
    	Calendar cal = Calendar.getInstance();
    	post.setFecha(cal.getTime());
    	post.setContenido(cont);
    	
    	usr.addPost(post);
    	
    	PostDAO.guardar(post);
    	
     	save(post);
    }
    
    public void suprPost(Usuario usr, Post post){
    	
    	//if(usr.getUserName().equals(post.getUsr().getUserName())){
    		
    		delete(post);
    		System.out.println("BORRADO");
    //	}
    	//else System.out.println("Usuario incorrecto! - su Usuario es: "+ usr + " y el del post es: " + post.getUsr().toString());
    }

	public void incrementarMeGusta(Post post, Usuario usr) {
		
		Voto v = new Voto();
		
		Post p = posts.get(post.getId());
		
		v.like();
		v.setPost(p);
		v.setUsr(usr);
		
		Voto flag = null;
		
		for(Voto vote : p.getVotos()){
			
			if(vote.equals(v)){
				
				flag = vote;
			}
		}
		
		if(flag!=null){
			
			if(flag.getVoto() == v.getVoto())	v.disVotar();
			
			v.setId(flag.getId());
			VotoDAO.actualizar(v);
		}
		
		else{
			p.votar(v);
			usr.addVoto(v); 
			VotoDAO.guardar(v);
		}
	}

	public void incrementarNoMeGusta(Post post, Usuario usr) {
		
		Voto v = new Voto();
		
		Post p = posts.get(post.getId());
		
		v.dislike();
		v.setPost(p);
		v.setUsr(usr);
		
		Voto flag = null;
		
		for(Voto vote : p.getVotos()){
			
			if(vote.equals(v)){
				
				flag = vote;
			}
		}
		
		if(flag != null){
			
			if(flag.getVoto()==v.getVoto())	v.disVotar();
			
			v.setId(flag.getId());
			VotoDAO.actualizar(v);
		}
		
		else{
			p.votar(v);
			usr.addVoto(v); 
			VotoDAO.guardar(v);
		}
	}
}
