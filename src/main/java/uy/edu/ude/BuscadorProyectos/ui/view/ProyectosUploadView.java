package uy.edu.ude.BuscadorProyectos.ui.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Button.ClickEvent;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.service.ProyectoService;
import uy.edu.ude.BuscadorProyectos.utils.ReceptorArchivos;

@SpringView
@SpringComponent
public class ProyectosUploadView extends ProyectosUploadViewDesign implements View{
	
    @Autowired
    private ProyectoService proyectoService;
    
    private String nombreArchivo;
    private String prefijoArchivo;

    
	@Override
	public void enter(ViewChangeEvent event)
	{
		cargarListaProyectos();
		add.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	update.setCaption("Agregar");
		    	form.setEnabled(true);
		    	delete.setVisible(false);
		    	updProyecto.setEnabled(true);
		    	
		    	Date fecha = new Date();
		    	DateFormat formatoFecha = new SimpleDateFormat("yyyyMMddhhmmss_");
		    	prefijoArchivo = formatoFecha.format(fecha);
		    	
		    	ReceptorArchivos receptor = new ReceptorArchivos("/tmp/", prefijoArchivo);
				updProyecto.setReceiver(receptor);
		    }
		});	
		
		update.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event) {
				
			    	if (update.getCaption() == "Agregar")
			    	{			    		
				    	try 
				    	{				    		
				    		proyectoService.add(new Proyecto(nombre.getValue(), Integer.parseInt(anio.getValue()), carrera.getValue(), Integer.parseInt(nota.getValue()), "/tmp/" + prefijoArchivo + nombreArchivo));				    		
				    	}
				    	catch (Exception e)
						{
				    		Notification.show("Ya existe el proyecto",Notification.Type.WARNING_MESSAGE);
				    		e.printStackTrace();
						}
				    	cargarListaProyectos();
			    	}
			    	else
			    	{
			    		
			    	}			
	
					list.setEnabled(true);
					actualizarInterfazAgregar();
					limpiarForm();
				}
		});
					
		updProyecto.setImmediateMode(true);
		
		updProyecto.addFinishedListener(event1 -> {
           nombreArchivo= event1.getFilename();
           Notification.show("Archivo subido exitosamente", Notification.Type.HUMANIZED_MESSAGE);
           updProyecto.setEnabled(false);
        });

	}
	
	
	private void actualizarInterfazAgregar() 
	{
		update.setCaption("Agregar");
		form.setEnabled(false);
		list.setEnabled(true);
	}
	
	private void limpiarForm()
	{
		nombre.clear();
		anio.clear();
		carrera.clear();
		nota.clear();		
	}
	
	private void cargarListaProyectos() 
	{
		list.setItems(proyectoService.listarProyectos());
	}
	
}
