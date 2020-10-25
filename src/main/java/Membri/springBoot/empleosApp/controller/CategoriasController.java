package Membri.springBoot.empleosApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Membri.springBoot.empleosApp.model.*;
import Membri.springBoot.empleosApp.service.ICategoriasService;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

	@Autowired
	private ICategoriasService serviceCategoria;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> categorias = serviceCategoria.buscarTodas();
		model.addAttribute("categorias", categorias);
		return "categorias/listCategorias";
	}

	@PostMapping("/save")
	// RedirectAttributes es el mensaje flash cuando existe una redirrecion
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes msgGuardado) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error : " + error.getDefaultMessage());
			}
			return "categorias/listCategorias";
		}
		serviceCategoria.guardar(categoria);
		msgGuardado.addFlashAttribute("msg", "Registro guardado con éxito");
		System.out.println("Categoria: " + categoria);

		return "redirect:/categorias/index";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Model model) {
		return "categorias/formCategorias";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {		
		// Eliminamos la categoria.
		serviceCategoria.eliminar(idCategoria);			
		attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
		return "redirect:/categorias/index";
	}

}
