package uy.edu.ude.BuscadorProyectos.ui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;

import uy.edu.ude.BuscadorProyectos.service.Fachada;

@SpringView
@SpringComponent
public class ProyectoView extends ProyectoViewDesign implements View{
		
	@Autowired
	private Fachada fachada;
	
	public void enter(ViewChangeEvent event) 
	{
		String idProyecto = event.getParameters();
		if ("".equals(idProyecto))
		{
			cargarVistaVacia();
		}
		else
		{
			cargarVistaProyecto(Integer.parseInt(idProyecto));
		}
	}

	private void cargarVistaVacia() 
	{
		// ni idea...
	}
	
	private void cargarVistaProyecto(int idProyecto)
	{
		//Acá se cargan los datos del proyecto en la vista
	}
}
