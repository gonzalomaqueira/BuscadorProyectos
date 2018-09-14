package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.UsuarioDao;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;

@Service
public class UsuarioServiceImp implements UsuarioService {

   @Autowired
   private UsuarioDao usuarioDao;

   @Transactional(readOnly = true)
   @Override
   public List<Usuario> listUsuarios() {
      return usuarioDao.listUsuarios();
   }
   
   @Transactional
   @Override
   public void add(Usuario usuario) {
	   usuarioDao.add(usuario);
   }

   @Transactional
   @Override
   public void modify(Usuario usuario) {
	   usuarioDao.modify(usuario);
   }
   
   @Transactional
   @Override
   public void delete(Usuario usuario) {
	   usuarioDao.delete(usuario);
   }
   
   @Transactional
   @Override
   public Usuario buscarUsuario(String user) {
	   return usuarioDao.buscarUsuario(user);
   }
   
   @Transactional
   @Override
   public void altaUsuario(String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil)
   {
	   Usuario usuario = new Usuario(nombreUsuario, contrasenia, nombre, apellido, email, perfil);
	   this.add(usuario);
   }
   
   @Transactional
   @Override
   public void modificarUsuario(int id, String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil)
   {
	   if(FuncionesTexto.esNuloOVacio(contrasenia))
		  contrasenia=buscarUsuario(nombreUsuario).getContrasenia();
	   
	   Usuario usuario = new Usuario(nombreUsuario, contrasenia, nombre, apellido, email, perfil);
	   usuario.setId(id);
	   this.modify(usuario);
   }
   
   
   @Transactional
   @Override
   public void eliminarUsuario(int id)
   {
	   Usuario usuario = new Usuario();
	   usuario.setId(id);
	   this.delete(usuario);
   }
}