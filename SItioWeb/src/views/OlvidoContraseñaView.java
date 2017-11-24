package views;

import com.vaadin.navigator.View;


import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import daos.UsuarioDAO;
import domain.model.Usuario;
import services.EmailSenderService;

public class OlvidoContraseñaView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "OlvidoContraseña";
	TextField email;
	
	public OlvidoContraseñaView() {
		
		Panel panel = new Panel("Por favor, ingrese su dirección de email!");
		panel.setSizeUndefined();
		addComponent(panel);
		setSizeFull();
		addStyleName("fondoOlvido");
		
		FormLayout formLogin = new FormLayout();
		
		email = new TextField("email:");
		email.setIcon(FontAwesome.ENVELOPE);
		email.setRequired(true);
		formLogin.addComponent(email);
		
		Button bttEnviarMail = new Button("Enviar email");
		bttEnviarMail.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				
				Usuario usuario = UsuarioDAO.buscarPorEmail(email.getValue());
				String contraseña = usuario.getPassword();
				
				String destinatario [] = {email.getValue()};
				
				String mensaje = "Estimado "+ usuario.getUserName()+"," + " ha solicitado recuperar su contraseña vía email." + "\n" + "\n" 
				+ "Contraseña: " + contraseña + "\n" + "\n" + "Saludos, el equipo de SitioWebPosts" + "\n" + "\n" + "-----------------------"+ "\n" +
				"Por favor, no responda este mensaje.";
				
				boolean envioMail = EmailSenderService.sendEmail("sitiowebposts@gmail.com", "sitio123", mensaje, 
						destinatario);
				
				if(envioMail){
					Notification.show("Email enviado correctamente");
				}
			}
		});
		
		bttEnviarMail.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		Button bttVolver = new Button("Volver");
		bttVolver.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(LoginView.NAME);
				
			}
			
		});
		
		bttVolver.addStyleName(ValoTheme.BUTTON_FRIENDLY);
	
		Link outlookLink = new Link("Ir a Outlook", new ExternalResource("https://www.outlook.com"));
		outlookLink.setTargetName("_blank");
		//formLogin.addComponent(outlookLink);
		
		Link gmailLink = new Link("Ir a Gmail", new ExternalResource("https://www.gmail.com"));
		gmailLink.setTargetName("_blank");
		
		HorizontalLayout acciones = new HorizontalLayout(bttEnviarMail, bttVolver);
		acciones.setSpacing(true);
		formLogin.addComponent(acciones);
		
		VerticalLayout acciones2 = new VerticalLayout(outlookLink, gmailLink);
		acciones2.setSpacing(true);
		formLogin.addComponent(acciones2);
		
		formLogin.setSizeUndefined();
		formLogin.setMargin(true);
		panel.setContent(formLogin);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
