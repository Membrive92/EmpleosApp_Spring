package Membri.springBoot.empleosApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import Membri.springBoot.empleos.model.Vacante;

@Controller
public class HomeController {
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	@GetMapping("/detalles")
	public String mostrarDetalles(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Pintor");
		vacante.setDescripcion("Se solicita un pintor para restaurar una fachada");
		vacante.setFecha(new Date());
		vacante.setSalario(2500.0);
		model.addAttribute("vacante", vacante);
		
		return "detalle";
		
	}
	
	//declaro el get en la ruta, y le paso una lista con diferentes oficios y los cargo con model en la vista "listado"
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Desarrollador Web");
		lista.add("Contable");
		lista.add("Mozo de Almacen");
		lista.add("Veterinario");
		lista.add("Pintor");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
	}
	
	
	@GetMapping("/")
	//Con Model podemos agregar cualquier tipo de  dato al modelo
	public String mostrarHome(Model model) {
		//añadimos a la variable "mensaje" la cadena "Empleos App"
	/*	model.addAttribute("mensaje", "Empleos App");
		model.addAttribute("fecha", new Date()); */
		String nombre = "Auxiliar de Contabilidad";
		Date fechaPublic = new Date();
		double salario = 9000.0;
		boolean vigente = true;
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("fechaPublic", fechaPublic);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		
		return "home";
	}
	
	/**
	 * Metodo que devuelve una lista de objetos tipo vacante
	 * @return
	 * @throws java.text.ParseException 
	 */
	private List<Vacante> getVacantes() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		
		try {
			
			//oferta 1
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Desarrollador Web");
			vacante1.setDescripcion("Solicitamos desarrollador web para proyecto Spring");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setSalario(12.500);
			
			//oferta 2
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Dev Ops");
			vacante2.setDescripcion("Solicitamos un dev Ops para proyectos");
			vacante2.setFecha(sdf.parse("08-12-2016"));
			vacante2.setSalario(15.500);
			
			//oferta 3
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Testing");
			vacante3.setDescripcion("Solicitamos un QA para proyectos");
			vacante3.setFecha(sdf.parse("08-12-2014"));
			vacante3.setSalario(17.500);
			
			//oferta 4
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Experto en Angular");
			vacante4.setDescripcion("Solicitamos una persona con experiencia en Angular 9");
			vacante4.setFecha(sdf.parse("08-12-2018"));
			vacante4.setSalario(17.500);
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
			
		}catch(ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return lista;
		
	}

}
