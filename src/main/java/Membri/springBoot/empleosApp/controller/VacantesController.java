package Membri.springBoot.empleosApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Membri.springBoot.empleos.model.Vacante;
import Membri.springBoot.empleosApp.service.IVacanteService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	@GetMapping("/create")
	public String crear() {
		
		return "vacantes/formVacante";
	}
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
			@RequestParam("estado") String estado, @RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado,
			@RequestParam("sueldo") double sueldo, @RequestParam("detalles") String detalles) {
		System.out.println("Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estado: " + estado);
		System.out.println("Fecha de publicacion: " + fecha);
		System.out.println("Destacado: " + destacado);
		System.out.println("Sueldo: " + sueldo);
		System.out.println("detalles: " + detalles);
		
		return "vacantes/listVacantes";	
	}
	
	//Request Param se utiliza para valores estaticos
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("id" , idVacante);
		return "mensaje";
	}

	//Path variable Param se utiliza para valores dinamicos
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante" , vacante);
		return "detalle";
		
	}
}
