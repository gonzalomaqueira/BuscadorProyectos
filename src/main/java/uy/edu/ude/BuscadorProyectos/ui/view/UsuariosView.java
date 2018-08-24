package uy.edu.ude.BuscadorProyectos.ui.view;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.SingleSelectionModel;

import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.ValueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.ValueObjects.UsuarioVO;

@SpringView
@SpringComponent
public class UsuariosView extends UsuariosViewDesign implements View {

	@Autowired
    private Fachada fachada;

    protected UsuarioVO usuarioSeleccionado;
    protected List<PerfilVO> listaPerfiles;

	@Override
	public void enter(ViewChangeEvent event)
	{
		grdUsuarios.addColumn(usuario -> usuario.getPerfil().getDescripcion()).setCaption("Perfil");
					
		this.cargarListaUsuarios();
		this.form.setEnabled(false);
		this.cargarPerfiles();
				
		grdUsuarios.setSelectionMode(SelectionMode.SINGLE);
		
		//Listeners
		grdUsuarios.addSelectionListener(evento -> 
		{
			btnAgregar.setVisible(false);
			btnModificar.setVisible(true);
			SingleSelectionModel<UsuarioVO> singleSelect = (SingleSelectionModel<UsuarioVO>) grdUsuarios.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			usuarioSeleccionado = new UsuarioVO();
			try
			{
				usuarioSeleccionado = singleSelect.getSelectedItem().get();
				cargarDatosUsuarioSeleccionado(usuarioSeleccionado);
				actualizarInterfazModificar();
			}
			catch (Exception e)
			{
				System.out.println("No hay elemento seleccionado.");
				limpiarForm();
				form.setEnabled(false);
			}
		});
		
		btnNuevo.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event) 
		    {
				btnAgregar.setVisible(true);
				btnModificar.setVisible(false);
		    	usuarioSeleccionado = null;
		    	limpiarForm();
		    	usuario.setEnabled(true);
		    	form.setEnabled(true);
		    	grdUsuarios.setEnabled(false);
		    	btnBorrar.setVisible(false);
		    	cargarPerfiles();		    	
		    }
		});
		
		btnAgregar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event) 
			{
				if (usuario.isEmpty() || contrasenia.isEmpty() || contrasenia2.isEmpty() || nombre.isEmpty() || apellido.isEmpty() 
						|| email.isEmpty() || cmbPerfil.isEmpty() ) 
				{
					Notification.show("Hay valores vacíos",Notification.Type.WARNING_MESSAGE);
				}
				else 
				{	
					if (!(contrasenia.getValue().equals((contrasenia2.getValue())))) 
					{
							Notification.show("Las contraseñas no coinciden",Notification.Type.WARNING_MESSAGE);
					}
					else 
					{		    		
				    	try 
				    	{
				    		fachada.altaUsuario(usuario.getValue(),
				    							contrasenia.getValue(),
				    							nombre.getValue(),
				    							apellido.getValue(),
				    							email.getValue(),
				    							cmbPerfil.getSelectedItem().get());						    			
				    	}
				    	catch (Exception e)
						{
				    		Notification.show("Ya existe el usuario",Notification.Type.WARNING_MESSAGE);				
						}
				    	
				    	cmbPerfil.getValue();					
				    	cargarListaUsuarios();
				    	grdUsuarios.setEnabled(true);
						actualizarInterfazAgregar();
						limpiarForm();
						    	
				    }
				}	
			}
		});
		
		btnModificar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event) 
			{
				if (usuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || cmbPerfil.isEmpty() ) 
				{
					Notification.show("Hay valores vacíos",Notification.Type.WARNING_MESSAGE);
				}
					else 
					{
						if (!(contrasenia.getValue().equals((contrasenia2.getValue())))) 
						{
							Notification.show("Las contraseñas no coinciden",Notification.Type.WARNING_MESSAGE);
						}
						else 
						{	    		
					    	try 
					    	{
					    		fachada.modificarUsuario(usuarioSeleccionado.getId(), 
	    								 usuario.getValue(), 
	    								 contrasenia.getValue(), 
	    								 nombre.getValue(),
	    								 apellido.getValue(),
	    								 email.getValue(),
	    								 cmbPerfil.getValue());						    			
					    	}
					    	catch (Exception e)
							{
					    		Notification.show("Ha ocurrido un error",Notification.Type.WARNING_MESSAGE);				
							}
							btnAgregar.setVisible(true);
							btnModificar.setVisible(false);
					    	cargarListaUsuarios();
					    	grdUsuarios.setEnabled(true);
							actualizarInterfazAgregar();
							limpiarForm();    	
				    	}
					}	
			  }

		});
		
		btnCancelar.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	limpiarForm();
		    	form.setEnabled(false);		    	
		    	grdUsuarios.setEnabled(true);	    	
		    }
		});		

		btnBorrar.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	btnBorrar.setCaption("Borrar");
		    	fachada.eliminarUsuario(usuarioSeleccionado.getId()); 	
		    	grdUsuarios.setEnabled(true);
				actualizarInterfazAgregar();
				limpiarForm();
				grdUsuarios.setItems(fachada.listarUsuarios());				
		    }
		});	
		
 	}
	
	private void actualizarInterfazAgregar() 
	{
		form.setEnabled(false);
		grdUsuarios.setEnabled(true);
	}
	
	private void actualizarInterfazModificar() 
	{
		form.setEnabled(true);
		usuario.setEnabled(false);
		btnBorrar.setVisible(true);
		cargarPerfiles();
	}
	
	private void cargarDatosUsuarioSeleccionado(UsuarioVO usuarioSeleccionado)
	{
		usuario.setValue(usuarioSeleccionado.getUsuario());
		nombre.setValue(usuarioSeleccionado.getNombre());
		apellido.setValue(usuarioSeleccionado.getApellido());
		email.setValue(usuarioSeleccionado.getEmail());
		cmbPerfil.setValue(usuarioSeleccionado.getPerfil());
	}
	
	private void limpiarForm()
	{
		usuario.clear();
		contrasenia.clear();
		contrasenia2.clear();
		nombre.clear();
		apellido.clear();
		email.clear();
		cmbPerfil.clear();
		
	}
	
	private void cargarPerfiles() 
	{
		if (listaPerfiles == null)
		{
			listaPerfiles = new ArrayList<PerfilVO>();
			listaPerfiles.addAll(fachada.listarPerfiles());
			cmbPerfil.setItems(listaPerfiles);
			cmbPerfil.setItemCaptionGenerator(PerfilVO::getDescripcion);
		}
	}

	private void cargarListaUsuarios() 
	{
		grdUsuarios.setItems(fachada.listarUsuarios());
	}
}
