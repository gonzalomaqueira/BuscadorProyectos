package uy.edu.ude.BuscadorProyectos.dao.implementaciones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.PerfilDao;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;

@Repository
public class PerfilDaoImp implements PerfilDao
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Perfil> obtenerPerfiles() 
	{
		 CriteriaQuery<Perfil> criteriaQuery = em.getCriteriaBuilder().createQuery(Perfil.class);
		 @SuppressWarnings("unused")
		 Root<Perfil> root = criteriaQuery.from(Perfil.class);
		 return em.createQuery(criteriaQuery).getResultList();
	}
}
