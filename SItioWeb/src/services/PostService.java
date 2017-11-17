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
import domain.model.Post;
import domain.model.Usuario;

public class PostService {	

	private HashMap<Long, Post> posts;
    private long nextId;
	private PostDAO pdao;
	
	
	public PostService(){
		
		pdao = new PostDAO();
		posts = new HashMap<Long, Post>();
		nextId = 0;
		
		for(Post p : uploadPosts()){
			
			posts.put(p.getId(), p);
			
			if(p.getId()>=nextId) nextId = p.getId() + 1;
		}
	}
	
	public List<Post> uploadPosts(){

		List<Post> listaPosts =  pdao.traer();
		 
		 return listaPosts;
	}
	
    
    public synchronized List<Post> findAll(String stringFilter) {
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
    
    public synchronized long count() {
        return posts.size();
    }
    
    public synchronized void delete(Post value) {
        posts.remove(value.getId());
        pdao.eliminar(value);
        pdao.eliminar(value);
    }
    
    public synchronized void save(Post entry) {
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
    
    public synchronized void createPost(Usuario usr, String cont){
    	
    	Post post = new Post();
    	post.setUsuario(usr);
    	Calendar cal = Calendar.getInstance();
    	post.setFecha(cal.getTime());
    	post.setContenido(cont);
    	
    	usr.addPost(post);
    	
    	pdao.guardar(post);
    	
     	save(post);
    }
    
    public synchronized void suprPost(Usuario usr, Post post){
    	
    	//if(usr.getUserName().equals(post.getUsr().getUserName())){
    		
    		delete(post);
    		System.out.println("BORRADO");
    //	}
    	//else System.out.println("Usuario incorrecto! - su Usuario es: "+ usr + " y el del post es: " + post.getUsr().toString());
    }
}
