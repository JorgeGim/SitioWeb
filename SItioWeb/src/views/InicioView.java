package views;

import java.util.ArrayList;

import services.PostService;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import domain.model.Post;
import domain.model.Usuario;

@SuppressWarnings("serial")
public class InicioView extends HorizontalLayout implements View{
	
	protected static final String NAME = "inicio";
	 TextField filter;
	 Grid postsList;
	 Button crearPost;
	 Button borrarPost;
	 Usuario usr;
	 FormPost formPost;
	 PostService service;
	 
	 ArrayList<PostView> posts;
	
	public InicioView(Usuario usr){
		
		this.usr = usr;
		
		posts = new ArrayList<>();
		
		filter = new TextField();
		postsList = new Grid();
		crearPost = new Button("Crear Post");
		borrarPost = new Button("Borrar Post");
		service = new PostService();
		formPost = new FormPost(this);
		configureComponents();
        buildLayout();
	}
	
	public void insertUsuario(Usuario usr){
		
		this.usr = usr;
	}
	
	private void configureComponents() {

	    borrarPost.addClickListener(new ClickListener(){
	    		
	    	@Override
	    	public void buttonClick(ClickEvent event){
	    			
	    		service.suprPost(usr,(Post) postsList.getSelectedRow());   //borra un post si el usuario corresponde
	    		refreshPosts();
	    	}
	    });
	    	
	    crearPost.addClickListener(new ClickListener() {

	        @Override
	        public void buttonClick(ClickEvent event) {
	            formPost.hacerVisible();
	        }
	    });

	    filter.setInputPrompt("Filter posts...");
	    filter.addTextChangeListener(new TextChangeListener() {

	            @Override
	            public void textChange(TextChangeEvent event) {
	                refreshPosts(event.getText());
	            }
	        });

	        postsList.setContainerDataSource(new BeanItemContainer<Post>(
	                Post.class));
	        
	        for(Object s : postsList.getContainerDataSource().getItemIds()){
	        	
	        	System.out.println(s.toString());
	        }
	        
	        //ESTO SERVIRIA PARA LA OPCION DE ESITAR UN POST
//	        postsList.setSelectionMode(Grid.SelectionMode.SINGLE);
//	        postsList.addSelectionListener(new SelectionListener() {
	//
//	            @Override
//	            public void select(SelectionEvent event) {
//	            	
//	            	Post post = (Post) postsList.getSelectedRow();
//	    			
//	    			if(post.getUsr().equals(usr)){
//	    				postForm.publicar();
//	    			}
//	            }
//	        });
	        refreshPosts();
	    }

	    private void buildLayout() {
	    	
	        HorizontalLayout actions = new HorizontalLayout(filter, crearPost, borrarPost);
	        actions.setWidth("100%");
	        filter.setWidth("100%");
	        actions.setExpandRatio(filter, 1);

	        VerticalLayout left = new VerticalLayout(actions);
	       // postsList.setSizeFull();
	        //left.setExpandRatio(postList,1);
	        
	        for(PostView view : posts){
	        	
	        	System.out.println("creo los posts");
	        	
	        	left.addComponent(view);
	        }
	        
	        left.setSizeFull();
	        
	        this.addComponent(left);
	        this.addComponent(formPost);
	        this.setSizeFull();
	        this.setExpandRatio(left, 1);
	    }

	    void refreshPosts() {
	        refreshPosts(filter.getValue());
	    }

	    private void refreshPosts(String stringFilter) {
//	        postsList.setContainerDataSource(new BeanItemContainer<Post>(
//	                Post.class, service.findAll(stringFilter)));
	    	
	    	posts = new ArrayList<>();
	    	
	    	for(Post p : service.findAll(stringFilter)){
	    		
	    		PostView view = new PostView(p);
	    		posts.add(view);
	    		
	    		System.out.println("entra");
	    	}
	        formPost.setVisible(false);
	    }

	    
	    PostService getService(){
	    	return service;
	    }
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
