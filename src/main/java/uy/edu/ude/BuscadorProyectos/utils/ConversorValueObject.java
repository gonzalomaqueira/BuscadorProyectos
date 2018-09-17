package uy.edu.ude.BuscadorProyectos.utils;

import java.util.ArrayList;
import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
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
									  convertirListaTecnologiasVO(proyecto.getTecnologia()),
									  convertirListaModeloProcesoVO(proyecto.getModeloProceso()),
									  convertirListaMetodologiaTestingVO(proyecto.getMetodologiaTesting()));
		
	}
	
	public static ProyectoVO convertirProyectoVO(Proyecto proyecto)
	{
		return new ProyectoVO(proyecto.getId(), 
							  proyecto.getNombre(),
							  proyecto.getAnio(),
							  proyecto.getCarrera(),
							  proyecto.getNota());
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

	
	/****************************************************** Tecnología */	

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
	
	public static TecnologiaVO convertirTecnologiaVO(Tecnologia tecnologia)
	{
		TecnologiaVO tecnologiaVO = new TecnologiaVO();
		tecnologiaVO.setId(tecnologia.getId());
		tecnologiaVO.setNombre(tecnologia.getNombre());	
		tecnologiaVO.setIdCategoria( tecnologia.getCategoria().getId() );
		tecnologiaVO.setListaSinonimos(convertirListaSinonimosVO(tecnologia.getSinonimos()));
		
		return tecnologiaVO;
	}

	public static List<TecnologiaVO> convertirListaTecnologiasVO(List<Tecnologia> listaTecnologias) 
	{
		List<TecnologiaVO> listaTecnologiaVO = new ArrayList<TecnologiaVO>();
		for(Tecnologia tecnologia : listaTecnologias)
		{
			listaTecnologiaVO.add(convertirTecnologiaVO(tecnologia));
		}
		return listaTecnologiaVO;
	}
	
	public static ModeloProcesoVO convertirModeloProcesoVO(ModeloProceso modeloProceso)
	{
		ModeloProcesoVO modeloProcesoVO = new ModeloProcesoVO();
		modeloProcesoVO.setId(modeloProceso.getId());
		modeloProcesoVO.setNombre(modeloProceso.getNombre());	
		modeloProcesoVO.setListaSinonimos(convertirListaSinonimosVO(modeloProceso.getSinonimos()));
		return modeloProcesoVO;
	}

	public static List<ModeloProcesoVO> convertirListaModeloProcesoVO(List<ModeloProceso> listaModeloProceso) 
	{
		List<ModeloProcesoVO> listaModeloProcesoVO = new ArrayList<ModeloProcesoVO>();
		for(ModeloProceso modeloProceso : listaModeloProceso)
		{
			listaModeloProcesoVO.add(convertirModeloProcesoVO(modeloProceso));
		}		
		return listaModeloProcesoVO;
	}
	
	public static MetodologiaTestingVO convertirMetodologiaTestingVO(MetodologiaTesting metodologiaTesting)
	{
		MetodologiaTestingVO metodologiaTestingVO = new MetodologiaTestingVO();
		metodologiaTestingVO.setId(metodologiaTesting.getId());
		metodologiaTestingVO.setNombre(metodologiaTesting.getNombre());	
		metodologiaTestingVO.setListaSinonimos(convertirListaSinonimosVO(metodologiaTesting.getSinonimos()));
		return metodologiaTestingVO;
	}

	public static List<MetodologiaTestingVO> convertirListaMetodologiaTestingVO(List<MetodologiaTesting> listaMetodologiaTesting) 
	{
		List<MetodologiaTestingVO> listaMetodologiaTestingVO = new ArrayList<MetodologiaTestingVO>();
		for(MetodologiaTesting metodologiaTesting : listaMetodologiaTesting)
		{
			listaMetodologiaTestingVO.add(convertirMetodologiaTestingVO(metodologiaTesting));
		}		
		return listaMetodologiaTestingVO;
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
