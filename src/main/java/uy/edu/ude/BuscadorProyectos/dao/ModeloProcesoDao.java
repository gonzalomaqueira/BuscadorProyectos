package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoModeloProceso;

public interface ModeloProcesoDao {
	
	List<ModeloProceso> obtenerModelosProceso();
	void add(ModeloProceso modelo);
	void modify(ModeloProceso modelo);
	void delete(ModeloProceso modelo);
	List<SinonimoModeloProceso> obtenerSinonimosModeloProceso(int idModeloProceso);

}
