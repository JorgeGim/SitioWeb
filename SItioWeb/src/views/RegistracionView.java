package views;

import services.UsuarioService;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import daos.UsuarioDAO;
import domain.model.Usuario;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */
public class RegistracionView extends VerticalLayout implements View{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Registracion";
//	UsuarioDAO udao;
	TextField username;
	TextField email;
	PasswordField password;
	TextField cuil;

    @SuppressWarnings("serial")
	public RegistracionView() {
        
//    	udao = new UsuarioDAO();
    	
    	Panel panel = new Panel("Bienvenido!, por favor complete los siguientes datos");
		panel.setSizeUndefined();
		addComponent(panel);
		setSizeFull();
		
		FormLayout formLogin = new FormLayout();
		
		username = new TextField("Ingrese un nombre de usuario:");
		username.setIcon(FontAwesome.USER);
		username.setRequired(true);
		formLogin.addComponent(username);
		
		password = new PasswordField("Ingrese una contraseña:");
		password.setIcon(FontAwesome.LOCK);
		password.setRequired(true);
		formLogin.addComponent(password);
		
		email = new TextField("Ingrese su email:");
		email.setIcon(FontAwesome.ENVELOPE);
		email.setRequired(true);
		formLogin.addComponent(email);
		
		cuil = new TextField("Ingrese su CUIL:");
		cuil.setIcon(FontAwesome.SORT_NUMERIC_DESC);
		cuil.setRequired(true);
		formLogin.addComponent(cuil);
		
		TextField facebook = new TextField("Conectar con Facebook:");
		facebook.setIcon(FontAwesome.FACEBOOK);
		formLogin.addComponent(facebook);
		
		Button bttRegistrarse = new Button("Registrarse");
		bttRegistrarse.addClickListener(new ClickListener(){
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				Usuario nuevoUsuario = UsuarioService.crearUsuario(cuil.getValue(), username.getValue(), email.getValue(), password.getValue());
				UsuarioService.save(nuevoUsuario);
				
//				Usuario nuevo = new Usuario();
//				nuevo.setUserName(username.getValue());
//				nuevo.setE_mail(email.getValue());
//				nuevo.setPassword(password.getValue());
//				nuevo.setCUIT_CUIL(cuil.getValue());
				
//				udao.guardar(nuevo);
				
				String notificacion = "Registrado con éxito!";
                Notification.show(notificacion);
				getUI().getNavigator().navigateTo(LoginView.NAME);
			}
			
		});
		
		bttRegistrarse.addStyleName(ValoTheme.BUTTON_PRIMARY);
		formLogin.addComponent(bttRegistrarse);
		
		formLogin.setSizeUndefined();
		formLogin.setMargin(true);
		panel.setContent(formLogin);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		
		addStyleName("fondoRegistracion");
    }

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}