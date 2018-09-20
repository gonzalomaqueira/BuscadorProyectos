package uy.edu.ude.BuscadorProyectos.dao.interfaces;

import java.util.List;
import java.util.Set;

import uy.edu.ude.BuscadorProyectos.entidades.Elemento;

public interface ElementoDao 
{	
	void agregar(Elemento elemento);
	void modificar(Elemento elemento);
	void eliminar(Elemento elemento);
	List<Elemento> obtenerElementos();
	Elemento obtenerElementoPorId(int id);
}
