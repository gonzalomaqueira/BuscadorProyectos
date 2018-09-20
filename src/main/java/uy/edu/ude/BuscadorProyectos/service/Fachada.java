package uy.edu.ude.BuscadorProyectos.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.TecnologiaDao;
import uy.edu.ude.BuscadorProyectos.entidades.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entidades.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entidades.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.entidades.Proyecto;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entidades.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;
import uy.edu.ude.BuscadorProyectos.entidades.Enumerados.EstadoProyectoEnum;
import uy.edu.ude.BuscadorProyectos.service.interfaces.PerfilService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ProyectoService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.TecnologiaService;
import uy.edu.ude.BuscadorProyectos.service.interfaces.UsuarioService;
import uy.edu.ude.BuscadorProyectos.utiles.ConversorValueObject;
import uy.edu.ude.BuscadorProyectos.utiles.FuncionesTexto;
import uy.edu.ude.BuscadorProyectos.utiles.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ElementoProyectoVO;
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
	private TecnologiaService tecnologiaService;

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
		Proyecto proyecto= proyectoService.obtenerProyectoPorId(idProyecto);
		String[] textoOriginal= proyectoService.obtenerTextoOriginalProyecto(proyecto);
		proyecto.setDocumentoPorSecciones( proyectoService.armarDocumentoPorSecciones(textoOriginal) );
		proyecto.setAlumnos(proyecto.devolverAlumnos());
		proyecto.setTutor(proyecto.devolverTutor());
		proyecto.setResumen(FuncionesTexto.convertirArrayAStringEspacios(proyecto.devolverResumen()));
				
		proyecto.setTecnologias(this.obtenerTecnologiasProyecto(proyecto));
		proyecto.setModeloProceso(this.obtenerModelosProcesoProyecto(proyecto));
		proyecto.setMetodologiaTesting(this.obtenerMetodologiasTestingProyecto(proyecto));
		
		proyecto.setEstado(EstadoProyectoEnum.PROCESADO);
		proyectoService.modificar(proyecto);
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
	
	/**************************************************************** Tecnologías */
	
	@Transactional(readOnly = true)
	public List<Tecnologia> obtenerTecnologias()
	{
		return tecnologiaService.obtenerTecnologias();
	}

	public void altaTecnologia(String nombreTecnologia, int idCategoria) 
	{		
		tecnologiaService.altaTecnologia(nombreTecnologia, idCategoria);
	}

	public void eliminarTecnologia(int id) 
	{		
		tecnologiaService.eliminarTecnologia(id);
	}

	public void modificarTecnologia(int idTecnologia, String nombre, int idCategoria) {
		 
		tecnologiaService.modificarTecnologia(idTecnologia, nombre, idCategoria);		
	}

	public void altaSinonimoTecnologia(String nombreSinonimo, int idTecnologia)
	{
		tecnologiaService.altaSinonimoTecnologia(nombreSinonimo, idTecnologia);
	}

	public void modificarSinonimo(int idSinonimo, String nombreSinonimo) 
	{
		tecnologiaService.modificarSinonimoTecnologia(idSinonimo, nombreSinonimo);
	}

	public void eliminarSinonimo(int idSinonimo) 
	{
		tecnologiaService.eliminarSinonimoTecnologia(idSinonimo);	
	}	
	

}
