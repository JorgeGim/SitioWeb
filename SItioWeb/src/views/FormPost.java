package views;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.themes.ValoTheme;

import daos.PostDAO;
import domain.model.Post;
import domain.model.Usuario;
import services.PostService;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */
@SuppressWarnings("serial")
public class FormPost extends FormLayout implements ClickListener {

    Button publicar = new Button("Publicar", this);
    Button cancelar = new Button("Cancelar", this);
    TextArea contenido = new TextArea("Contenido:");
    InicioView ui;
    PostService service;
    Usuario usr;

    BeanFieldGroup<Post> formFieldBindings;

    public FormPost(InicioView ui) {
    	
    	this.ui = ui;
    	service = ui.service;
    	usr = ui.usr;
        configureComponents();
        buildLayout();
        
        configPost();
    }

	private void configPost() {
		contenido.setMaxLength(40);
        final Label contCaracteres = new Label();
        contCaracteres.setValue(contenido.getValue().length() + " de " + contenido.getMaxLength());
        
        contenido.addTextChangeListener(new TextChangeListener(){

			@Override
			public void textChange(TextChangeEvent event) {
				
				int len = event.getText().length();
				contCaracteres.setValue(len + " de " + contenido.getMaxLength());
				
			}
        	
        });
        
        contenido.setTextChangeEventMode(TextChangeEventMode.LAZY);
        
        addComponent(contCaracteres);
	}

    private void configureComponents() {

        publicar.setStyleName(ValoTheme.BUTTON_PRIMARY);
        publicar.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(publicar, cancelar);
        actions.setSpacing(true);

        
        addComponents(contenido, actions);
    }

    void hacerVisible() {
        setVisible(true);
    }

    @Override
    public SitioWebUI getUI() {
        return (SitioWebUI) super.getUI();
    }

    @Override
    public void buttonClick(ClickEvent event) {
        if (event.getButton() == publicar) {
        	
                service.createPost(usr,contenido.getValue());

                String msg = "Posteado!";
                Notification.show(msg, Type.TRAY_NOTIFICATION);
                ui.refreshPosts();

        } else if (event.getButton() == cancelar) {
        	
            setVisible(false);
        }

    }

}