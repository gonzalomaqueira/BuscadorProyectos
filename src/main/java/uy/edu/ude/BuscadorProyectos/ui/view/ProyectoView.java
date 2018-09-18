package uy.edu.ude.BuscadorProyectos.ui.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.navigation.NavigationManager;
import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;

@SpringView
@SpringComponent
public class ProyectoView extends ProyectoViewDesign implements View{
		
	@Autowired
	private Fachada fachada;
	
	private ProyectoDetalleVO proyecto;
	
    private final NavigationManager navigationManager;
    
    @Autowired
    public ProyectoView (NavigationManager navigationManager)
    {
    	this.navigationManager = navigationManager;
    }
	
	public void enter(ViewChangeEvent event) 
	{
		cargarInterfazInicial();
		String idProyecto = event.getParameters();
		if ("".equals(idProyecto))
		{
			cargarVistaVacia();
		}
		else
		{
			cargarVistaProyecto(Integer.parseInt(idProyecto));
		}	
		
		btnEditar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
				cargarInterfazModificar();
			}
		});
		
		btnCancelar.addClickListener(new Button.ClickListener()
		{
				public void buttonClick(ClickEvent event)
				{	
					cargarVistaProyecto(Integer.parseInt(idProyecto));
					cargarInterfazInicial();
				}
		});
		
		btnVolver.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{	
				navigationManager.navigateTo(ProyectosUploadView.class );
			}
		});
		
		btnGuardar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
				cargarDatosProyecto();
				try 
				{
					fachada.modificarProyectoCompleto( 	proyecto.getId(), 
														proyecto.getNombre(),
														proyecto.getAnio(),
														proyecto.getCarrera(),
														proyecto.getNota(),
														proyecto.getResumen(),
														proyecto.getAlumnos(),
														proyecto.getTutor());
					
				     Notification.show("Proyecto modificado exitosamente", Notification.Type.HUMANIZED_MESSAGE);           
		    	}
		    	catch (Exception e)
				{
		    		Notification.show("Hubo un error al modificar proyecto",Notification.Type.WARNING_MESSAGE);
		    		e.printStackTrace();
		    		cargarVistaProyecto(proyecto.getId());
				}
				cargarInterfazInicial();
			}
		});
	}

	private void cargarVistaVacia()
	{
		// ni idea...
	}	
	
	private void cargarVistaProyecto(int idProyecto)
	{
		this.proyecto = fachada.obtenerProyectoPorId(idProyecto);
		
		this.txtNombreProyecto.setValue(proyecto.getNombre());
		this.txtCarrera.setValue(proyecto.getCarrera());	
		this.txtNota.setValue(Integer.toString(proyecto.getNota()));
		this.txtAnio.setValue(Integer.toString(proyecto.getAnio()));
		this.txtTutor.setValue(FuncionesTexto.convertirArrayAStringSaltoLinea(proyecto.getTutor()));
		this.txtAlumnos.setValue(FuncionesTexto.convertirArrayAStringSaltoLinea(proyecto.getAlumnos()));
		this.txtResumen.setValue(proyecto.getResumen());
		permitirEdicion(false);
		
		this.grdTecnologias.setItems(proyecto.getTecnologia());
	}
	
	private void permitirEdicion(boolean opcion)
	{
		
		if(opcion)
		{
			this.txtNombreProyecto.setReadOnly(false);
			this.txtCarrera.setReadOnly(false);
			this.txtNota.setReadOnly(false);
			this.txtAnio.setReadOnly(false);
			this.txtTutor.setReadOnly(false);
			this.txtAlumnos.setReadOnly(false);
			this.txtResumen.setReadOnly(false);
		}
		else
		{
			this.txtNombreProyecto.setReadOnly(true);
			this.txtCarrera.setReadOnly(true);
			this.txtNota.setReadOnly(true);
			this.txtAnio.setReadOnly(true);
			this.txtTutor.setReadOnly(true);
			this.txtAlumnos.setReadOnly(true);
			this.txtResumen.setReadOnly(true);
		}	
	}
	
	private void cargarInterfazInicial()
	{
		permitirEdicion(false);
		btnEditar.setVisible(true);
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
	}
	
	private void cargarInterfazModificar()
	{
		permitirEdicion(true);
		btnCancelar.setVisible(true);
		btnEditar.setVisible(false);
		btnGuardar.setVisible(true);
	}
	
	private void cargarDatosProyecto()
	{
		proyecto.setNombre(txtNombreProyecto.getValue());
		proyecto.setCarrera(txtCarrera.getValue());
		proyecto.setNota( Integer.parseInt(txtNota.getValue()) );
		proyecto.setAnio( Integer.parseInt(txtAnio.getValue()) );
		proyecto.setTutor(FuncionesTexto.convertirStringAArrayList(txtTutor.getValue()));
		proyecto.setAlumnos(FuncionesTexto.convertirStringAArrayList(txtAlumnos.getValue()));
		proyecto.setResumen(txtResumen.getValue());
	}
	
	
	
}
