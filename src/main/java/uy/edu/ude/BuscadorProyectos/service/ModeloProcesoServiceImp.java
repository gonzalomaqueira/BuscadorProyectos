package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.ModeloProcesoDao;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;


@Service
public class ModeloProcesoServiceImp implements ModeloProcesoService{

	@Autowired
	private ModeloProcesoDao modeloProcesoDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<ModeloProceso> obtenerModelosProceso() 
	{
		return modeloProcesoDao.obtenerModelosProceso();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<ModeloProceso> obtenerModelosProcesoCompleto()
	{
		List<ModeloProceso> vRetorno = modeloProcesoDao.obtenerModelosProceso();
		for(ModeloProceso mp: vRetorno)
		{
			mp.setSinonimos(modeloProcesoDao.obtenerSinonimosModeloProceso(mp.getId()));
		}
		return vRetorno;
	}
	
	
	@Transactional
	@Override
	public void add(ModeloProceso modelo) {
		modeloProcesoDao.add(modelo);
		
	}

	@Transactional
	@Override
	public void modify(ModeloProceso modelo) {
		modeloProcesoDao.modify(modelo);
		
	}

	@Transactional
	@Override
	public void delete(ModeloProceso modelo) {
		modeloProcesoDao.delete(modelo);
		
	}
}
