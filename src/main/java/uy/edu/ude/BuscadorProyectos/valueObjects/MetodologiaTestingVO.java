package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.List;

public class MetodologiaTestingVO
{
	private int id;
	private String nombre;
	private List<SinonimoVO> listaSinonimos;
	
	public MetodologiaTestingVO() {

	}
	public MetodologiaTestingVO(String value) {
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
}
