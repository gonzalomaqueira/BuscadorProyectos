package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ElementoService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.PerfilService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ProyectoService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.UsuarioService;
import uy.edu.ude.BuscadorProyectos.utiles.ConversorValueObject;
import uy.edu.ude.BuscadorProyectos.utiles.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.valueObjects.ElementoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

@Service
public class Fachada {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private ElementoService elementoService;

	/**************************************************************** Proyectos */
	
	public List<ProyectoVO> obtenerProyectos()
	{
		return ConversorValueObject.convertirListaProyectoVO(proyectoService.obtenerProyectos());
	}
	
	public ProyectoDetalleVO obtenerProyectoPorId(int idProyecto)
	{
		return ConversorValueObject.convertirProyectoDetalleVO(proyectoService.obtenerProyectoPorId(idProyecto));
	}
	
	public void altaProyecto(String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
		proyectoService.agregar(nombre, anio, carrera, nota, rutaArchivo);
	}
	
	public void modificarProyecto(int id, String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
		proyectoService.modificar(id, nombre, anio, carrera, nota, rutaArchivo);
	}
	
	public void modificarProyectoCompleto(int id, String nombre, int anio, String carrera, int nota, String resumen, 
			ArrayList<String> alumnos, ArrayList<String> tutor) 
	{
		proyectoService.modificar(id, nombre, anio, carrera, nota, resumen, alumnos, tutor);
	}
	
	public void borrarProyecto(int id)
	{
		proyectoService.eliminar(id);
	}
	
	public List<SeccionTexto> armarDocumentoPorSecciones(String[] textoOriginal)
	{
		return proyectoService.armarDocumentoPorSecciones(textoOriginal);
	}
	
	public void ProcesarProyecto(int idProyecto)
	{
		proyectoService.procesarProyecto(idProyecto);
	}
	
	/**************************************************************** Usuarios */	
	
	public List<UsuarioVO> obtenerUsuarios()
	{
		return ConversorValueObject.convertirListaUsuarioVO(usuarioService.obtenerUsuarios());
	}
	
	public void altaUsuario(String usuario, String contrasenia, String nombre, String apellido, String email, PerfilVO perfil) 
	{
		Perfil p = new Perfil();
		p.setId(perfil.getId());
		usuarioService.agregar(usuario, contrasenia, nombre, apellido, email, p);
	}	
	
	public void modificarUsuario(int id, String usuario, String contrasenia, String nombre, String apellido, String email, PerfilVO perfil) 
	{
		Perfil p = new Perfil(); 
		p.setId(perfil.getId());
		usuarioService.modificar(id, usuario, contrasenia, nombre, apellido, email, p);
	}
	
	public void eliminarUsuario(int id)
	{
		usuarioService.eliminar(id);
	}

	public List<PerfilVO> obtenerPerfiles()
	{
		return ConversorValueObject.convertirListaPerfilVO(perfilService.obtenerPerfiles());
	}
	
	/**************************************************************** Elementos */
	
	public List<ElementoVO> obtenerElementos()
	{
		return ConversorValueObject.convertirListaElementoVO(elementoService.obtenerElementos());
	}

}
