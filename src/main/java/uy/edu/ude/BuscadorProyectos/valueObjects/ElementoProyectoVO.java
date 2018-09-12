 package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.List;

public class ElementoProyectoVO {

	private Long id;
	private String nombre;
	private List<SinonimoVO> listaSinonimos;
	
	public ElementoProyectoVO() {

	}
	public ElementoProyectoVO(String value) {
		this.nombre=value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
}
	
