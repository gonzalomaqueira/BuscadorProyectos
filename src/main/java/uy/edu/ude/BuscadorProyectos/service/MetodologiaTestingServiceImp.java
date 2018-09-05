package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.MetodologiaTestingDao;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;


@Service
public class MetodologiaTestingServiceImp implements MetodologiaTestingService {
	
	@Autowired
	private MetodologiaTestingDao metodologiaTestingDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<MetodologiaTesting> obtenerMetodologiasTesting() 
	{
		return metodologiaTestingDao.obtenerMetodologiasTesting();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<MetodologiaTesting> obtenerMetodologiasTestingCompleto()
	{
		List<MetodologiaTesting> vRetorno = metodologiaTestingDao.obtenerMetodologiasTesting();
		for(MetodologiaTesting mt: vRetorno)
		{
			mt.setSinonimos(metodologiaTestingDao.obtenerSinonimosMetodologiaTesting(mt.getId()));
		}
		return vRetorno;
	}
	
	
	@Transactional
	@Override
	public void add(MetodologiaTesting metodologia) {
		metodologiaTestingDao.add(metodologia);
		
	}

	@Transactional
	@Override
	public void modify(MetodologiaTesting metodologia) {
		metodologiaTestingDao.modify(metodologia);
		
	}

	@Transactional
	@Override
	public void delete(MetodologiaTesting metodologia) {
		metodologiaTestingDao.delete(metodologia);
		
	}

}
