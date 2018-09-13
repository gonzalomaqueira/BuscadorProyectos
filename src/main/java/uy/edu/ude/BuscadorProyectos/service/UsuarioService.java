package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;

public interface UsuarioService {
	
    List<Usuario> listUsuarios();
    void add(Usuario usuario);
	void modify(Usuario usuario);
	void delete(Usuario usuario);
	Usuario buscarUsuario(String user);
	void altaUsuario(String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void modificarUsuario(int id, String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void eliminarUsuario(int id);

}