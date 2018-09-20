package uy.edu.ude.BuscadorProyectos.dao.interfaces;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Proyecto;

public interface ProyectoDao
{
	void agregar(Proyecto proyecto);
	void modificar(Proyecto proyecto);
	void eliminar(Proyecto proyecto);
	List<Proyecto> obtenerProyectos();
	Proyecto obtenerProyectoPorId(int id);
}
