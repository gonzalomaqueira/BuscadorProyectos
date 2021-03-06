package uy.edu.ude.BuscadorProyectos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uy.edu.ude.BuscadorProyectos.entity.Enumerados.EstadoProyectoEnum;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;

import java.sql.Blob;

@Entity
@Table(name = "Proyectos")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProyecto")
	private int id;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Nombre")
	private String nombre;

	@NotNull
	@Column(name = "Anio")
	private int anio;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Carrera")
	private String carrera;

	@Column(name = "Nota")
	private int nota;

	@Column(name = "Alumnos")
	private ArrayList<String> alumnos;

	@Size(min = 1, max = 255)
	@Column(name = "Tutor")
	private ArrayList<String> tutor;

	@NotNull
	@Column(name = "RutaArchivo")
	private String rutaArchivo;

	@Column(name = "Resumen", columnDefinition="TEXT")
	private String resumen;

	@NotNull
	@Column(name = "FechaAlta")
	private Date fechaAlta;

	@NotNull
	@Column(name = "FechaUltimaModificacion")
	private Date fechaUltimaModificacion;

	@Enumerated(EnumType.STRING)
	private EstadoProyectoEnum estado;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RelProyectoTecnologia", joinColumns = { @JoinColumn(name = "Proyectos") }, inverseJoinColumns = { @JoinColumn(name = "Tecnologias") })
	private List <Tecnologia> tecnologia;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RelProyectoModeloProceso", joinColumns = { @JoinColumn(name = "Proyectos") }, inverseJoinColumns = { @JoinColumn(name = "ModeloProceso") })
	private List <ModeloProceso> modeloProceso;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RelProyectoMetodologiaTesting", joinColumns = { @JoinColumn(name = "Proyectos") }, inverseJoinColumns = { @JoinColumn(name = "MetodologiaTesting") })
	private List <MetodologiaTesting> metodologiaTesting;
	
	@Transient
	private List<SeccionTexto>  DocumentoPorSecciones;

	public Proyecto()
	{
		this.estado = EstadoProyectoEnum.SIN_PROCESAR;
		Date vfecha = new Date();
		this.fechaAlta = vfecha;
		this.fechaUltimaModificacion = vfecha;
	}

	public Proyecto(String nombre, int anio, String carrera, int nota, String rutaArchivo)
	{
		this();
		this.nombre = nombre;
		this.anio = anio;
		this.carrera = carrera;
		this.nota = nota;
		this.rutaArchivo = rutaArchivo;
	}

	public Proyecto(String nombre, int anio, String carrera, int nota, ArrayList<String> alumnos, ArrayList<String> tutor, String rutaArchivo, String resumen)
	{
		this(nombre, anio, carrera, nota, rutaArchivo);
		this.alumnos = alumnos;
		this.tutor = tutor;
		this.resumen = resumen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public ArrayList<String> getAlumnos()
	{ 
		return alumnos; 
	}
	  
	public void setAlumnos(ArrayList<String> alumnos) 
	{
		this.alumnos = alumnos;
	}
 
	public ArrayList<String> getTutor()
	{
		return tutor;
	}

	public void setTutor(ArrayList<String> tutor)
	{
		this.tutor = tutor;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public Enumerados.EstadoProyectoEnum getEstado() {
		return estado;
	}

	public void setEstado(Enumerados.EstadoProyectoEnum estado)
	{
		this.estado = estado;
		this.fechaUltimaModificacion = new Date();
	}

	public List<SeccionTexto> getDocumentoPorSecciones() {
		return DocumentoPorSecciones;
	}

	public void setDocumentoPorSecciones(List<SeccionTexto> documentoPorSecciones) {
		DocumentoPorSecciones = documentoPorSecciones;
	}
	
	public List<Tecnologia> getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(List<Tecnologia> tecnologia) {
		this.tecnologia = tecnologia;
	}

	public List<ModeloProceso> getModeloProceso() {
		return modeloProceso;
	}

	public void setModeloProceso(List<ModeloProceso> modeloProceso) {
		this.modeloProceso = modeloProceso;
	}

	public List<MetodologiaTesting> getMetodologiaTesting() {
		return metodologiaTesting;
	}

	public void setMetodologiaTesting(List<MetodologiaTesting> metodologiaTesting) {
		this.metodologiaTesting = metodologiaTesting;
	}
	
	
	
	
	public ArrayList<String> devolverResumen() 
	{
		if( this.DocumentoPorSecciones!=null ) 
		{
			SeccionTexto secResumen=null;
			for(SeccionTexto sec : this.DocumentoPorSecciones)
			{
				if(sec.getTitulo().trim().equals("Resumen") || sec.getTitulo().trim().equals("Abstract"))
				{
					secResumen= sec;
					break;
				}
			}
			if( secResumen!=null ) 
			{
				return limpiarResumen(secResumen.getContenido());
			}
		}
		return null;
	}

	private ArrayList<String> limpiarResumen(ArrayList<String> contenido)
	{
		int largoList = contenido.size();
		for(int x=largoList-1; x>=0; x--)
		{			
			if( !FuncionesTexto.terminaPunto(contenido.get(x)))
				contenido.remove(x);
			else
				break;
		}
		for(int x=0; x<contenido.size(); x++)
		{
			if(contenido.get(x).trim().equals(""))
			{
				contenido.remove(x);
				x--;
			}
			else
				break;
		}
		return contenido;
	}
	
	public ArrayList<String> devolverAlumnos () 
	{
		if( this.DocumentoPorSecciones!=null ) 
		{
			SeccionTexto secAlumnos=null;
			for(SeccionTexto sec : this.DocumentoPorSecciones)
			{
				if(sec.getTitulo().trim().equals("Alumnos") || sec.getTitulo().trim().equals("Integrantes"))
				{
					secAlumnos = sec;
					break;
				}
			}
			if(secAlumnos!=null)
			{
				return limpiarAlumnos(secAlumnos.getContenido());
			}
		}
		return null;
	}
	
	private ArrayList<String> limpiarAlumnos(ArrayList<String> contenido)
	{		
		contenido = FuncionesTexto.eliminarLineasVacias(contenido);
		contenido = FuncionesTexto.eliminarEspacios(contenido);
		contenido = FuncionesTexto.separarAlumnos(contenido);
		
		return contenido;
	}
	
	public ArrayList<String> devolverTutor()
	{
		if( this.DocumentoPorSecciones!=null ) 
		{
			SeccionTexto secTutor=null;
			for(SeccionTexto sec : this.DocumentoPorSecciones)
			{
				if(sec.getTitulo().trim().equals("Tutor"))
				{
					secTutor = sec;
					break;
				}
			}
			if(secTutor!=null)
			{
				return limpiarTutor(secTutor.getContenido());
			}
		}
		return null;
	}
	
	private ArrayList<String> limpiarTutor(ArrayList<String> contenido)
	{		
		contenido = FuncionesTexto.eliminarLineasVacias(contenido);
		contenido = FuncionesTexto.eliminarEspacios(contenido);
		
		return contenido;
	}
	



	
}
