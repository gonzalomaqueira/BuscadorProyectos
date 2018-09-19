package uy.edu.ude.BuscadorProyectos.service.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.ElementoDao;
import uy.edu.ude.BuscadorProyectos.entity.Elemento;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ElementoService;

@Service
public class ElementoServiceImp implements ElementoService 
{
	@Autowired
	private ElementoDao elementoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Elemento> obtenerElementos() 
	{
		return elementoDao.obtenerElementos();
	}
		
	@Transactional(readOnly = true)
	@Override
	public Elemento obtenerElementoPorId(int id)
	{
		return elementoDao.obtenerElementoPorId(id);	
	}

	@Transactional
	@Override
	public void agregar(Elemento elemento)
	{
		elementoDao.agregar(elemento);
	}

	@Transactional
	@Override
	public void modificar(Elemento elemento)
	{
		elementoDao.modificar(elemento);
	}

	@Transactional
	@Override
	public void eliminar(Elemento elemento) 
	{
		elementoDao.eliminar(elemento);
	}		
}
