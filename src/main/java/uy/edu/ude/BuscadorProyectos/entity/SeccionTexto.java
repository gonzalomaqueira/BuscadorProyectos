package uy.edu.ude.BuscadorProyectos.entity;

import java.util.ArrayList;

public class SeccionTexto 
{
	public String titulo;
	public ArrayList<String> contenido;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArrayList<String> getContenido() {
		return contenido;
	}
	public void setContenido(ArrayList<String> contenido) {
		this.contenido = contenido;
	}
	
	
	public void listarSeccion()
	{
		System.out.println("-------------------");
		System.out.println(titulo);
		System.out.println("");
		for (String c : contenido)
		{
			System.out.println(c);
		}
		System.out.println("");
	}	
}
