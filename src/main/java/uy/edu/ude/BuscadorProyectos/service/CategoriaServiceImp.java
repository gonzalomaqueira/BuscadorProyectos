package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.CategoriaDao;
import uy.edu.ude.BuscadorProyectos.dao.TecnologiaDao;
import uy.edu.ude.BuscadorProyectos.dao.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;

@Service
public class CategoriaServiceImp implements CategoriaService {

	@Autowired
	private CategoriaDao categoriaDao;
	@Autowired
	private TecnologiaService tecnologiaService;
	
	@Transactional(readOnly = true)
	@Override
	public List<Categoria> obtenerCategorias() {
		return categoriaDao.obtenerCategorias();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Categoria> obtenerCategoriasCompleto() {
		List<Categoria> vRetorno = categoriaDao.obtenerCategorias();
		for(Categoria cat: vRetorno)
		{
			cat.setTecnologias(tecnologiaService.obtenerTecnologiasCompletoPorCategoria(cat));
		}
		return vRetorno;
	}

	@Transactional
	@Override
	public void add(Categoria categoria) {
		categoriaDao.add(categoria);
		
	}

	@Transactional
	@Override
	public void modify(Categoria categoria) {
		categoriaDao.modify(categoria);
		
	}

	@Transactional
	@Override
	public void delete(Categoria categoria) {
		categoriaDao.delete(categoria);
		
	}

}
