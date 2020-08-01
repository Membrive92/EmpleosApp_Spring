package Membri.springBoot.empleosApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import Membri.springBoot.empleos.model.Vacante;
import Membri.springBoot.empleosApp.service.IVacanteService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
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
	
	
		
	}


