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
		});
		
		grdTecnologias.addSelectionListener(evt -> 
		{
			grdSinonimos.setEnabled(true);
			SingleSelectionModel<ElementoProyectoVO> singleSelect = (SingleSelectionModel<ElementoProyectoVO>) grdTecnologias.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			tecnologiaSelecionada = new ElementoProyectoVO();
			try
			{
				tecnologiaSelecionada = singleSelect.getSelectedItem().get();
				cargarSinonimosPorTecnologia(tecnologiaSelecionada);
			}
			catch (Exception e)
			{
				grdSinonimos.setEnabled(false);
			}
		});
		
		btnNuevaTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event) 
		    {
		    	cargarCategoriaTecnologias();
				btnAgregar.setVisible(true);
				btnModificar.setVisible(false);
		    	form.setEnabled(true);
   	
		    }
		});
		
		btnAgregar.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event) 
			{
				if (txtNombreTecnologia.isEmpty()) 
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
			    		e.printStackTrace();
			    		Notification.show("Ocurrió un error",Notification.Type.WARNING_MESSAGE);				
					}

			    	actualizarCategorias();
			    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
			    	grdTecnologias.setEnabled(true);
			    	txtNombreTecnologia.clear();
			    	form.setEnabled(false);
						    	
				}
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
		grdTecnologias.setItems(categoria.getTecnologias());
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

	
	
}
