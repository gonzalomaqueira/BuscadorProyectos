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
	private TecnologiaVO tecnologiaSeleccionada;
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
				tecnologiaSeleccionada = singleSelect.getSelectedItem().get();
				cargarDatosTecnologiaSeleccionada(tecnologiaSeleccionada);
				cargarSinonimosPorTecnologia(tecnologiaSeleccionada);
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
		    	limpiarFormSinonimos();
		    	formSinonimos.setEnabled(false);
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
			    		fachada.modificarTecnologia(tecnologiaSeleccionada.getId(),
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
		    	fachada.eliminarTecnologia(tecnologiaSeleccionada.getId());	
		    	formTecnologia.setEnabled(false);
		    	actualizarCategorias();
		    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
		    	grdSinonimos.setItems(new ArrayList<SinonimoVO>());
		
		    }
		});	
		
		btnCancelarTecnologia.addClickListener(new Button.ClickListener()
		{
		    public void buttonClick(ClickEvent event)
		    {		    	
		    	cargarInterfazInicial();		
		    }
		});
		
		
		btnAgregarSinonimo.addClickListener(new Button.ClickListener()
		{
			public void buttonClick(ClickEvent event)
			{
				if (txtNombreSinonimo.isEmpty()) 
				{
					Notification.show("Hay valores vacíos", Notification.Type.WARNING_MESSAGE);
				}
				else
				{
			    	try
			    	{
			    		fachada.altaSinonimoTecnologia(txtNombreSinonimo.getValue(), tecnologiaSeleccionada.getId());
			    	}
			    	catch (Exception e)
					{
					}
			    	actualizarCategorias();
			    	cargarTecnologiasPorCategoria(categoriaSeleccionada);
			    	actualizarTecnologiaSeleccionada();
			    	cargarSinonimosPorTecnologia(tecnologiaSeleccionada);
			    	cargarInterfazInicial();
			    	grdSinonimos.setEnabled(true);	    	
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
		this.actualizarCategoriaSeleccionada();
	}
	
	private void actualizarCategoriaSeleccionada()
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
	
	private void actualizarTecnologiaSeleccionada()
	{
		CategoriaVO categoriaActualizada = this.obtenerCategoriaPorId(tecnologiaSeleccionada.getIdCategoria());
		
		if (categoriaActualizada != null)
		{
			for(TecnologiaVO tec: categoriaActualizada.getTecnologias())
			{
				if (tec.getId() == this.tecnologiaSeleccionada.getId())
				{
					this.tecnologiaSeleccionada = tec;
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
	
	private void cargarSinonimosPorTecnologia(TecnologiaVO tecnologia)
	{
		if(tecnologia != null)
		{
			grdSinonimos.setItems(tecnologia.getListaSinonimos());
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
		
		this.actualizarInterfazAgregarSinonimo();
	}
	
	private void actualizarInterfazAgregarSinonimo()
	{
		formSinonimos.setEnabled(true);
		btnBorrarSinonimo.setVisible(false);
		btnAgregarSinonimo.setVisible(true);
		btnModificarTecnologia.setVisible(false);
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
	
	private void limpiarFormSinonimos()
	{
	    txtNombreSinonimo.clear();
	    formSinonimos.setEnabled(false);
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
		limpiarFormSinonimos();
		form.setEnabled(true);
		grdTecnologias.setEnabled(true);
		formTecnologia.setEnabled(false);
		formSinonimos.setEnabled(false);
		grdSinonimos.setEnabled(false);
	}
}
