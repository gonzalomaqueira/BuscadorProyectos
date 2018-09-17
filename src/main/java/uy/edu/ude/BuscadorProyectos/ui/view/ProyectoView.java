package uy.edu.ude.BuscadorProyectos.ui.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;

@SpringView
@SpringComponent
public class ProyectoView extends ProyectoViewDesign implements View{
		
	@Autowired
	private Fachada fachada;
	
	private ProyectoDetalleVO proyecto;
	
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
		fachada.ProcesarProyecto(idProyecto); // cambiar
		this.proyecto=fachada.obtenerProyectoPorId(idProyecto);
		
		this.txtResumen.setValue(proyecto.getResumen());
		this.grdTecnologias.setItems(proyecto.getTecnologia());
	}
}
