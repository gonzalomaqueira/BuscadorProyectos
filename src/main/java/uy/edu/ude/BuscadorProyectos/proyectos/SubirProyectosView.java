package uy.edu.ude.BuscadorProyectos.proyectos;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;


@SpringComponent
@SpringView
public class SubirProyectosView extends SubirProyectosDesign implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		// Nothing to do, just show the view
	}
	


}
