package Membri.springBoot.empleosApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
@RequestMapping(value="/index" , method=RequestMethod.GET)
public String mostrarIndex(Model model) {
	return "categorias/listaCategorias";
}

@RequestMapping(value="/create" , method=RequestMethod.GET)
public String crear(Model model) {
	return "categorias/formCategorias";
}

@RequestMapping(value="/save" , method=RequestMethod.POST)
public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion) {
	System.out.println("Categoria: " + nombre);
	System.out.println("Desriocion: " + descripcion);
	return "categorias/listCategorias";
}
}
