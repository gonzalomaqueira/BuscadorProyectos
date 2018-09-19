package uy.edu.ude.BuscadorProyectos.dao.interfaces;

import java.util.List;
import java.util.Set;

import uy.edu.ude.BuscadorProyectos.entity.Elemento;

public interface ElementoDao 
{	
	List<Elemento> obtenerElementos();
	Elemento obtenerElementoPorId(int id);
	void agregar(Elemento elemento);
	void modificar(Elemento elemento);
	void eliminar(Elemento elemento);
}
