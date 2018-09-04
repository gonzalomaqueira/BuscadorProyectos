package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
@Repository
public class TecnologiaDaoImp implements TecnologiaDao {

	@PersistenceContext
	   private EntityManager em;
	
	@Override
	public List<Tecnologia> obtenerTecnologias() {
	      CriteriaQuery<Tecnologia> criteriaQuery = em.getCriteriaBuilder().createQuery(Tecnologia.class);
	      @SuppressWarnings("unused")
	      Root<Tecnologia> root = criteriaQuery.from(Tecnologia.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void add(Tecnologia tecnologia) {
		   em.merge(tecnologia);

	}

	@Override
	public void modify(Tecnologia tecnologia) {
		  em.merge(tecnologia);

	}

	@Override
	public void delete(Tecnologia tecnologia) {
		em.remove(em.contains(tecnologia) ? tecnologia : em.merge(tecnologia));

	}
	
    public List<SinonimoTecnologia> obtenerSinonimosTecnologia(long idTecnologia) {
        TypedQuery<SinonimoTecnologia> query = em.createNamedQuery("SinonimoTecnologia.obtenerSinonimosTecnologia", SinonimoTecnologia.class);
        query.setParameter("idTecnologia", idTecnologia);
        List<SinonimoTecnologia> resultado = query.getResultList();
        return resultado;
    }
	

}
