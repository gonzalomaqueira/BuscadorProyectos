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
public class TecnologiaServiceImp implements TecnologiaService {

	@Autowired
	private TecnologiaDao tecnologiaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Tecnologia> listTecnologias() {
		return tecnologiaDao.listTecnologias();
	}

	@Transactional
	@Override
	public void add(Tecnologia tecnologia) {
		tecnologiaDao.add(tecnologia);
		
	}

	@Transactional
	@Override
	public void modify(Tecnologia tecnologia) {
		tecnologiaDao.modify(tecnologia);
		
	}

	@Transactional
	@Override
	public void delete(Tecnologia tecnologia) {
		tecnologiaDao.delete(tecnologia);
		
	}

}
