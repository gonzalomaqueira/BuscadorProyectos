package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.List;

public class ElementoVO 
{	
	private int id;
	private String nombre;
	private List<SinonimoVO> sinonimos;
	private List<ElementoVO> elementosRelacionados;
	
	public ElementoVO()
	{	}
		
 	public ElementoVO(String nombre)
 	{
 		this.nombre = nombre;
 	}
 	
 	public int getId() { return id;	}
 	public void setId(int id) { this.id = id; }
 	
 	public String getNombre() { return nombre; }
 	public void setNombre(String nombre) { this.nombre = nombre; }
 	
 	public List<SinonimoVO> getSinonimos() { return sinonimos; }
 	public void setSinonimos(List<SinonimoVO> sinonimos) { this.sinonimos = sinonimos; }
 	
 	public List<ElementoVO> getElementosRelacionados() { return elementosRelacionados; }
 	public void setElementosRelacionados(List<ElementoVO> elementosRelacionados) { this.elementosRelacionados = elementosRelacionados; }
 }
