package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;

public interface ProyectoDao {

	List<Proyecto> listarProyectos();
	void add(Proyecto proyecto);
	void modify(Proyecto proyecto);
	void delete(Proyecto proyecto);
}
