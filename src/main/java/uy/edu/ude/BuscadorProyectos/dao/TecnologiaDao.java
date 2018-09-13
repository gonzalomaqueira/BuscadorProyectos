package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;

public interface TecnologiaDao {
	List<Tecnologia> obtenerTecnologias();
	void add(Tecnologia tecnologia);
	void modify(Tecnologia tecnologia);
	void delete(Tecnologia tecnologia);
	List<SinonimoTecnologia> obtenerSinonimosTecnologia(int idTecnologia);
	List<Tecnologia> obtenerTecnologiasPorCategoria(int idCategoria);
	Tecnologia obtenerTecnologiaPorId(int idTecnologia); 
}
