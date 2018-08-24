package uy.edu.ude.BuscadorProyectos.ui.view;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;


@SpringComponent
@SpringView
public class AccessDeniedView extends AccessDeniedDesign implements View{

	
	@Override
	public void enter(ViewChangeEvent event) {
		// Nothing to do, just show the view
		
		
	}
}
