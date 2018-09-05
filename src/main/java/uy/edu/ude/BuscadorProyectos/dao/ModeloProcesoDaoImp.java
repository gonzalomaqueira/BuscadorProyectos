package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoModeloProceso;

@Repository
public class ModeloProcesoDaoImp implements ModeloProcesoDao {
	

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<ModeloProceso> obtenerModelosProceso() {
	      CriteriaQuery<ModeloProceso> criteriaQuery = em.getCriteriaBuilder().createQuery(ModeloProceso.class);
	      @SuppressWarnings("unused")
	      Root<ModeloProceso> root = criteriaQuery.from(ModeloProceso.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void add(ModeloProceso modelo) {
		   em.merge(modelo);

	}

	@Override
	public void modify(ModeloProceso modelo) {
		  em.merge(modelo);

	}

	@Override
	public void delete(ModeloProceso modelo) {
		em.remove(em.contains(modelo) ? modelo : em.merge(modelo));

	}
	
    public List<SinonimoModeloProceso> obtenerSinonimosModeloProceso(long idModeloProceso) {
        TypedQuery<SinonimoModeloProceso> query = em.createNamedQuery("SinonimoModeloProceso.obtenerSinonimosModeloProceso", SinonimoModeloProceso.class);
        query.setParameter("idModeloProceso", idModeloProceso);
        List<SinonimoModeloProceso> resultado = query.getResultList();
        return resultado;
    }

}
