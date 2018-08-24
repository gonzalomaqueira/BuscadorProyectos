package uy.edu.ude.BuscadorProyectos;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import uy.edu.ude.BuscadorProyectos.navigation.NavigationManager;
import uy.edu.ude.BuscadorProyectos.ui.MainView;

@Theme("apptheme")
@SpringUI
@DesignRoot
 
public class BuscadorProyectosUI extends UI{
	
	private final SpringViewProvider viewProvider;
	
	private final NavigationManager navigationManager;

	private final MainView mainView;
	
	@Autowired
	public BuscadorProyectosUI(SpringViewProvider viewProvider, NavigationManager navigationManager, MainView mainView) {
		this.viewProvider = viewProvider;
		this.navigationManager = navigationManager;
		this.mainView = mainView;
	}

	@Override
	protected void init(VaadinRequest request) {
		
		setContent(mainView);

		navigationManager.navigateToDefaultView();
		
	}

}
