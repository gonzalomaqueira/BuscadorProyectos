package uy.edu.ude.BuscadorProyectos.dao.implementaciones;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;

@Repository
public class UsuarioDaoImp implements UsuarioDao 
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void agregar(Usuario usuario)
	{
		em.merge(usuario);
	}
   
	@Override
	public void modificar(Usuario usuario)
	{
		em.merge(usuario);
	}

	@Override
	public void eliminar(Usuario usuario)
	{
		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
	}
	
	@Override
	public List<Usuario> obtenerUsuarios()
    {
		CriteriaQuery<Usuario> criteriaQuery = em.getCriteriaBuilder().createQuery(Usuario.class);
		@SuppressWarnings("unused")
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		return em.createQuery(criteriaQuery).getResultList();
    }
   
	@Override
	public Usuario obtenerUsuarioPorId(int id)
	{
		return (Usuario)em.find(Usuario.class, id);
	}
	
	@Override
	public Usuario buscarUsuario(String usuario)
	{
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class);
		query.setParameter("usuario", usuario);
		return query.getSingleResult();
	}   
}

