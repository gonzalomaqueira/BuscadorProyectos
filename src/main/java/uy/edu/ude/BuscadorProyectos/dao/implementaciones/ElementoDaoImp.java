package uy.edu.ude.BuscadorProyectos.dao.implementaciones;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.ElementoDao;
import uy.edu.ude.BuscadorProyectos.entity.Elemento;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;

@Repository
public class ElementoDaoImp implements ElementoDao
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Elemento> obtenerElementos()
	{
		CriteriaQuery<Elemento> criteriaQuery = em.getCriteriaBuilder().createQuery(Elemento.class);
		@SuppressWarnings("unused")
		Root<Elemento> root = criteriaQuery.from(Elemento.class);
		return em.createQuery(criteriaQuery).getResultList();
	}
	
	@Override
	public void agregar(Elemento elemento)
	{
		em.merge(elemento);
	}
	
	@Override
	public void modificar(Elemento elemento)
	{
		em.merge(elemento);
	}
	
	@Override
	public void eliminar(Elemento elemento)
	{
		em.remove(em.contains(elemento) ? elemento : em.merge(elemento));
	}
	
	@Override
	public Elemento obtenerElementoPorId(int id)
	{
		Session session = em.unwrap(Session.class);
		Object instancia = session.find(Elemento.class, id);
    	return (Elemento)instancia;
	}
}