package uy.edu.ude.BuscadorProyectos.dao.interfaces;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Elemento;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;

public interface SinonimoDao
{
	void agregar(Sinonimo sinonimo);
	void modificar(Sinonimo sinonimo);
	void eliminar(Sinonimo sinonimo);
	List<Sinonimo> obtenerSinonimos();
	Sinonimo obtenerSinonimoPorId(int id);
}
