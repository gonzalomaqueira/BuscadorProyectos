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
import com.vaadin.ui.components.grid.SingleSelectionModel;

import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.TecnologiaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.SinonimoVO;


@SpringView
@SpringComponent
public class TecnologiasView extends TecnologiasViewDesign implements View{
	

	@Autowired
    private Fachada fachada;
	private TecnologiaVO tecnologiaSelecionada;
	private CategoriaVO categoriaSeleccionada;
    private List<CategoriaVO> listaCategorias;
    
	public void enter(ViewChangeEvent event) 
	{
		cargarInterfazInicial();
		this.cargarCategorias();
		cargarTodasTecnologias();
		
		cmbCategorias.addValueChangeListener(evt -> 
		{
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
			SingleSelectionModel<TecnologiaVO> singleSelect = (SingleSelectionModel<TecnologiaVO>) grdTecnologias.getSelectionModel();
			singleSelect.setDeselectAllowed(false);			
			try
			{
				tecnologiaSelecionada = singleSelect.getSelectedItem().get();
				cargarDatosTecnologiaSeleccionada(tecnologiaSelecionada);
				cargarSinonimosPorTecnologia(tecnologiaSelecionada);
				actualizarInterfazModificarTecnologia();
			}
			catch (Exception e)
			{				
			}
		});
		
		btnNuevaTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event) 
		    {
		    	grdTecnologias.setEnabled(false);
		    	grdSinonimos.setEnabled(false);
		    	limpiarFormTecnologias();
		    	cargarCategoriaTecnologias();
		    	actualizarInterfazAgregarTecnologia();
		    }
		});
		
		btnAgregarTecnologia.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				if (txtNombreTecnologia.isEmpty() || cmbCategoriasTecnologias.getValue() == null) 
				{
					Notification.show("Hay valores vacíos", Notification.Type.WARNING_MESSAGE);
				}
				else
				{	
			    	try 
			    	{
			    		fachada.altaTecnologia(txtNombreTecnologia.getValue(),
			    							   cmbCategoriasTecnologias.getValue().getId());
			    	}
			    	catch (Exception e)
					{
					}
			    	actualizarCategorias();
			    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
			    	cargarInterfazInicial();
				}
			}	
		});
		
		btnModificarTecnologia.addClickListener(new Button.ClickListener()
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
			    		fachada.modificarTecnologia(tecnologiaSelecionada.getId(),
			    									txtNombreTecnologia.getValue(),
			    									cmbCategoriasTecnologias.getValue().getId());
			    	}
			    	catch (Exception e)
					{
			    		e.printStackTrace();
					}
			    	actualizarCategorias();
			    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
			    	cargarInterfazInicial();
				}
			}	
		});
		
		
		btnBorrarTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event)
		    {
		    	fachada.eliminarTecnologia(tecnologiaSelecionada.getId());	
		    	formTecnologia.setEnabled(false);
		    	actualizarCategorias();
		    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
		
		    }
		});	
		
		btnCancelarTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event)
		    {		    	
		    	cargarInterfazInicial();		
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
		if ( this.listaCategorias != null && !this.listaCategorias.isEmpty()) 
		{

			List<TecnologiaVO> vRetorno = new ArrayList<TecnologiaVO>();
			for(CategoriaVO cat : this.listaCategorias)
			{
				vRetorno.addAll(cat.getTecnologias());
			}
			grdTecnologias.setItems(vRetorno);
		}

	}
	
	private void cargarSinonimosPorTecnologia(TecnologiaVO tecnologia)
	{
		
		grdSinonimos.setItems(tecnologia.getListaSinonimos());
	}
	
	private void cargarCategoriaTecnologias()
	{
		cmbCategoriasTecnologias.setItems(listaCategorias);
		cmbCategoriasTecnologias.setItemCaptionGenerator(CategoriaVO::getNombre);
	}
	
	private void actualizarInterfazAgregarTecnologia() 
	{
		formTecnologia.setEnabled(true);
		btnBorrarTecnologia.setVisible(false);
		btnAgregarTecnologia.setVisible(true);
		btnModificarTecnologia.setVisible(false);
	}
	
	private void actualizarInterfazModificarTecnologia() 
	{
		formTecnologia.setEnabled(true);
		btnBorrarTecnologia.setVisible(true);
		btnAgregarTecnologia.setVisible(false);
		btnModificarTecnologia.setVisible(true);
	}
	
	private void cargarDatosTecnologiaSeleccionada(TecnologiaVO tecnologia)
	{
		txtNombreTecnologia.setValue(tecnologia.getNombre());
		cmbCategoriasTecnologias.setItems(this.listaCategorias);
		cmbCategoriasTecnologias.setItemCaptionGenerator(CategoriaVO::getNombre);
		cmbCategoriasTecnologias.setValue(obtenerCategoriaPorId(tecnologia.getIdCategoria()));
	}
	
	private void limpiarFormTecnologias()
	{
	    txtNombreTecnologia.clear();
	    cmbCategoriasTecnologias.clear();
	    formTecnologia.setEnabled(false);
	}
	
	private CategoriaVO obtenerCategoriaPorId(int id)
	{
		CategoriaVO categoria= new CategoriaVO();
		if ( this.listaCategorias != null && !this.listaCategorias.isEmpty()) 
		{
			for(CategoriaVO cat : this.listaCategorias)
			{
				if ( cat.getId() == id)
				{
					categoria=cat;
					break;
				}	
			}
		} 
		return categoria;
	}
	
	private void cargarInterfazInicial() 
	{
		limpiarFormTecnologias();
		form.setEnabled(true);
		grdTecnologias.setEnabled(true);
		formTecnologia.setEnabled(false);
		formSinonimos.setEnabled(false);
		grdSinonimos.setEnabled(false);
	}
}
