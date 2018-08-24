package uy.edu.ude.BuscadorProyectos.dao;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Usuario;

public interface UsuarioDao {   
   List<Usuario> listUsuarios();
   void add(Usuario usuario);
   void modify(Usuario usuario);
   void delete(Usuario usuario);
   Usuario buscarUsuario(String user);
}