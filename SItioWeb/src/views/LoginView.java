package views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import daos.UsuarioDAO;
import domain.model.Usuario;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginView extends VerticalLayout implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "login";
	
	TextField username;
	PasswordField password;
	UsuarioDAO udao;
	
	
	@SuppressWarnings("serial")
	public LoginView()
	{
		udao = new UsuarioDAO();
		
		Panel panel = new Panel("Bienvenido!");
		panel.setSizeUndefined();
		addComponent(panel);
		setSizeFull();
		
		FormLayout formLogin = new FormLayout();
		
		username = new TextField("Usuario:");
		username.setIcon(FontAwesome.USER);
		username.setRequired(true);
		formLogin.addComponent(username);
		
		password = new PasswordField("Contraseña:");
		password.setIcon(FontAwesome.LOCK);
		password.setRequired(true);
		formLogin.addComponent(password);
		
		
		
		Button bttInicSesion = new Button("Iniciar Sesión");
		bttInicSesion.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				
				System.out.println("entro");
				Usuario nuevo = udao.buscar(username.getValue());
				
				if(nuevo!=null){
					
					if(nuevo.getPassword().equals(password.getValue())){
				
						getUI().getNavigator().addView(InicioView.NAME, new InicioView(nuevo));
						getUI().getNavigator().navigateTo(InicioView.NAME);
					}
					
					else System.out.println("Contraseña Incorrecta");
				}
				else System.out.println("Usuario incorrecto");
				
			}
			
		});	
		
		bttInicSesion.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		Button bttRegistrarse = new Button("No estoy registrado");
		bttRegistrarse.addClickListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().addView(RegistracionView.NAME, new RegistracionView());
				getUI().getNavigator().navigateTo(RegistracionView.NAME);
				
			}
			
		});	
		bttRegistrarse.addStyleName(ValoTheme.BUTTON_DANGER);
	
		
		formLogin.setSizeUndefined();
		formLogin.setMargin(true);
		panel.setContent(formLogin);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
		HorizontalLayout actions = new HorizontalLayout(bttInicSesion, bttRegistrarse);
		actions.setSpacing(true);
		formLogin.addComponent(actions);
		
		addStyleName("fondoPantalla");
		panel.addStyleName("fondoPanel");
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
