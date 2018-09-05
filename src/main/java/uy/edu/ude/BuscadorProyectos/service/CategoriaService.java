package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;
import uy.edu.ude.BuscadorProyectos.entity.Categoria;


public interface CategoriaService {

	List<Categoria> listCategorias();
    void add(Categoria categoria);
	void modify(Categoria categoria);
	void delete(Categoria categoria);	
}
