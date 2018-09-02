package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;

public interface ProyectoService {

    List<Proyecto> listarProyectos();
    void add(Proyecto proyecto);
	void modify(Proyecto proyecto);
	void delete(Proyecto proyecto);
	List<SeccionTexto> armarDocumentoPorSecciones(String textoOriginal[]);
	SeccionTexto armarSeccionAlumnos(String texto[]);
	SeccionTexto armarSeccionTutor(String texto[]);
	List<Tecnologia> obtenerTecnologiasProyecto(Proyecto proyecto, List<Tecnologia> tecnologias);
	List<MetodologiaTesting> obtenerMetodologiasTestingProyecto(Proyecto proyecto, List<MetodologiaTesting> metodologiasTesting);
	List<ModeloProceso> obtenerModelosProcesoProyecto(Proyecto proyecto, List<ModeloProceso> modelosProceso);
}
