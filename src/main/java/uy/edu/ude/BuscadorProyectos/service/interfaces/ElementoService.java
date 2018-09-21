package uy.edu.ude.BuscadorProyectos.service.interfaces;

import java.util.List;
import java.util.Set;

import uy.edu.ude.BuscadorProyectos.entidades.Elemento;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entidades.Enumerados.TipoElemento;

public interface ElementoService
{	
	void agregar(Elemento elemento);
	void agregar(String nombre, boolean esCategoria, TipoElemento tipoElemento, List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos);
	void modificar(int id, String nombre, boolean esCategoria, TipoElemento tipoElemento, List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos);
	void eliminar(int id);
	void eliminar(Elemento elemento);
	List<Elemento> obtenerElementos();
	Elemento obtenerElementoPorId(int id);
}