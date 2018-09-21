package uy.edu.ude.BuscadorProyectos.service.implementaciones;

import java.util.List;
import java.util.Set;

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
	public void modificar(int id, String nombre, boolean esCategoria, TipoElemento tipoElemento,
						  List<Elemento> elementosRelacionados, List<Sinonimo> sinonimos)
	{
		Elemento elementoBase = this.obtenerElementoPorId(id);
		elementoBase.setElementosRelacionados(elementosRelacionados);
		elementoDao.modificar(elementoBase);
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
/////////////////////////////////////////////////////////////////////////////
	    
		for(Elemento eR : elemento.getElementosRelacionados())
		{		
			for (Elemento eO : eR.getElementosOrigen())
			{
				if(eO.getId() == elemento.getId())
				{
					eR.getElementosOrigen().remove(elemento);
					break;
				}
			}
		}
		elemento.getElementosRelacionados().removeAll(elemento.getElementosRelacionados());
		
		
		for(Elemento eO : elemento.getElementosOrigen())
		{
			for (Elemento eR : eO.getElementosRelacionados())
			{
				if(eR.getId() == elemento.getId())
				{
					eO.getElementosRelacionados().remove(elemento);
					break;
				}
			}
		}
		elemento.getElementosOrigen().removeAll(elemento.getElementosOrigen());
		
////////////////////////////////////////////////////////////////////////
	    elementoDao.eliminar(elemento);
	}
	
	@Transactional
	@Override
	public void eliminar(Elemento elemento)
	{
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
