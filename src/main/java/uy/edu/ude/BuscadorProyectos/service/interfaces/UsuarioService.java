package uy.edu.ude.BuscadorProyectos.service.interfaces;

import java.util.List;

import uy.edu.ude.BuscadorProyectos.entidades.Perfil;
import uy.edu.ude.BuscadorProyectos.entidades.Usuario;

public interface UsuarioService
{
    List<Usuario> obtenerUsuarios();
	Usuario buscarUsuario(String user);
	void agregar(String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void modificar(int id, String nombreUsuario, String contrasenia, String nombre, String apellido, String email, Perfil perfil);
	void eliminar(int id);
}