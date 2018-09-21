package uy.edu.ude.BuscadorProyectos.service.interfaces;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Elemento;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entidades.Enumerados.TipoElemento;

public interface ElementoService
{	
	void agregar(Elemento elemento);
	void agregar(String nombre, boolean esCategoria, TipoElemento tipoElemento, List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos);
	void modificar(String nombre, boolean esCategoria, TipoElemento tipoElemento, List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos);
	void eliminar(int id);
	List<Elemento> obtenerElementos();
	Elemento obtenerElementoPorId(int id);
}