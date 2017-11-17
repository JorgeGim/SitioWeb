package views;

import java.util.Calendar;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


@Title("Addressbook")
@Theme("clase_vaadin")
public class SitioWebUI extends UI {
	
	LoginView log;
	Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
    	
    	log = new LoginView();
    	      
        navigator = new Navigator(this, this);
        navigator.addView(LoginView.NAME, log);
        navigator.navigateTo("login");
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = SitioWebUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    
}

