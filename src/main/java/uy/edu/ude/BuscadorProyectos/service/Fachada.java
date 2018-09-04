package uy.edu.ude.BuscadorProyectos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.utils.ConversorValueObject;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

@Service
public class Fachada {
	
	@Autowired
	private TecnologiaService tecnologiaService;
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

	public void altaUsuario(String usuario, String contrasenia, String nombre, String apellido, String email, PerfilVO perfil) 
	{
		Perfil p = new Perfil();
		p.setId(perfil.getId());
		usuarioService.altaUsuario(usuario, contrasenia, nombre, apellido, email, p);
	}	
	
	public void modificarUsuario(Long id, String usuario, String contrasenia, String nombre, String apellido, String email, PerfilVO perfil) 
	{
		Perfil p = new Perfil();
		p.setId(perfil.getId());
		usuarioService.modificarUsuario(id, usuario, contrasenia, nombre, apellido, email, p);
	}
	
	public void eliminarUsuario(Long id)
	{
		usuarioService.eliminarUsuario(id);
	}

}
