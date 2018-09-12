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
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ElementoProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.SinonimoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

@SpringView
@SpringComponent
public class TecnologiasView extends TecnologiasViewDesign implements View{
	

	@Autowired
    private Fachada fachada;
	
	private ElementoProyectoVO tecnologiaSelecionada;
	
	private CategoriaVO categoriaSeleccionada;
    
    private List<CategoriaVO> listaCategorias;
    
	public void enter(ViewChangeEvent event) 
	{
		form.setEnabled(true);
		formTecnologia.setEnabled(false);
		formSinonimos.setEnabled(false);
		grdSinonimos.setEnabled(false);
		this.cargarCategorias();
		cargarTodasTecnologias();
		
		cmbCategorias.addValueChangeListener(evt -> {
		    if (evt.getSource().isEmpty()) {
		    	
		    	cargarTodasTecnologias();
		    } 
		    else 
		    {
		    	grdSinonimos.setItems(new ArrayList<SinonimoVO>());
		    	this.cargarTecnologiasPorCategoria(evt.getValue());
		    	categoriaSeleccionada = evt.getValue();
		    }
		    
		    limpiarFormTecnologias();
		});
		
		grdTecnologias.addSelectionListener(evt -> 
		{
			grdSinonimos.setEnabled(true);
			SingleSelectionModel<ElementoProyectoVO> singleSelect = (SingleSelectionModel<ElementoProyectoVO>) grdTecnologias.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			try
			{
				tecnologiaSelecionada = singleSelect.getSelectedItem().get();
				cargarDatosTecnologiaSeleccionada(tecnologiaSelecionada);
				cargarSinonimosPorTecnologia(tecnologiaSelecionada);
				actualizarInterfazModificar();
			}
			catch (Exception e)
			{
				
			}
		});
		
		btnNuevaTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event) 
		    {
		    	limpiarFormTecnologias();
		    	cargarCategoriaTecnologias();
				btnAgregarTecnologia.setVisible(true);
				btnModificarTecnologia.setVisible(false);
		    	formTecnologia.setEnabled(true);
   	
		    }
		});
		
		btnAgregarTecnologia.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event) 
			{
				if (txtNombreTecnologia.isEmpty() || cmbCategoriasTecnologias.getValue() == null) 
				{
					Notification.show("Hay valores vacíos",Notification.Type.WARNING_MESSAGE);
				}
				else 
				{	
			    	try 
			    	{
			    		fachada.altaTecnologia(	txtNombreTecnologia.getValue(), cmbCategoriasTecnologias.getValue().getId());
			    	}
			    	catch (Exception e)
					{
			    						
					}

			    	actualizarCategorias();
			    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
			    	grdTecnologias.setEnabled(true);
			    	limpiarFormTecnologias();
				}
			}	

		});
		
		btnBorrarTecnologia.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	btnBorrarTecnologia.setCaption("Borrar");
		    	fachada.eliminarTecnologia(tecnologiaSelecionada.getId());	
		    	formTecnologia.setEnabled(false);
		    	actualizarCategorias();
		    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
		
		    }
		});	
	}
	
	private void cargarCategorias() 
	{
		if (listaCategorias == null)
		{
			this.actualizarCategorias();
		}
	}
	
	private void actualizarCategorias()
	{
		listaCategorias = new ArrayList<CategoriaVO>();
		listaCategorias.addAll(fachada.obtenerCategorias());
		cmbCategorias.setItems(listaCategorias);
		cmbCategorias.setItemCaptionGenerator(CategoriaVO::getNombre);
		this.ActualizarCategoriaSeleccionada();
	}
	
	private void ActualizarCategoriaSeleccionada()
	{
		if (this.categoriaSeleccionada != null && this.listaCategorias != null && !this.listaCategorias.isEmpty())
		{
			for(CategoriaVO cat: this.listaCategorias)
			{
				if (cat.getId() == this.categoriaSeleccionada.getId())
				{
					this.categoriaSeleccionada = cat;
					break;
				}
			}
		}
	}
	
	private void cargarTecnologiasPorCategoria(CategoriaVO categoria)
	{
		if(categoria != null)
		{
			grdTecnologias.setItems(categoria.getTecnologias());
		}
		else
		{
			this.cargarTodasTecnologias();
		}
	}	
	
	private void cargarTodasTecnologias()
	{
		if ( this.listaCategorias != null && !this.listaCategorias.isEmpty()) {

			List<ElementoProyectoVO> vRetorno = new ArrayList<ElementoProyectoVO>();
			for(CategoriaVO cat : this.listaCategorias)
			{
				vRetorno.addAll(cat.getTecnologias());
			}
			grdTecnologias.setItems(vRetorno);
		}

	}
	
	private void cargarSinonimosPorTecnologia(ElementoProyectoVO tecnologia)
	{
		
		grdSinonimos.setItems(tecnologia.getListaSinonimos());
	}
	
	private void cargarCategoriaTecnologias()
	{
		cmbCategoriasTecnologias.setItems(listaCategorias);
		cmbCategoriasTecnologias.setItemCaptionGenerator(CategoriaVO::getNombre);

	}
	
	private void actualizarInterfazModificar() 
	{
		formTecnologia.setEnabled(true);
		btnBorrarTecnologia.setVisible(true);

	}
	
	private void cargarDatosTecnologiaSeleccionada(ElementoProyectoVO tecnologiaSelecionada)
	{
		txtNombreTecnologia.setValue(tecnologiaSelecionada.getNombre());

	}
	
	private void limpiarFormTecnologias()
	{
	    txtNombreTecnologia.clear();
	    cmbCategoriasTecnologias.clear();
	    formTecnologia.setEnabled(false);
	}
	
}
