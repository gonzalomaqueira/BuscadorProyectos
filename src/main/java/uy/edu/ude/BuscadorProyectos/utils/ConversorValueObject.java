package uy.edu.ude.BuscadorProyectos.utils;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.ElementoProyectoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.SinonimoVO;
import uy.edu.ude.BuscadorProyectos.valueObjects.UsuarioVO;

public class ConversorValueObject {
	
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

	
	public static CategoriaVO convertirCategoriaVO(Categoria categoria)
	{
		CategoriaVO categoriaVO = new CategoriaVO();
		categoriaVO.setId(categoria.getId());
		categoriaVO.setNombre(categoria.getNombre());
		categoriaVO.setTecnologias(convertirListaTecnologiasVO(categoria.getTecnologias()));
		
		return categoriaVO;
	}
	
	
	public static List<CategoriaVO> convertirListaCategoriaVO(List<Categoria> listaCategorias) 
	{
		List<CategoriaVO> listaCategoriasVO = new ArrayList<CategoriaVO>();		
		for(Categoria categoria : listaCategorias)
		{
			listaCategoriasVO.add(convertirCategoriaVO(categoria));
		}		
		return listaCategoriasVO;
	}
	
	public static ElementoProyectoVO convertirTecnologiaVO(Tecnologia tecnologia)
	{
		ElementoProyectoVO tecnologiaVO = new ElementoProyectoVO();
		tecnologiaVO.setId(tecnologia.getId());
		tecnologiaVO.setNombre(tecnologia.getNombre());		
		tecnologiaVO.setListaSinonimos(convertirListaSinonimosVO(tecnologia.getSinonimos()));
		
		return tecnologiaVO;
	}

	public static List<ElementoProyectoVO> convertirListaTecnologiasVO(List<Tecnologia> listaTecnologias) 
	{
		List<ElementoProyectoVO> listaTecnologiaVO = new ArrayList<ElementoProyectoVO>();
		for(Tecnologia tecnologia : listaTecnologias)
		{
			listaTecnologiaVO.add(convertirTecnologiaVO(tecnologia));
		}		
		return listaTecnologiaVO;
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
			listaSinonimosVO.add(convertirSinonimoVO(sinonimo));
		}		
		return listaSinonimosVO;
	}


}
