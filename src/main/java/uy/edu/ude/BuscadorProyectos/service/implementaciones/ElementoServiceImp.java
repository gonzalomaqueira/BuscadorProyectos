package uy.edu.ude.BuscadorProyectos.service.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.ElementoDao;
import uy.edu.ude.BuscadorProyectos.entidades.Elemento;
import uy.edu.ude.BuscadorProyectos.entidades.Proyecto;
import uy.edu.ude.BuscadorProyectos.entidades.Enumerados.TipoElemento;
import uy.edu.ude.BuscadorProyectos.entidades.Sinonimo;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ElementoService;

@Service
public class ElementoServiceImp implements ElementoService 
{
	@Autowired
	private ElementoDao elementoDao;

	@Transactional
	@Override
	public void agregar(Elemento elemento)
	{
		elementoDao.agregar(elemento);
	}
	
	@Transactional
	@Override
	public void agregar(String nombre, boolean esCategoria, TipoElemento tipoElemento,
						List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos)
	{
		Elemento elemento = new Elemento(nombre, esCategoria, tipoElemento, elementosRelacionados, sinonimos);
		elementoDao.agregar(elemento);
	}

	@Transactional
	@Override
	public void modificar(String nombre, boolean esCategoria, TipoElemento tipoElemento,
						  List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos)
	{
		// TODO Auto-generated method stub
	}

	@Transactional
	@Override
	public void eliminar(int id)
	{
		Elemento elemento = this.obtenerElementoPorId(id);
		for (Proyecto proy: elemento.getProyectos())
	    {
		    proy.getElementosRelacionados().remove(elemento);
	    }
	    elemento.getProyectos().removeAll(elemento.getProyectos());
	    elementoDao.eliminar(elemento);
	}
	
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
}
