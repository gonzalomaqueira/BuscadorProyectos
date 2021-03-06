package uy.edu.ude.BuscadorProyectos.ui.view;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
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
public class ProyectosUploadViewDesign extends VerticalLayout {
	protected TextField search;
	protected Button btnNuevo;
	protected Grid<uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO> grdProyectos;
	protected VerticalLayout formContenido;
	protected TextField txtNombre;
	protected TextField txtAnio;
	protected TextField txtCarrera;
	protected TextField txtNota;
	protected Upload updProyecto;
	protected Button btnVerDetalle;
	protected Button btnProcesar;
	protected Button btnAgregar;
	protected Button btnModificar;
	protected Button btnCancelar;
	protected Button btnBorrar;

	public ProyectosUploadViewDesign() {
		Design.read(this);
	}
}
