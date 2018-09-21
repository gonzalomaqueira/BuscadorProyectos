package uy.edu.ude.BuscadorProyectos.service.interfaces;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Elemento;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;

public interface SinonimoService
{
	void agregar(String nombre, Elemento elemento);
	void modificar(int id, String nombre);
	void eliminar(int id);
    List<Sinonimo> obtenerSinonimos();
    Sinonimo obtenerSinonimoPorId(int id);
}
