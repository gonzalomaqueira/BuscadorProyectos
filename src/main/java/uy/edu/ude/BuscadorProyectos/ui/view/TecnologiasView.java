package uy.edu.ude.BuscadorProyectos.ui.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.SingleSelectionModel;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.service.CategoriaService;
import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.service.TecnologiaService;
import uy.edu.ude.BuscadorProyectos.service.UsuarioService;

@SpringView
@SpringComponent
public class TecnologiasView extends TecnologiasViewDesign implements View{
	
	@Autowired
    private CategoriaService categoriaService;
	
	@Autowired
    private TecnologiaService tecnologiaService;
	
	@Autowired
    private Fachada fachada;
	
    protected Categoria categoriaSeleccionada;
	
    protected Tecnologia tecnologiaSeleccionada;
    
    protected List<Categoria> listaCategorias;
    
	public void enter(ViewChangeEvent event) {
		
		txtCategoria.setVisible(false);
		gridcategoria.removeColumn("id");
		gridtecnologia.removeColumn("id");
		cargarListaCategorias();
		cargarListaTecnologias(); 
		gridcategoria.setSelectionMode(SelectionMode.SINGLE);
		gridtecnologia.setSelectionMode(SelectionMode.SINGLE);
		
		//Listeners
		gridcategoria.addSelectionListener(evento -> {
			SingleSelectionModel<Categoria> singleSelect = (SingleSelectionModel<Categoria>) gridcategoria.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			categoriaSeleccionada = new Categoria();
			try
			{
				categoriaSeleccionada = singleSelect.getSelectedItem().get();
				cargarDatosCategoriaSeleccionada(categoriaSeleccionada);
	
			}
			catch (Exception e)
			{
				System.out.println("No hay elemento seleccionado.");

				form.setEnabled(false);
			}
		});
		
		addCategoria.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	categoriaSeleccionada = null;
		    	limpiarForm();
		    	cmbCategoria.clear();
		    	cmbCategoria.setEnabled(true);
		    	cmbCategoria.setTextInputAllowed(true);
		    	cmbCategoria.setVisible(false);
		    	txtCategoria.setVisible(true);
		    	tecnologia.setVisible(false);
		    	update.setCaption("Agregar");
		    	form.setEnabled(true);
		    	gridcategoria.setEnabled(false);
		    	gridtecnologia.setEnabled(false);
		    	delete.setVisible(false);
		    }
		});
		
		addTecnologia.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tecnologiaSeleccionada = null;
		    	limpiarForm();
		    	cmbCategoria.setEnabled(true);
		    	tecnologia.setVisible(true);
		    	tecnologia.setEnabled(true);
		    	update.setCaption("Agregar");
		    	form.setEnabled(true);
		    	gridcategoria.setEnabled(false);
		    	gridtecnologia.setEnabled(false);
		    	delete.setVisible(false);
		    	cargarCategorias();
		    }
		});
		
		update.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
			
				if (cmbCategoria.getValue().toString().isEmpty()) {
					Notification.show("Ingrese una categoria",Notification.Type.WARNING_MESSAGE);
				}
					else {
						
					    	if (update.getCaption() == "Agregar")
					    	{
					    		
							    	try {
						    			categoriaService.add(new Categoria(cmbCategoria.getValue().toString(),null));
							    	}
							    	catch (Exception e)
									{
							    		Notification.show("Ya existe la Categoria",Notification.Type.WARNING_MESSAGE);
				
									}
							    	gridcategoria.setItems(categoriaService.listCategorias());
					    	}
					    	else
					    	{
					    		actualizarCategoria();
					    		categoriaService.modify(categoriaSeleccionada);
					    		gridcategoria.getDataProvider().refreshAll();		    		
					    	}			
							//context.close();
			
					    	gridcategoria.setEnabled(true);
							actualizarInterfazAgregar();
							limpiarForm();
						}
	
			    }
	
		});
		
	}

	
	
	
	private void cargarListaCategorias() 
	{
		gridcategoria.setItems(categoriaService.listCategorias());
	}
	
	
	private void cargarListaTecnologias() 
	{
		gridtecnologia.setItems(fachada.obtenerTecnologias());
	}
	
	private void cargarDatosCategoriaSeleccionada(Categoria categoriaSeleccionada)
	{

	}
	
	private void cargarCategorias() 
	{
		if (listaCategorias == null)
		{
			listaCategorias = new ArrayList<Categoria>();
			listaCategorias.addAll(categoriaService.listCategorias());
			cmbCategoria.setItems(listaCategorias);
			cmbCategoria.setItemCaptionGenerator(Categoria::getNombre);
		}
	}
	
	
	private void limpiarForm()
	{
		cmbCategoria.clear();
		tecnologia.clear();
		
	}
	
	private void actualizarCategoria() 
	{
		if (categoriaSeleccionada != null)
		{
			categoriaSeleccionada.setNombre(cmbCategoria.getValue().toString());
		}
	}	
	
	private void actualizarInterfazAgregar() 
	{
		update.setCaption("Agregar");
		form.setEnabled(false);
		gridcategoria.setEnabled(true);
	}
	
}
