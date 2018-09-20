package uy.edu.ude.BuscadorProyectos.utiles;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Categoria;
import uy.edu.ude.BuscadorProyectos.entidades.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entidades.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.entidades.Proyecto;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entidades.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.MetodologiaTestingVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ModeloProcesoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoDetalleVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.SinonimoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.TecnologiaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

public class ConversorValueObject 
{
	/****************************************************** Proyecto */
	
	public static ProyectoDetalleVO convertirProyectoDetalleVO(Proyecto proyecto)
	{
		return new ProyectoDetalleVO( proyecto.getId(), 
									  proyecto.getNombre(),
									  proyecto.getAnio(),
									  proyecto.getCarrera(),
									  proyecto.getNota(),
									  proyecto.getAlumnos(),
									  proyecto.getTutor(),
									  proyecto.getRutaArchivo(),
									  proyecto.getResumen(),
									  proyecto.getFechaAlta(),
									  proyecto.getFechaUltimaModificacion(),
									  convertirListaTecnologiasVO(proyecto.getTecnologias()),
									  convertirListaModeloProcesoVO(proyecto.getModeloProceso()),
									  convertirListaMetodologiaTestingVO(proyecto.getMetodologiaTesting()));
		
	}
	
	public static ProyectoVO convertirProyectoVO(Proyecto proyecto)
	{
		return new ProyectoVO(proyecto.getId(), 
							  proyecto.getNombre(),
							  proyecto.getAnio(),
							  proyecto.getCarrera(),
							  proyecto.getNota(),
							  proyecto.getEstado());
	}
	
	public static List<ProyectoVO> convertirListaProyectoVO(List<Proyecto> listaProyectos)
	{
		List<ProyectoVO> listaProyectosVO = new ArrayList<ProyectoVO>();

		for(Proyecto proyecto : listaProyectos)
		{
			listaProyectosVO.add(convertirProyectoVO(proyecto));
		}		
		return listaProyectosVO;
	}
	
	/****************************************************** Usuario */
	
	public static UsuarioVO convertirUsuarioVO(Usuario usuario)
	{
		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setId(usuario.getId());
		usuarioVO.setUsuario(usuario.getUsuario());
		usuarioVO.setNombre(usuario.getNombre());
		usuarioVO.setApellido(usuario.getApellido());
		usuarioVO.setEmail(usuario.getEmail());
		usuarioVO.setPerfil(convertirPerfilVO(usuario.getPerfil()));
		
		return usuarioVO;
	}
	
	public static List<UsuarioVO> convertirListaUsuarioVO(List<Usuario> listaUsuarios)
	{
		List<UsuarioVO> listaUsuariosVO= new ArrayList<UsuarioVO>();
		
		for(Usuario usuario : listaUsuarios)
		{
			listaUsuariosVO.add(convertirUsuarioVO(usuario));
		}		
		return listaUsuariosVO;
	}
	
	public static PerfilVO convertirPerfilVO(Perfil perfil)
	{
		PerfilVO perfilVO = new PerfilVO();
		perfilVO.setId(perfil.getId());
		perfilVO.setDescripcion(perfil.getDescripcion());
		
		return perfilVO;
	}
	
	public static List<PerfilVO> convertirListaPerfilVO(List<Perfil> listaPerfiles)
	{
		List<PerfilVO> listaPerfilesVO = new ArrayList<PerfilVO>();
		
		for(Perfil perfil : listaPerfiles)
		{
			listaPerfilesVO.add(convertirPerfilVO(perfil));
		}		
		return listaPerfilesVO;
	}

	public static SinonimoVO convertirSinonimoVO(Sinonimo sinonimo)
	{
		SinonimoVO sinonimoVO = new SinonimoVO();
		sinonimoVO.setId(sinonimo.getId());
		sinonimoVO.setNombre(sinonimo.getNombre());
		
		return sinonimoVO;
	}
	
	private static List<SinonimoVO> convertirListaSinonimosVO(List<Sinonimo> listaSinonimos)
	{
		List<SinonimoVO> listaSinonimosVO = new ArrayList<SinonimoVO>();
		for(Sinonimo sinonimo : listaSinonimos)
		{
			listaSinonimosVO.agregar(convertirSinonimoVO(sinonimo));
		}		
		return listaSinonimosVO;
	}

}
