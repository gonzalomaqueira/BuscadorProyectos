package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;

public interface CategoriaDao {
	List<Categoria> listCategorias();
	   void add(Categoria usuario);
	   void modify(Categoria usuario);
	   void delete(Categoria usuario);
}
