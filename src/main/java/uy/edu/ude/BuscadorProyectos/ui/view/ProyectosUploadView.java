package uy.edu.ude.BuscadorProyectos.ui.view;



import org.springframework.beans.factory.annotation.Autowired;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;

import com.vaadin.ui.Notification;

import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.Button.ClickEvent;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.navigation.NavigationManager;
import uy.edu.ude.BuscadorProyectos.service.Fachada;

import uy.edu.ude.BuscadorProyectos.utils.Constantes;
import uy.edu.ude.BuscadorProyectos.utils.ReceptorArchivos;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO;


@SpringView
@SpringComponent
public class ProyectosUploadView extends ProyectosUploadViewDesign implements View
{
	
    @Autowired
    private Fachada fachada;
    
    private String nombreArchivo;//="SPN-Carpeta Principal.pdf";
    private String prefijoArchivo;
    private ProyectoVO proyectoSeleccionado;
    
    private final NavigationManager navigationManager;
    
    @Autowired
    public ProyectosUploadView (NavigationManager navigationManager)
    {
    	this.navigationManager = navigationManager;
    }
    
	@Override
	public void enter(ViewChangeEvent event)
	{
		form.setEnabled(true);
		formContenido.setEnabled(false);		

		cargarListaProyectos();
		
		grdProyectos.addSelectionListener(evt -> 
		{
			SingleSelectionModel<ProyectoVO> singleSelect = (SingleSelectionModel<ProyectoVO>) grdProyectos.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			try
			{
				proyectoSeleccionado = singleSelect.getSelectedItem().get();
				actualizarInterfazModificar();
			}
			catch (Exception e)
			{				
			}
		});
		
		
		btnNuevo.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event)
		    {
		    	Date fecha = new Date();
		    	DateFormat formatoFecha = new SimpleDateFormat("yyyyMMddhhmmss_");
		    	prefijoArchivo = formatoFecha.format(fecha);
		    	
		    	ReceptorArchivos receptor = new ReceptorArchivos(Constantes.RUTA_ARCHIVOS, prefijoArchivo);
				updProyecto.setReceiver(receptor);
				updProyecto.setImmediateMode(false);
				updProyecto.setButtonCaption(null);
		    	
		    	updProyecto.setEnabled(true);
		    	btnAgregar.setCaption("Agregar");
		    	formContenido.setEnabled(true);
		    	btnBorrar.setVisible(false);
		    	
		    }
		});	
		
		btnAgregar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
	    		// Falta controlar que estén cargados los datos y el archivo a subir
	    		updProyecto.submitUpload();
			}
		});
		
		updProyecto.addFinishedListener(evt ->
		{
           nombreArchivo= evt.getFilename();           
           try 
	    	{	
               fachada.altaProyecto(txtNombre.getValue(), 
  					 Integer.parseInt(txtAnio.getValue()), 
  					 txtCarrera.getValue(), 
  					 Integer.parseInt(txtNota.getValue()), 
  					 Constantes.RUTA_ARCHIVOS + prefijoArchivo + nombreArchivo);
               
               Notification.show("Archivo subido exitosamente", Notification.Type.HUMANIZED_MESSAGE);           
	    	}
	    	catch (Exception e)
			{
	    		Notification.show("Hubo un error al subir el proyecto",Notification.Type.WARNING_MESSAGE);
	    		e.printStackTrace();
			}
	    	cargarListaProyectos();

			grdProyectos.setEnabled(true);
			cargarInterfazInicial();
			limpiarFormContenido();
           
           updProyecto.setEnabled(false);
        });
		
//		btnModificar.addClickListener(new Button.ClickListener()
//		{
//			public void buttonClick(ClickEvent event)
//			{						
//		    	try 
//		    	{
//		    		fachada.modificarProyecto(proyectoSeleccionado.getId(), txtNombre.getValue(), Integer.parseInt(txtAnio.getValue()), txtCarrera.getValue(), Integer.parseInt(txtNota.getValue()), Constantes.RUTA_ARCHIVOS + prefijoArchivo + nombreArchivo);			    		
//		    	}
//		    	catch (Exception e)
//				{
//		    		Notification.show("Hubo un error al subir el proyecto",Notification.Type.WARNING_MESSAGE);
//		    		e.printStackTrace();
//				}
//		    	cargarListaProyectos();
//
//				grdProyectos.setEnabled(true);
//				actualizarInterfazAgregar();
//				limpiarFormContenido();
//			}
//		});
		
		btnBorrar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
		    	try 
		    	{
		    		fachada.borrarProyecto(proyectoSeleccionado.getId());			    		
		    	}
		    	catch (Exception e)
				{
		    		Notification.show("Hubo un error al eliminar el proyecto",Notification.Type.WARNING_MESSAGE);
		    		e.printStackTrace();
				}
		    	cargarListaProyectos();

				grdProyectos.setEnabled(true);
				cargarInterfazInicial();
				limpiarFormContenido();
			}
		});							

		btnProyecto.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				navigationManager.navigateTo(ProyectoView.class , proyectoSeleccionado.getId());
			}
		});		
	}
	
	private void cargarInterfazInicial() 
	{
		formContenido.setEnabled(false);
		grdProyectos.setEnabled(true);
	}
	
	private void actualizarInterfazModificar()
	{
		formContenido.setEnabled(true);
		btnBorrar.setVisible(true);
		btnAgregar.setVisible(false);
		btnModificar.setVisible(true);
	}
	
	private void limpiarFormContenido()
	{
		txtNombre.clear();
		txtAnio.clear();
		txtCarrera.clear();
		txtNota.clear();		
	}
	
	private void cargarListaProyectos() 
	{
		grdProyectos.setItems(fachada.obtenerProyectos());
	}
}
