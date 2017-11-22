package views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import domain.model.Post;

public class PostView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Post post;
	
	Label contenido;
	Label fecha;
	Label usuario;
	
	public Panel principal;
	
	VerticalLayout encuadre;
	HorizontalLayout encabezado;
	HorizontalLayout postContainer;
	HorizontalLayout botones;
	
	public PostView(Post p){
	
		post = p;
		
		contenido = new Label(p.getContenido());
		
		fecha = new Label(p.getFecha().toString());

		usuario = new Label(p.getUsuario().getUserName());
		
		encabezado = new HorizontalLayout(usuario,fecha);
		encabezado.setSizeFull();
		encabezado.setComponentAlignment(fecha, Alignment.TOP_RIGHT);
		
		Panel panelEncabezado = new Panel(encabezado);
		panelEncabezado.setWidth("100%");
		
		postContainer = new HorizontalLayout(contenido);
		postContainer.setSizeFull();
		postContainer.setComponentAlignment(contenido, Alignment.MIDDLE_CENTER);
		
		Panel panelContenido = new Panel(postContainer);
		panelContenido.setWidth("100%");
		
		botones = new HorizontalLayout();
		botones.setSizeFull();
		
		Panel panelBotones = new Panel(botones);
		panelBotones.setWidth("100%");
		
		encuadre = new VerticalLayout(panelEncabezado,panelContenido,panelBotones);
		encuadre.setSizeFull();
		
		principal = new Panel(encuadre);
		principal.setWidth("100%");
		
		configureComponents();
	}
	

	private void configureComponents() {
		// TODO Auto-generated method stub
		
	}
}
