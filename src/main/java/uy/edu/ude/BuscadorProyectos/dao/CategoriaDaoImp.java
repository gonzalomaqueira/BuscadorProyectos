package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;

@Repository
public class CategoriaDaoImp implements CategoriaDao {

	@PersistenceContext
	   private EntityManager em;
	
	@Override
	public List<Categoria> obtenerCategorias() {
	      CriteriaQuery<Categoria> criteriaQuery = em.getCriteriaBuilder().createQuery(Categoria.class);
	      @SuppressWarnings("unused")
	      Root<Categoria> root = criteriaQuery.from(Categoria.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void add(Categoria categoria) {
		   em.merge(categoria);

	}

	@Override
	public void modify(Categoria categoria) {
		  em.merge(categoria);

	}

	@Override
	public void delete(Categoria categoria) {
		em.remove(em.contains(categoria) ? categoria : em.merge(categoria));

	}

}
