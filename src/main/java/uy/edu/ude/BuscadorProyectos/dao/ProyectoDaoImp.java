package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.Proyecto;

@Repository
public class ProyectoDaoImp implements ProyectoDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Proyecto> listarProyectos()
	{
	      CriteriaQuery<Proyecto> criteriaQuery = em.getCriteriaBuilder().createQuery(Proyecto.class);
	      @SuppressWarnings("unused")
	      Root<Proyecto> root = criteriaQuery.from(Proyecto.class);
	      return em.createQuery(criteriaQuery).getResultList();
	   }

	@Override
	public void add(Proyecto proyecto)
	{
		em.merge(proyecto);
    }

	@Override
	public void modify(Proyecto proyecto)
	{
		em.merge(proyecto);
    }

	@Override
	public void delete(Proyecto proyecto)
	{
		  em.remove(em.contains(proyecto) ? proyecto : em.merge(proyecto));
    }

	@Override
	public Proyecto obtenerProyectoPorId(int id)
	{
		return (Proyecto)em.find(Proyecto.class, id);
	}
}
