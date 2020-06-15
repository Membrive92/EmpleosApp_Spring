package Membri.springBoot.empleosApp.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	//Con Model podemos agregar cualquier tipo de  dato al modelo
	public String mostrarHome(Model model) {
		//añadimos a la variable "mensaje" la cadena "Empleos App"
		model.addAttribute("mensaje", "Empleos App");
		model.addAttribute("fecha", new Date());
		return "home";
	}

}
