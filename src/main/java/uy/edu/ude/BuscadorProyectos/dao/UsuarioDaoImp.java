package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import uy.edu.ude.BuscadorProyectos.entity.Usuario;

@Repository
public class UsuarioDaoImp implements UsuarioDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public List<Usuario> listUsuarios() {
      CriteriaQuery<Usuario> criteriaQuery = em.getCriteriaBuilder().createQuery(Usuario.class);
      @SuppressWarnings("unused")
      Root<Usuario> root = criteriaQuery.from(Usuario.class);
      return em.createQuery(criteriaQuery).getResultList();
   }
   
   @Override
   public void add(Usuario usuario) {
      em.merge(usuario);
   }
   
   @Override
   public void modify(Usuario usuario) {
      em.merge(usuario);
   }

   @Override
   public void delete(Usuario usuario) {
	  em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
   }
   
   @Override
   public Usuario buscarUsuario(String usuario) {
       TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario", Usuario.class);
       query.setParameter("usuario", usuario);
       return query.getSingleResult();
   }

}

