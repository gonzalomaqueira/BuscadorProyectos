package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.List;

public class TecnologiaVO extends ElementoProyectoVO{
	
	private int id;
	private String nombre;
	private List<SinonimoVO> listaSinonimos;
	private int idCategoria;
	
	public TecnologiaVO() {

	}
	public TecnologiaVO(String value) {
		this.nombre=value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<SinonimoVO> getListaSinonimos() {
		return listaSinonimos;
	}
	public void setListaSinonimos(List<SinonimoVO> listaSinonimos) {
		this.listaSinonimos = listaSinonimos;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	

}
