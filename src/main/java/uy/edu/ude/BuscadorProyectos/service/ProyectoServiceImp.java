package uy.edu.ude.BuscadorProyectos.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uy.edu.ude.BuscadorProyectos.dao.ProyectoDao;
import uy.edu.ude.BuscadorProyectos.entity.ElementoProyecto;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.SeccionTexto;
import uy.edu.ude.BuscadorProyectos.entity.Sinonimo;
import uy.edu.ude.BuscadorProyectos.entity.SinonimoTecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.utils.FuncionesTexto;

@Service
public class ProyectoServiceImp implements ProyectoService {

	@Autowired
	private ProyectoDao proyectoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Proyecto> obtenerProyectos() {
		return proyectoDao.listarProyectos();
	}

	@Transactional
	@Override
	public void agregar(Proyecto proyecto) {
		proyectoDao.add(proyecto);
	}

	@Transactional
	@Override
	public void modificar(Proyecto proyecto) {
		proyectoDao.modify(proyecto);
	}

	@Transactional
	@Override
	public void borrar(Proyecto proyecto) {
		proyectoDao.delete(proyecto);
	}
	
	
	@Transactional
	@Override
	public void altaProyecto(String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
	   Proyecto proyecto = new Proyecto(nombre, anio, carrera, nota, rutaArchivo);
	   this.agregar(proyecto);
	}

	@Transactional
	@Override
	public void modificarProyecto(int id, String nombre, int anio, String carrera, int nota, String rutaArchivo) 
	{
		Proyecto proy= this.obtenerProyectoPorId(id);
		proy.setNombre(nombre);
		proy.setAnio(anio);
		proy.setCarrera(carrera);
		proy.setNota(nota);
		proyectoDao.modify(proy);
	}
	
	@Transactional
	@Override
	public void modificarCompleto(int id, String nombre, int anio, String carrera, int nota, 
			String resumen, ArrayList<String> alumnos, ArrayList<String> tutor)
	{
		Proyecto proy= this.obtenerProyectoPorId(id);
		proy.setNombre(nombre);
		proy.setAnio(anio);
		proy.setCarrera(carrera);
		proy.setNota(nota);
		proy.setResumen(resumen);
		proy.setAlumnos(alumnos);
		proy.setTutor(tutor);
		proyectoDao.modify(proy);
	}
	
	
	@Transactional
	@Override
	public void borrarProyecto(int id) 
	{
		Proyecto proyecto = proyectoDao.obtenerProyectoPorId(id);
		for (Tecnologia tec: proyecto.getTecnologia())
		{
			tec.getProyectos().remove(proyecto);
		}
		proyecto.getTecnologia().removeAll(proyecto.getTecnologia());
		this.borrar(proyecto);
	}
	
	@Transactional
	@Override
	public Proyecto obtenerProyectoPorId(int idProyecto)
	{
		Proyecto proy = proyectoDao.obtenerProyectoPorId(idProyecto);
		if (proy.getTecnologia() == null || proy.getTecnologia().isEmpty())
		{
			proy.setTecnologia(new ArrayList<Tecnologia>());
		}
		if (proy.getModeloProceso() == null || proy.getModeloProceso().isEmpty())
		{
			proy.setModeloProceso(new ArrayList<ModeloProceso>());
		}
		if (proy.getMetodologiaTesting() == null || proy.getMetodologiaTesting().isEmpty())
		{
			proy.setMetodologiaTesting(new ArrayList<MetodologiaTesting>());
		}
		for(Tecnologia tec : proy.getTecnologia())
		{
			if (tec.getSinonimos() == null || tec.getSinonimos().isEmpty())
				tec.setSinonimos(new ArrayList<SinonimoTecnologia>());
		}
		
		return proy;
	}
	
	public List<SeccionTexto> armarDocumentoPorSecciones(String textoOriginal[])
	{
		List<SeccionTexto> documentoPorSecciones = new ArrayList<SeccionTexto>();
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTitulo = false;
		
        documentoPorSecciones.add(armarSeccionAlumnos(textoOriginal));
        documentoPorSecciones.add(armarSeccionTutor(textoOriginal));
         
        List<String> Textolista = new ArrayList<String>(Arrays.asList(textoOriginal));
        
        for (String linea : textoOriginal)
        {
        	if (!FuncionesTexto.esTituloResumen(linea))
        	{
        		Textolista.remove(0);
        	}
        	else
        		break;
        }
        
		for (String linea : Textolista)
        {
    	    if (FuncionesTexto.esTitulo(linea))
    	    {
    	    	encontreTitulo = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    		documentoPorSecciones.add(seccion);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTitulo && contenido != null)
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
			documentoPorSecciones.add(seccion);
		}
		
		return documentoPorSecciones;
	}
	
	public SeccionTexto armarSeccionAlumnos(String texto[])
	{
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTituloAlumno = false;
        
		for (String linea : texto)
        {
    	    if (FuncionesTexto.esTituloTutor(linea))
    	    	break;
    	    
			if (FuncionesTexto.esTituloAlumnos(linea))
    	    {
    	    	encontreTituloAlumno = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTituloAlumno && !FuncionesTexto.esTituloTutor(linea))
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
		}
		return seccion;
	}
	
	public SeccionTexto armarSeccionTutor(String texto[])
	{
        SeccionTexto seccion = null;
        ArrayList<String> contenido = new ArrayList<String>();
        boolean encontreTituloAlumno = false;
        
		for (String linea : texto)
        {
    	    if (FuncionesTexto.esTituloResumen(linea))
    	    	break;
    	    
			if (FuncionesTexto.esTituloTutor(linea))
    	    {
    	    	encontreTituloAlumno = true;
    	    	if (seccion != null)
    	    	{
    	    		seccion.setContenido(contenido);
    	    	}
    	    	seccion = new SeccionTexto();
    	    	contenido = new ArrayList<String>();
    	    	seccion.setTitulo(linea);
			}
    	    else
    	    {
    	    	if (encontreTituloAlumno && !FuncionesTexto.esTituloResumen(linea))
    	    	{
    	    		contenido.add(linea);
    	    	}        	    	
    	    }
        }
		if (seccion != null)
		{
			seccion.setContenido(contenido);
		}
		return seccion;
	}	
	
	@Transactional
	@Override
	public List<Tecnologia> obtenerTecnologiasProyecto(Proyecto proyecto, List<Tecnologia> tecnologias)
	{
		List<ElementoProyecto> vElementos = new ArrayList<ElementoProyecto>();
		for (Tecnologia tec: tecnologias)
		{
			vElementos.add(tec);
		}		
		List<ElementoProyecto> vElementosProyecto = obtenerElementosProyecto (proyecto, vElementos);
		
		List<Tecnologia> vRetorno = new ArrayList<Tecnologia>();
		for (ElementoProyecto elem: vElementosProyecto)
		{
			vRetorno.add((Tecnologia)elem);
		}		
		return vRetorno;
	}
	
	@Transactional
	@Override
	public List<MetodologiaTesting> obtenerMetodologiasTestingProyecto(Proyecto proyecto, List<MetodologiaTesting> metodologiasTesting)
	{
		List<ElementoProyecto> vElementos = new ArrayList<ElementoProyecto>();
		for (MetodologiaTesting met: metodologiasTesting)
		{
			vElementos.add(met);
		}		
		List<ElementoProyecto> vElementosProyecto = obtenerElementosProyecto (proyecto, vElementos);
		
		List<MetodologiaTesting> vRetorno = new ArrayList<MetodologiaTesting>();
		for (ElementoProyecto elem: vElementosProyecto)
		{
			vRetorno.add((MetodologiaTesting)elem);
		}		
		return vRetorno;
	}
	
	@Transactional
	@Override
	public List<ModeloProceso> obtenerModelosProcesoProyecto(Proyecto proyecto, List<ModeloProceso> modelosProceso)
	{
		List<ElementoProyecto> vElementos = new ArrayList<ElementoProyecto>();
		for (ModeloProceso mod: modelosProceso)
		{
			vElementos.add(mod);
		}		
		List<ElementoProyecto> vElementosProyecto = obtenerElementosProyecto (proyecto, vElementos);
		
		List<ModeloProceso> vRetorno = new ArrayList<ModeloProceso>();
		for (ElementoProyecto elem: vElementosProyecto)
		{
			vRetorno.add((ModeloProceso)elem);
		}		
		return vRetorno;
	}
	
	
	
	private List<ElementoProyecto> obtenerElementosProyecto (Proyecto proyecto, List<ElementoProyecto> vElementos)
	{
		boolean vEncontroElemento = false;
		
		List<ElementoProyecto> vListaRetorno = new ArrayList<ElementoProyecto>();
		if (proyecto.getDocumentoPorSecciones() != null)
		{
			for(ElementoProyecto elemento : vElementos)
			{
				for(SeccionTexto seccion : proyecto.getDocumentoPorSecciones())
				{
					if (vEncontroElemento)
					{
						vEncontroElemento = false;
						break;
					}
					if(FuncionesTexto.seccionContieneTexto(seccion, elemento.getNombre()))
					{
						vListaRetorno.add(elemento);
						break;
					}
					else
					{
						for (Sinonimo sinonimo: elemento.getSinonimos())
						{
							if(FuncionesTexto.seccionContieneTexto(seccion, sinonimo.getNombre()))
							{
								vListaRetorno.add(elemento);
								vEncontroElemento = true;
								break;
							}
						}
					}
				}	
			}
		}
		return vListaRetorno;
	}
	
	@Override
	public String[] obtenerTextoOriginalProyecto(Proyecto proyecto) {
		
		PDDocument pdDoc = null;
		PDFTextStripper pdfStripper;
		String parsedText = null;
		String fileName = proyecto.getRutaArchivo();
		try 
		{
			pdDoc = PDDocument.load(new File(fileName));
			pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
			if (pdDoc != null)
				pdDoc.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			try {
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
		
        String textoOriginal[] = parsedText.split("\\r?\\n");
		return textoOriginal;
	}
	

}
