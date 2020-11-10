package Membri.springBoot.empleosApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.service.IUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuariosService serviceUsuarios;
	
		@GetMapping("/index")
		public String mostrarIndex(Model model) {
			List<Usuario> lista = serviceUsuarios.buscarTodos();
			model.addAttribute("usuarios", lista);
			return "usuarios/listUsuarios";
		}
		
		@GetMapping("/indexPaginate")
		public String mostrarIndex(Model model, Pageable page) {
			Page<Usuario> lista = serviceUsuarios.buscarTodas(page);
			model.addAttribute("usuarios", lista);
			return "usuarios/listUsuarios";
		}
	
		@GetMapping("/delete/{id}")
		public String eliminar(@PathVariable("id") int usuario, RedirectAttributes attributes) {
			System.out.println("Borrando Usuario con id: " + usuario);	
			serviceUsuarios.eliminar(usuario);
			attributes.addFlashAttribute("msg","La vacante fue eliminada");
			
			return "redirect:/usuarios/index";
		}
	
	
}
