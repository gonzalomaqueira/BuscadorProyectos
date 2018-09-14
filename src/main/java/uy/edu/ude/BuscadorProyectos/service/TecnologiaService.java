package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;
import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;


public interface TecnologiaService {

	List<Tecnologia> obtenerTecnologias();
	List<Tecnologia> obtenerTecnologiasCompleto();
    void add(Tecnologia tecnologia);
	void modify(Tecnologia tecnologia);
	void delete(Tecnologia tecnologia);
	List<Tecnologia> obtenerTecnologiasCompletoPorCategoria(Categoria categoria);
	void altaTecnologia(String nombreTecnologia, int idCategoria);
	void eliminarTecnologia(int id);
	void modificarTecnologia(int idTecnologia, String nombre, int idCategoria);
	void altaSinonimoTecnologia(String nombreSinonimo, int idTecnologia);
	void modificarSinonimoTecnologia(int idSinonimo, String nombreSinonimo);
	void eliminarSinonimoTecnologia(int idSinonimo);

}
