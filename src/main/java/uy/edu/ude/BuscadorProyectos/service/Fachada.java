package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	private ExtraccionService extraccionService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private TecnologiaService tecnologiaService;
	@Autowired
	private ModeloProcesoService modeloProcesoService;
	@Autowired
	private MetodologiaTestingService metodologiaTestingService;

	
	
	
	@Autowired
	private TecnologiaDao tecnologiaDao;
	
	@Transactional(readOnly = true)
	public void obtenerTecnologiasPrueba()
	{
		List<Tecnologia> listaTec = tecnologiaService.obtenerTecnologias();
		tecnologiaDao.delete(listaTec.get(5));
	}
	
	

	@Transactional(readOnly = true)
	public List<Tecnologia> obtenerTecnologias()
	{
		return tecnologiaService.obtenerTecnologias();
	}
	
	public List<PerfilVO> listarPerfiles()
	{
		return ConversorValueObject.convertirListaPerfilVO(perfilService.listPerfiles());
	}
	
	public List<UsuarioVO> listarUsuarios()
	{
		return ConversorValueObject.convertirListaUsuarioVO(usuarioService.listUsuarios());
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

	public List<CategoriaVO> obtenerCategorias() {
		
		return ConversorValueObject.convertirListaCategoriaVO(categoriaService.obtenerCategoriasCompleto());
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
}
