package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;

public interface TecnologiaDao {
	List<Tecnologia> listTecnologias();
	   void add(Tecnologia tecnologia);
	   void modify(Tecnologia tecnologia);
	   void delete(Tecnologia tecnologia);
}
