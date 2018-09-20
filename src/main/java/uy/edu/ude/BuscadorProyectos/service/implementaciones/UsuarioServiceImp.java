package uy.edu.ude.BuscadorProyectos.service.implementaciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.interfaces.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;
import uy.edu.ude.BuscadorProyectos.service.interfaces.UsuarioService;
import uy.edu.ude.BuscadorProyectos.utiles.FuncionesTexto;

@Service
public class UsuarioServiceImp implements UsuarioService 
{
   @Autowired
   private UsuarioDao usuarioDao;

   @Transactional(readOnly = true)
   @Override
   public List<Usuario> obtenerUsuarios()
   {
      return usuarioDao.obtenerUsuarios();
   }
   
   @Transactional
   @Override
   public Usuario buscarUsuario(String usuario)
   {
	   return usuarioDao.buscarUsuario(usuario);
   }
   
   @Transactional
   @Override
   public void agregar(String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil)
   {
	   Usuario usuario = new Usuario(nombreUsuario, contrasenia, nombre, apellido, email, perfil);
	   usuarioDao.agregar(usuario);
   }
   
   @Transactional
   @Override
   public void modificar(int id, String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil)
   {
	   if(FuncionesTexto.esNuloOVacio(contrasenia))
	   {
		  contrasenia=buscarUsuario(nombreUsuario).getContrasenia();
	   }	   
	   Usuario usuario = new Usuario(nombreUsuario, contrasenia, nombre, apellido, email, perfil);
	   usuario.setId(id);
	   usuarioDao.modificar(usuario);
   }   
   
   @Transactional
   @Override
   public void eliminar(int id)
   {
	   Usuario usuario = new Usuario();
	   usuario.setId(id);
	   usuarioDao.eliminar(usuario);
   }
}