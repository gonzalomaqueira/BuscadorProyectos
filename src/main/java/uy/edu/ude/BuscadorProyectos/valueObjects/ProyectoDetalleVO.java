package uy.edu.ude.BuscadorProyectos.valueObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;

public class ProyectoDetalleVO
{
	private int id;

	private String nombre;

	private int anio;

	private String carrera;

	private int nota;
	
	private ArrayList<String> alumnos;
	
	private ArrayList<String> tutor;
	
	private String resumen;
	
	private String rutaArchivo;
	
	private Date fechaAlta;
	
	private Date fechaUltimaModificacion;

	private List <TecnologiaVO> tecnologia;
	
	private List <ModeloProcesoVO> modeloProceso;
	
	private List <MetodologiaTestingVO> metodologiaTesting;
	
	
	
	
	public ProyectoDetalleVO(int id, String nombre, int anio, String carrera, int nota, ArrayList<String> alumnos, ArrayList<String> tutor,
			String rutaArchivo, String resumen, Date fechaAlta, Date fechaUltimaModificacion,
			List<TecnologiaVO> tecnologia, List<ModeloProcesoVO> modeloProceso,
			List<MetodologiaTestingVO> metodologiaTesting) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.carrera = carrera;
		this.nota = nota;
		this.alumnos = alumnos;
		this.tutor = tutor;
		this.rutaArchivo = rutaArchivo;
		this.resumen = resumen;
		this.fechaAlta = fechaAlta;
		this.fechaUltimaModificacion = fechaUltimaModificacion;
		this.tecnologia = tecnologia;
		this.modeloProceso = modeloProceso;
		this.metodologiaTesting = metodologiaTesting;
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

	public ArrayList<String> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<String> alumnos) {
		this.alumnos = alumnos;
	}

	public ArrayList<String> getTutor() {
		return tutor;
	}

	public void setTutor(ArrayList<String> tutor) {
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

	public List<TecnologiaVO> getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(List<TecnologiaVO> tecnologia) {
		this.tecnologia = tecnologia;
	}

	public List<ModeloProcesoVO> getModeloProceso() {
		return modeloProceso;
	}

	public void setModeloProceso(List<ModeloProcesoVO> modeloProceso) {
		this.modeloProceso = modeloProceso;
	}

	public List<MetodologiaTestingVO> getMetodologiaTesting() {
		return metodologiaTesting;
	}

	public void setMetodologiaTesting(List<MetodologiaTestingVO> metodologiaTesting) {
		this.metodologiaTesting = metodologiaTesting;
	}		
}
