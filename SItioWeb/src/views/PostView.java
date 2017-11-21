package views;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import domain.model.Post;

public class PostView extends HorizontalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField contenido;
	TextField fecha;
	TextField usuario;
	VerticalLayout encuadre;
	HorizontalLayout encabezado;
	HorizontalLayout postContainer;
	HorizontalLayout botones;
	Post post;
	
	public PostView(Post p){
	
		post = p;
		
		contenido = new TextField(p.getContenido());
		
//		contenido.setWidth("300px");
//		contenido.setHeight("100px");
		fecha = new TextField(p.getFecha().toString());
//		fecha.setWidth("100px");
		usuario = new TextField(p.getUsuario().getUserName());
//		usuario.setWidth("100px");
		
		
		encabezado = new HorizontalLayout(usuario,fecha);
		postContainer = new HorizontalLayout(contenido);
		botones = new HorizontalLayout();
		
		encuadre = new VerticalLayout(encabezado,postContainer,botones);
		
		configureComponents();
	}
	

	private void configureComponents() {
		// TODO Auto-generated method stub
		
	}
}
