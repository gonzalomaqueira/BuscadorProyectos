package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.Perfil;

@Repository
public class PerfilDaoImp implements PerfilDao
{
	@PersistenceContext
	   private EntityManager em;
	
	@Override
	public List<Perfil> listPerfiles() 
	{
		 CriteriaQuery<Perfil> criteriaQuery = em.getCriteriaBuilder().createQuery(Perfil.class);
		 @SuppressWarnings("unused")
		 Root<Perfil> root = criteriaQuery.from(Perfil.class);
		 return em.createQuery(criteriaQuery).getResultList();
	}
}
