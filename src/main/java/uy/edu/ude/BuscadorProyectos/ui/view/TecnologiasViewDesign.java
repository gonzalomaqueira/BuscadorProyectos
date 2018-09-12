package uy.edu.ude.BuscadorProyectos.ui.view;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class TecnologiasViewDesign extends VerticalLayout {
	protected ComboBox<uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO> cmbCategorias;
	protected Label lblCategoria;
	protected Button btnNuevaTecnologia;
	protected Grid<uy.edu.ude.BuscadorProyectos.valueObjects.ElementoProyectoVO> grdTecnologias;
	protected Grid<uy.edu.ude.BuscadorProyectos.valueObjects.SinonimoVO> grdSinonimos;
	protected VerticalLayout form;
	protected CssLayout formTecnologia;
	protected TextField txtNombreTecnologia;
	protected ComboBox<uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO> cmbCategoriasTecnologias;
	protected Button btnAgregarTecnologia;
	protected Button btnModificarTecnologia;
	protected Button btnCancelarTecnologia;
	protected Button btnBorrarTecnologia;
	protected CssLayout formSinonimos;
	protected TextField txtNombreSinonimo;
	protected Button btnAgregarSinonimo;
	protected Button btnModificarSinonimo;
	protected Button btnCancelarSinonimo;
	protected Button btnBorrarSinonimo;

	public TecnologiasViewDesign() {
		Design.read(this);
	}
}
