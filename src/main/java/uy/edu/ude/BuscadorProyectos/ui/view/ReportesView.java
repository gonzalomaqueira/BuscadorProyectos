package uy.edu.ude.BuscadorProyectos.ui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;

import uy.edu.ude.BuscadorProyectos.entity.Elemento;
import uy.edu.ude.BuscadorProyectos.entity.Enumerados.TipoElemento;
import uy.edu.ude.BuscadorProyectos.entity.MetodologiaTesting;
import uy.edu.ude.BuscadorProyectos.entity.ModeloProceso;
import uy.edu.ude.BuscadorProyectos.entity.Perfil;
import uy.edu.ude.BuscadorProyectos.entity.Proyecto;
import uy.edu.ude.BuscadorProyectos.entity.Tecnologia;
import uy.edu.ude.BuscadorProyectos.entity.Usuario;
import uy.edu.ude.BuscadorProyectos.service.Fachada;
import uy.edu.ude.BuscadorProyectos.service.interfaces.ElementoService;
import uy.edu.ude.BuscadorProyectos.valueObjects.PerfilVO;

@SpringView
@SpringComponent
public class ReportesView extends ReportesViewDesign implements View{
	@Autowired
	Fachada fachada;
	
	@Autowired
	ElementoService elementoService;
	
	public void enter(ViewChangeEvent event)
	{
		
//		
//		Elemento elemento1 = new Elemento("Elemento 1", true, TipoElemento.TECNOLOGIA);
//		Elemento elemento2 = new Elemento("Elemento 2", true, TipoElemento.METODOLOGIA_TESTING);
//		Elemento elemento3 = new Elemento("Elemento 3", true, TipoElemento.METODOLOGIA_TESTING);
//		Elemento elemento4 = new Elemento("Elemento 4", true, TipoElemento.METODOLOGIA_TESTING);
//		Elemento elemento5 = new Elemento("Elemento 5", true, TipoElemento.METODOLOGIA_TESTING);
//
//		
//		elemento1.getElementosOrigen().add(elemento2);
//		elemento1.getElementosOrigen().add(elemento3);
//		elemento1.getElementosOrigen().add(elemento4);
//		elemento1.getElementosOrigen().add(elemento5);
//		
//		elemento2.getElementosOrigen().add(elemento3);
//		elemento2.getElementosOrigen().add(elemento4);
//		elemento2.getElementosOrigen().add(elemento5);
//		
//		elemento3.getElementosOrigen().add(elemento4);
//		elemento3.getElementosOrigen().add(elemento5);
//		
//		elemento4.getElementosOrigen().add(elemento5);
//
//		elementoService.agregar(elemento1);
//		elementoService.agregar(elemento2);
//		elementoService.agregar(elemento3);
//		elementoService.agregar(elemento4);
//		elementoService.agregar(elemento5);
		
		
		@SuppressWarnings("unused")
		List<Elemento> ListaElementos = elementoService.obtenerElementos();	 
		 
//		Elemento elem = elementoService.obtenerElementoPorId(1);
		
//		List<Elemento> relacionados = elem.getElementoRelacionados();
//		List<Elemento> origenes = elem.getElementosOrigen();
		
		@SuppressWarnings("unused")
		PDDocument pdDoc = null;
//		PDFTextStripper pdfStripper;
//
//		String parsedText;
//		String fileName = "C:\\temp\\Resumenes\\JDBC1.pdf";
//		try {
//
////			pdDoc = PDDocument.load(new File(fileName));
////			pdfStripper = new PDFTextStripper();
////			parsedText = pdfStripper.getText(pdDoc);
////	        String textoOriginal[] = parsedText.split("\\r?\\n");
////	        
////	        Proyecto proyecto= new Proyecto();
////	        proyecto.setDocumentoPorSecciones(fachada.armarDocumentoPorSecciones(textoOriginal));
////	        System.out.println(parsedText);
////	        
////	        
////	        List<Tecnologia> vTecnologiasDelProyecto = fachada.obtenerTecnologiasProyecto(proyecto);	  	        
////	        for(Tecnologia tecnologia : vTecnologiasDelProyecto)
////			{
////	        	System.out.println(tecnologia.toString());
////			}
////	        
////	        List<ModeloProceso> vModelosProcesoDelProyecto = fachada.obtenerModelosProcesoProyecto(proyecto);	  	        
////	        for(ModeloProceso modelo : vModelosProcesoDelProyecto)
////			{
////	        	System.out.println(modelo.toString());
////			}
////	        
////
////	        List<MetodologiaTesting> vMetodologiaTestingDelProyecto = fachada.obtenerMetodologiasTestingProyecto(proyecto);  	        	        
////	        for(MetodologiaTesting metodologia : vMetodologiaTestingDelProyecto)
////			{
////	        	System.out.println(metodologia.toString());
////			}
//	        
//	        /*
//	        for(String linea : proyecto.devolverResumen())
//	        {	        	
//	        	System.out.println(linea);
//	        }
//	        
//	        for(SeccionTexto sec : proyecto.getDocumentoPorSecciones())
//	        {	        	
//	        	sec.listarSeccion();
//	        }
//	        
//	        /*
//	        for(String linea : proyecto.devolverAlumnos())
//	        {
//	        	System.out.println(linea);
//	        }*/
//	        
//	        System.out.println("--------------------------------------------");
//	        System.out.println("--------------------------------------------");
//	        /*
//	        for(String linea : proyecto.devolverTutor())
//	        {
//	        	System.out.println(linea);
//	        }*/
//	        
//	        
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				if (pdDoc != null)
//					pdDoc.close();
//			} catch (Exception e1) {
//				e.printStackTrace();
//			}
//		}
//		link.setCaption("Hola!!!!!");
	}

}
