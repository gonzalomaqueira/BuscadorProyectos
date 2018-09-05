package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;


public interface ModeloProcesoService {

	List<ModeloProceso> obtenerModelosProceso();
	List<ModeloProceso> obtenerModelosProcesoCompleto();
    void add(ModeloProceso modelo);
	void modify(ModeloProceso modelo);
	void delete(ModeloProceso modelo);
}
