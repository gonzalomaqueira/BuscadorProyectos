package uy.edu.ude.BuscadorProyectos.service;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;

public interface UsuarioService {
	
    List<Usuario> obtenerUsuarios();
    void agregar(Usuario usuario);
	void modificar(Usuario usuario);
	void borrar(Usuario usuario);
	Usuario buscarUsuario(String user);
	void altaUsuario(String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void modificarUsuario(int id, String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void eliminarUsuario(int id);

}