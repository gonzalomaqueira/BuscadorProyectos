package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.entity.Categoria;
import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.valueObjects.CategoriaVO;

@Repository
public class TecnologiaDaoImp implements TecnologiaDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Tecnologia> obtenerTecnologias() 
	{
	      CriteriaQuery<Tecnologia> criteriaQuery = em.getCriteriaBuilder().createQuery(Tecnologia.class);
	      @SuppressWarnings("unused")
	      Root<Tecnologia> root = criteriaQuery.from(Tecnologia.class);
	      return em.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public void add(Tecnologia tecnologia) 
	{
		em.merge(tecnologia);
	}

	@Override
	public void modify(Tecnologia tecnologia) 
	{
		  em.merge(tecnologia);
	}


	@Override
	public void delete(Tecnologia tecnologia)
	{
		em.remove(em.contains(tecnologia) ? tecnologia : em.merge(tecnologia));		
	}
	
    public List<SinonimoTecnologia> obtenerSinonimosTecnologia(int idTecnologia) 
    {
        TypedQuery<SinonimoTecnologia> query = em.createNamedQuery("SinonimoTecnologia.obtenerSinonimosTecnologia", SinonimoTecnologia.class);
        query.setParameter("idTecnologia", new Tecnologia(idTecnologia));
        List<SinonimoTecnologia> resultado = query.getResultList();
        return resultado;
    }
    
    public List<Tecnologia> obtenerTecnologiasPorCategoria(int idCategoria)
    {
    	TypedQuery<Tecnologia> query = em.createNamedQuery("Tecnologia.obtenerTecnologiasPorCategoria", Tecnologia.class);
        query.setParameter("idCategoria", new Categoria(idCategoria));
        List<Tecnologia> resultado = query.getResultList();
        return resultado;
    }
    
    @Override
    public Tecnologia obtenerTecnologiaPorId(int idTecnologia) {
    	Object persistentInstance = em.find(Tecnologia.class, idTecnologia);
    	return (Tecnologia)persistentInstance;
    }
    
    @Override
    public void agregarSinonimo(SinonimoTecnologia sinonimo)
    {
    	em.merge(sinonimo);
    }
    
    @Override
    public void modificarSinonimo(SinonimoTecnologia sinonimo)
    {
    	Object persistentInstance= em.find(SinonimoTecnologia.class, sinonimo.getId());
    	SinonimoTecnologia aux= (SinonimoTecnologia) persistentInstance;
    	aux.setNombre(sinonimo.getNombre());
    	em.merge(aux);		
    }
    
    @Override
    public void eliminarSinonimo(SinonimoTecnologia sinonimo)
    {
    	Object persistentInstance= em.find(SinonimoTecnologia.class, sinonimo.getId());
    	em.remove(em.contains(persistentInstance) ? persistentInstance : em.merge(persistentInstance));		
    }
}
