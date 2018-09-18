package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;

public interface ProyectoService {

    List<Proyecto> obtenerProyectos();
    void agregar(Proyecto proyecto);
	void modificar(Proyecto proyecto);
	void borrar(Proyecto proyecto);
	
	void altaProyecto(String nombre, int anio, String carrera, int nota, String rutaArchivo);
	void modificarProyecto(int id, String nombre, int anio, String carrera, int nota, String rutaArchivo);
	void modificarCompleto(int id, String nombre, int anio, String carrera, int nota, String resumen, ArrayList<String> alumnos, 
							ArrayList<String> tutor);
	void borrarProyecto(int id);
	Proyecto obtenerProyectoPorId(int idProyecto);
	
	List<SeccionTexto> armarDocumentoPorSecciones(String textoOriginal[]);
	SeccionTexto armarSeccionAlumnos(String texto[]);
	SeccionTexto armarSeccionTutor(String texto[]);
	List<Tecnologia> obtenerTecnologiasProyecto(Proyecto proyecto, List<Tecnologia> tecnologias);
	List<MetodologiaTesting> obtenerMetodologiasTestingProyecto(Proyecto proyecto, List<MetodologiaTesting> metodologiasTesting);
	List<ModeloProceso> obtenerModelosProcesoProyecto(Proyecto proyecto, List<ModeloProceso> modelosProceso);
	String[] obtenerTextoOriginalProyecto(Proyecto proyecto);

	
}
