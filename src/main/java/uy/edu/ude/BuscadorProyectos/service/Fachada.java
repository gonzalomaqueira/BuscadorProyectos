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
import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.utils.ConversorValueObject;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ElementoProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

@Service
public class Fachada {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private TecnologiaService tecnologiaService;
	@Autowired
	private ModeloProcesoService modeloProcesoService;
	@Autowired
	private MetodologiaTestingService metodologiaTestingService;
	
	/**************************************************************** Proyectos */
	
	public List<ProyectoVO> obtenerProyectos()
	{
		return ConversorValueObject.convertirListaProyectoVO(proyectoService.obtenerProyectos());
	}
	
	public void altaProyecto(String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
		proyectoService.altaProyecto(nombre, anio, carrera, nota, rutaArchivo);
	}
	
	public void modificarProyecto(int id, String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
		proyectoService.modificarProyecto(id, nombre, anio, carrera, nota, rutaArchivo);
	}
	
	public void borrarProyecto(int id)
	{
		proyectoService.borrarProyecto(id);
	}
	
	public List<SeccionTexto> armarDocumentoPorSecciones(String[] textoOriginal)
	{
		return proyectoService.armarDocumentoPorSecciones(textoOriginal);
	}
	
	public List<Tecnologia> obtenerTecnologiasProyecto(Proyecto proyecto)
	{
		return proyectoService.obtenerTecnologiasProyecto(proyecto, tecnologiaService.obtenerTecnologiasCompleto());
	}
	
	public List<ModeloProceso> obtenerModelosProcesoProyecto(Proyecto proyecto)
	{
		return proyectoService.obtenerModelosProcesoProyecto(proyecto, modeloProcesoService.obtenerModelosProcesoCompleto());
	}
	
	public List<MetodologiaTesting> obtenerMetodologiasTestingProyecto(Proyecto proyecto)
	{
		return proyectoService.obtenerMetodologiasTestingProyecto(proyecto, metodologiaTestingService.obtenerMetodologiasTestingCompleto());
	}
	
	public Proyecto obtenerProyectoPorId(int idProyecto)
	{
		return proyectoService.obtenerProyectoPorId(idProyecto);
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
		usuarioService.altaUsuario(usuario, contrasenia, nombre, apellido, email, p);
	}	
	
	public void modificarUsuario(int id, String usuario, String contrasenia, String nombre, String apellido, String email, PerfilVO perfil) 
	{
		Perfil p = new Perfil(); 
		p.setId(perfil.getId());
		usuarioService.modificarUsuario(id, usuario, contrasenia, nombre, apellido, email, p);
	}
	
	public void eliminarUsuario(int id)
	{
		usuarioService.eliminarUsuario(id);
	}

	public List<PerfilVO> obtenerPerfiles()
	{
		return ConversorValueObject.convertirListaPerfilVO(perfilService.obtenerPerfiles());
	}
	
	/**************************************************************** Tecnologías */
	
	public List<CategoriaVO> obtenerCategorias() {
		
		return ConversorValueObject.convertirListaCategoriaVO(categoriaService.obtenerCategoriasCompleto());
	}
	
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

	public String[] obtenerTextoOriginalProyecto(Proyecto proyecto) {
		
		PDDocument pdDoc = null;
		PDFTextStripper pdfStripper;
		String parsedText = null;
		String fileName = proyecto.getRutaArchivo();
		try 
		{
			pdDoc = PDDocument.load(new File(fileName));
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
		} catch (InvalidPasswordException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String textoOriginal[] = parsedText.split("\\r?\\n");
		return textoOriginal;
	}
}
