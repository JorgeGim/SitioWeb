package views;

import com.vaadin.navigator.View;


import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
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
		
		FormLayout formLogin = new FormLayout();
		
		email = new TextField("e-mail:");
		email.setIcon(FontAwesome.ENVELOPE);
		email.setRequired(true);
		formLogin.addComponent(email);
		
		Button enviarMail = new Button("Enviar email");
		enviarMail.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				
				String destinatario [] = {"jorgehgimenez.1996@gmail.com"};
				boolean envioMail = EmailSenderService.sendEmail("jorgehgimenez.1996@gmail.com", "jo020396", "prueba", 
						destinatario);
				
				if(envioMail)
					Notification.show("Email enviado correctamente");
			}
			
		});
		
		enviarMail.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		formLogin.addComponent(email);
		formLogin.addComponent(enviarMail);
		
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
