package uy.edu.ude.BuscadorProyectos.ui.view;



import org.springframework.beans.factory.annotation.Autowired;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;

import com.vaadin.ui.Notification;

import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.Button.ClickEvent;

import uy.edu.ude.BuscadorProyectos.entity.Enumerados;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.navigation.NavigationManager;
import uy.edu.ude.BuscadorProyectos.service.Fachada;

import uy.edu.ude.BuscadorProyectos.utils.Constantes;
import uy.edu.ude.BuscadorProyectos.utils.ReceptorArchivos;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.TecnologiaVO;


@SpringView
@SpringComponent
public class ProyectosUploadView extends ProyectosUploadViewDesign implements View
{
	
    @Autowired
    private Fachada fachada;
    
    private String nombreArchivo;
    private String prefijoArchivo;
    private ProyectoVO proyectoSeleccionado;
    private List<ProyectoVO> listaProyectos;
    
    private final NavigationManager navigationManager;
    
    @Autowired
    public ProyectosUploadView (NavigationManager navigationManager)
    {
    	this.navigationManager = navigationManager;
    }
    
	@Override
	public void enter(ViewChangeEvent event)
	{
		cargarListaProyectos();
		cargarInterfazInicial();
		
		grdProyectos.addSelectionListener(evt -> 
		{
			SingleSelectionModel<ProyectoVO> singleSelect = (SingleSelectionModel<ProyectoVO>) grdProyectos.getSelectionModel();
			singleSelect.setDeselectAllowed(false);
			try
			{
				if (singleSelect.getSelectedItem() != null)
				{
					proyectoSeleccionado = singleSelect.getSelectedItem().get();
					cargarDatosProyectoSeleccionado();
				}
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
				
		    	actualizarInterfazNuevoProyecto(); 	
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
	    		Notification.show("Hubo un error al subir el proyecto", Notification.Type.WARNING_MESSAGE);
	    		e.printStackTrace();
			}
            actualizarProyectos();

			grdProyectos.setEnabled(true);
			cargarInterfazInicial();
			limpiarFormContenido();
           
           updProyecto.setEnabled(false);
        });
		
		btnModificar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
		    	try 
		    	{
		    		fachada.modificarProyecto(	proyectoSeleccionado.getId(),
							    				 txtNombre.getValue(), 
							  					 Integer.parseInt(txtAnio.getValue()), 
							  					 txtCarrera.getValue(), 
							  					 Integer.parseInt(txtNota.getValue()), 
							  					 Constantes.RUTA_ARCHIVOS + prefijoArchivo + nombreArchivo);
		    		
		    		Notification.show("Archivo modificado exitosamente", Notification.Type.HUMANIZED_MESSAGE); 
		    	}
		    	catch (Exception e)
				{
		    		Notification.show("Hubo un error al modificar el proyecto",Notification.Type.WARNING_MESSAGE);
		    		e.printStackTrace();
				}
		    	

		    	actualizarProyectos();
				grdProyectos.setEnabled(true);
				cargarInterfazInicial();
			}
		});
		
		btnBorrar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
		    	try 
		    	{
		    		fachada.borrarProyecto(proyectoSeleccionado.getId());
		    		Notification.show("Proyecto eliminado exitosamente", Notification.Type.HUMANIZED_MESSAGE);
		    	}
		    	catch (Exception e)
				{
		    		Notification.show("Hubo un error al eliminar el proyecto",Notification.Type.WARNING_MESSAGE);
		    		e.printStackTrace();
				}
		    	
				actualizarProyectos();
				grdProyectos.setEnabled(true);
				cargarInterfazInicial();
			}
		});
		
		btnCancelar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{						
		    	cargarInterfazInicial();
			}
		});		

		btnVerDetalle.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{			
				navigationManager.navigateTo(ProyectoView.class , proyectoSeleccionado.getId());
			}
		});
		
		btnProcesar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				try
		    	{
					fachada.ProcesarProyecto(proyectoSeleccionado.getId());
		    		Notification.show("Proyecto procesado exitosamente", Notification.Type.HUMANIZED_MESSAGE);
		    	}
		    	catch (Exception e)
				{
		    		Notification.show("Hubo un error al procesar el proyecto", Notification.Type.WARNING_MESSAGE);
		    		e.printStackTrace();
				}
				actualizarProyectos();
				grdProyectos.setEnabled(true);
				actualizarBotonesProcesamiento();
			}
		});		
	}
	
	private void cargarDatosProyectoSeleccionado() 
	{
		this.txtNombre.setValue(proyectoSeleccionado.getNombre());
		this.txtAnio.setValue(Integer.toString(proyectoSeleccionado.getAnio()));
		this.txtCarrera.setValue(proyectoSeleccionado.getCarrera());
		this.txtNota.setValue(Integer.toString(proyectoSeleccionado.getNota()));
	}

	private void cargarInterfazInicial() 
	{
		formContenido.setEnabled(false);
		grdProyectos.setEnabled(true);
		limpiarFormContenido();
	}
	
	private void actualizarInterfazNuevoProyecto() 
	{
		limpiarFormContenido();
		grdProyectos.deselectAll();
		grdProyectos.setEnabled(false);
		updProyecto.setEnabled(true);
    	btnAgregar.setVisible(true);
    	btnModificar.setVisible(false);
    	formContenido.setEnabled(true);
    	btnBorrar.setVisible(false);	    	
    	btnProcesar.setVisible(false);
    	btnVerDetalle.setVisible(false);
	}
	
	private void actualizarInterfazModificar()
	{
		formContenido.setEnabled(true);
		btnBorrar.setVisible(true);
		btnAgregar.setVisible(false);
		btnModificar.setVisible(true);
    	btnProcesar.setVisible(true);
    	btnVerDetalle.setVisible(true);
		
		actualizarBotonesProcesamiento();
	}
	
	private void actualizarBotonesProcesamiento()
	{
		if (proyectoSeleccionado != null)
		{
			if (proyectoSeleccionado.getEstado() == Enumerados.EstadoProyectoEnum.SIN_PROCESAR)
			{
				btnProcesar.setCaption("PROCESAR");
				btnVerDetalle.setEnabled(false);
			}
			else
			{
				btnProcesar.setCaption("RE-PROCESAR");
				btnVerDetalle.setEnabled(true);
			}
		}
		else
		{
			btnProcesar.setEnabled(false);
		}	
	}	
	
	private void limpiarFormContenido()
	{
		updProyecto.setEnabled(false);
		txtNombre.clear();
		txtAnio.clear();
		txtCarrera.clear();
		txtNota.clear();		
	}
	
	private void cargarListaProyectos() 
	{
		if (listaProyectos == null)
		{
			this.actualizarProyectos();
		}
	}
	
	private void actualizarProyectos()
	{
		listaProyectos = new ArrayList<ProyectoVO>();
		listaProyectos.addAll(fachada.obtenerProyectos());
		grdProyectos.setItems(listaProyectos);
		this.actualizarProyectoSeleccionado();
	}	
	
	private void actualizarProyectoSeleccionado()
	{
		if (this.proyectoSeleccionado != null && this.listaProyectos != null && !this.listaProyectos.isEmpty())
		{
			for(ProyectoVO proy: this.listaProyectos)
			{
				if (proy.getId() == this.proyectoSeleccionado.getId())
				{
					this.proyectoSeleccionado = proy;
					break;
				}
			}
		}
	}
}
