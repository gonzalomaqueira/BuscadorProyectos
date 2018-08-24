package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

public interface ExtraccionService  {
	
	String ExtraerTodo(String ruta);
	String ExtraerTutor(String contenido);
	List<String> ExtraerAlumnos(String contenido);
	List<String> ExtraerTecnologias(String contenido);
	List<String> ExtraerMetodologia(String contenido);
	List<String> ExtraerTesting(String contenido);

}
