package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.List;

public class CategoriaVO {
		
	private Long id;
	private String nombre;
	private List<ElementoProyectoVO> listaTecnologias;
	
	
	public long getId() 
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public List<ElementoProyectoVO> getTecnologias() {
		return listaTecnologias;
	}

	public void setTecnologias(List<ElementoProyectoVO> listaTecnologias) {
		this.listaTecnologias = listaTecnologias;
	}
	
}
