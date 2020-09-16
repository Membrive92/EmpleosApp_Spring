package Membri.springBoot.empleosApp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Membri.springBoot.empleos.Util.Utiles;
import Membri.springBoot.empleosApp.model.Vacante;
import Membri.springBoot.empleosApp.service.ICategoriasService;
import Membri.springBoot.empleosApp.service.IVacanteService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	
	//ruta declarada en el properties de Spring
	@Value("$empleosApp.ruta.imagenes")
	private String ruta;
	
	@Autowired
	private IVacanteService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> vacantes = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", vacantes );
		
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}

	@PostMapping("/save")
	//RedirectAttributes es el mensaje flash cuando existe una redirrecion
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes msgGuardado, @RequestParam("archivoImagen") MultipartFile multiPart) {
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error : " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utiles.guardarArchivo(multiPart, ruta);
			if(nombreImagen != null) {
				// se ha subido la imagen, procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
        serviceVacantes.guardar(vacante);
        msgGuardado.addFlashAttribute("msg", "Registro guardado con éxito");
		System.out.println("Vacante: " + vacante);

		return "redirect:/vacantes/index";
	}

	/*
	 * @PostMapping("/save") public String guardar(@RequestParam("nombre") String
	 * nombre, @RequestParam("descripcion") String descripcion,
	 * 
	 * @RequestParam("estado") String estado, @RequestParam("fecha") String
	 * fecha, @RequestParam("destacado") int destacado,
	 * 
	 * @RequestParam("sueldo") double sueldo, @RequestParam("detalles") String
	 * detalles) { System.out.println("Vacante: " + nombre);
	 * System.out.println("Descripcion: " + descripcion);
	 * System.out.println("Estado: " + estado);
	 * System.out.println("Fecha de publicacion: " + fecha);
	 * System.out.println("Destacado: " + destacado); System.out.println("Sueldo: "
	 * + sueldo); System.out.println("detalles: " + detalles);
	 * 
	 * return "vacantes/listVacantes"; }
	 */

	// Request Param se utiliza para valores estaticos
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	// Path variable Param se utiliza para valores dinamicos
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {

		Vacante vacante = serviceVacantes.buscarPorId(idVacante);

		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		return "detalle";

	}

	// metodo para configurar el dataBinding para diferentes tipos de datos (Date en
	// este caso)
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}
}
