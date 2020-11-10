package Membri.springBoot.empleosApp.controller;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Membri.springBoot.empleosApp.model.Perfil;
import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.model.Vacante;
import Membri.springBoot.empleosApp.service.ICategoriasService;
import Membri.springBoot.empleosApp.service.IUsuariosService;
import Membri.springBoot.empleosApp.service.IVacanteService;

@Controller
public class HomeController {

	@Autowired
	private IVacanteService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private IUsuariosService serviceUsuarios;

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
		vacante.setSueldo(2500.0);
		model.addAttribute("vacante", vacante);

		return "detalle";

	}

	// declaro el get en la ruta, y le paso una lista con diferentes oficios y los
	// cargo con model en la vista "listado"
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
	// Con Model podemos agregar cualquier tipo de dato al modelo
	public String mostrarHome(Model model) {
		return "home";
	}
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		usuario.setEstado(1); //Activado por defecto
		usuario.setFechaRegistro(new Date()); //Fecha de Registro, la fecha actual del servidor
		
		Perfil perfil = new Perfil();
		perfil.setId(3); //Perfil usuario
		usuario.agregar(perfil);
		
		//Guardamos el usuarion en base de datos
		serviceUsuarios.guardar(usuario);
		
		attributes.addFlashAttribute("msg", "Registro guardado con éxito");
		

		
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("busqueda") Vacante vacante, Model model) {
		System.out.println("Buscnado por: " + vacante);
		
		ExampleMatcher matcher = ExampleMatcher
				//esto hara que en la consulta select use el operador 'Like' en lugar del '='
				.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example = Example.of(vacante, matcher);
		List<Vacante> lista = serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		
		return "home";
	}
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los cambia a Null
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//especificamos con este metodo que si es vacio lo pase a null
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	

	//Agregar al modelo todos los atributos que queramos, disponibles para todos los metodos de este controlador
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteBusqueda = new Vacante();
		//se usa el metodo reset para que no busque la imagen 
		vacanteBusqueda.reset();
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		model.addAttribute("busqueda", vacanteBusqueda);
	}

}
