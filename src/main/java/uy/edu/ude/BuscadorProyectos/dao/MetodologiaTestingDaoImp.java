package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoMetodologiaTesting;


@Repository
public class MetodologiaTestingDaoImp implements MetodologiaTestingDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MetodologiaTesting> obtenerMetodologiasTesting() {
	      CriteriaQuery<MetodologiaTesting> criteriaQuery = em.getCriteriaBuilder().createQuery(MetodologiaTesting.class);
	      @SuppressWarnings("unused")
	      Root<MetodologiaTesting> root = criteriaQuery.from(MetodologiaTesting.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void add(MetodologiaTesting metodologia) {
		   em.merge(metodologia);

	}

	@Override
	public void modify(MetodologiaTesting metodologia) {
		  em.merge(metodologia);

	}

	@Override
	public void delete(MetodologiaTesting metodologia) {
		em.remove(em.contains(metodologia) ? metodologia : em.merge(metodologia));

	}
	
    public List<SinonimoMetodologiaTesting> obtenerSinonimosMetodologiaTesting(long idMetodologiaTesting) {
        TypedQuery<SinonimoMetodologiaTesting> query = em.createNamedQuery("SinonimoMetodologiaTesting.obtenerSinonimosMetodologiaTesting", SinonimoMetodologiaTesting.class);
        query.setParameter("idMetodologiaTesting", idMetodologiaTesting);
        List<SinonimoMetodologiaTesting> resultado = query.getResultList();
        return resultado;
    }
}
