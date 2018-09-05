package uy.edu.ude.BuscadorProyectos.valueObjects;

public class UsuarioVO {

	private Long id;
	private String usuario;
	private String nombre;
	private String apellido;
	private String email;
	private PerfilVO perfil;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PerfilVO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}	
}
