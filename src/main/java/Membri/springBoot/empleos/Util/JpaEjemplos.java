package Membri.springBoot.empleos.Util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import Membri.springBoot.empleosApp.model.Categoria;
import Membri.springBoot.empleosApp.model.Perfil;
import Membri.springBoot.empleosApp.model.Vacante;
import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.repository.CategoriasRepository;
import Membri.springBoot.empleosApp.repository.PerfilesRepository;
import Membri.springBoot.empleosApp.repository.UsuariosRepository;
import Membri.springBoot.empleosApp.repository.VacantesRepository;

//Comento el implements, metodo main y run para tener una unico metodo de arranque en lugar de que spring nos pregunte que apliacion lanzar

@SpringBootApplication
//CommandLineRUnner y el metodo Run hace que se ejecute al arrancar la app por linea de comandos
public class JpaEjemplos //implements CommandLineRunner

{

	// inyeccion de instancia
	@Autowired
	private CategoriasRepository repositorioCategorias;

	@Autowired
	private VacantesRepository repositorioVacantes;

	@Autowired
	private UsuariosRepository repositorioUsuarios;

	@Autowired
	private PerfilesRepository repositorioPerfiles;

	/*public static void main(String[] args) {
		SpringApplication.run(JpaEjemplos.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		buscarVacantesVariosEstados();
	} */

	/**
	 * Query Method: Buscar vacantes por varios Estados (In)
	 */
	private void buscarVacantesVariosEstados() {
		String[] estado = new String[] {"Eliminada","Creada"};
		List<Vacante> lista = repositorioVacantes.findByEstadoIn(estado);
		System.out.println("Resgistros: " + lista.size());
		for (Vacante tempovacante : lista) {
			System.out.println(tempovacante.getId() + ": " + tempovacante.getNombre() + ": " + tempovacante.getEstado());
		}
	}
	/**
	 * Query Method: Buscar vacantes por rango de Sueldo (Between)
	 */
	private void buscarVacantesSueldo() {
		List<Vacante> lista = repositorioVacantes.findBySueldoBetweenOrderBySueldoDesc(7000, 14000);
		System.out.println("Resgistros: " + lista.size());
		for (Vacante tempovacante : lista) {
			System.out.println(
					tempovacante.getId() + ": " + tempovacante.getNombre() + ": " + tempovacante.getSueldo() + "€");

		}
	}

	/**
	 * Query Method: Buscar vacantes por Estado y Destacado Ordenado por Id Desc
	 */
	private void buscarVacantesPorEstadoDestacado() {
		List<Vacante> lista = repositorioVacantes.findByEstadoAndDestacadoOrderByIdDesc("Aprobada", 1);
		System.out.println("Resgistros: " + lista.size());
		for (Vacante tempovacante : lista) {
			System.out.println(tempovacante.getId() + ": " + tempovacante.getNombre() + ": " + tempovacante.getEstado()
					+ ": " + tempovacante.getDestacado());
		}
	}

	/**
	 * Query Method: Buscar vacantes por Estado
	 */
	private void buscarVacantesPorEstado() {
		List<Vacante> lista = repositorioVacantes.findByEstado("Aprobada");
		System.out.println("Resgistros: " + lista.size());
		for (Vacante tempovacante : lista) {
			System.out
					.println(tempovacante.getId() + ": " + tempovacante.getNombre() + ": " + tempovacante.getEstado());
		}
	}

	/**
	 * Metodo para buscar un usuario mediante su id
	 */
	private void buscarUsuario() {
		Optional<Usuario> optional = repositorioUsuarios.findById(1);
		optional.ifPresentOrElse((value) -> {
			System.out.println("Usuario:  " + value.getNombre());
			System.out.println("Perfiles asignados");
			for (Perfil perfiles : value.getPerfiles()) {
				System.out.println(perfiles.getPerfil());
			}
		}, () -> {
			System.out.println("Perfil no encontrado. ");
		});

	}

	/**
	 * Crear un usuario con perfil "SUPERVISOR" y "USUARIO"
	 *
	 */
	private void crearUsuarioDosPerfiles() {
		Usuario user = new Usuario();
		user.setNombre("Jose A Membrive");
		user.setEmail("membri@test.com");
		user.setFechaRegistro(new Date());
		user.setUsername("Membri");
		user.setPassword("1111");
		user.setEstado(1);

		Perfil perfil1 = new Perfil();
		perfil1.setId(2);
		Perfil perfil2 = new Perfil();
		perfil2.setId(3);

		user.agregar(perfil1);
		user.agregar(perfil2);

		repositorioUsuarios.save(user);
	}

	/**
	 * Guardar una vacante
	 */
	private void crearPerfilesAplicacion() {
		repositorioPerfiles.saveAll(getPerfilesAplicacion());
	}

	/**
	 *
	 * Guardar una vacante
	 */
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Tester");
		vacante.setDescripcion("Empresa solicita QA");
		vacante.setFecha(new Date());
		vacante.setSueldo(5000.0);
		vacante.setEstado("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("testing.png");
		vacante.setDetalles("<h1> QA mentor </h1>");
		Categoria categoria = new Categoria();
		categoria.setId(8);
		vacante.setCategoria(categoria);
		repositorioVacantes.save(vacante);

	}

	private void buscarVacantes() {
		List<Vacante> lista = repositorioVacantes.findAll();
		for (Vacante vacante : lista) {
			System.out.println(vacante.getId() + " " + vacante.getNombre() + " " + vacante.getCategoria().getNombre());
		}
	}

	/**
	 * Metodo findAll [con Paginacion y Ordenados] - Interfaz
	 * PagingAndSortingRepository
	 */

	private void buscarTodosPaginacionOrdenados() {
		// obtiene el numero de pagina (desde el registro que empieza) y los elementos
		// por pagina ( en este caso del elemento 1 al 5)
		Page<Categoria> page = repositorioCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		// Estos elementos van configurados en funcion de la variable page (de su
		// PageRequest)
		System.out.println("Total Registros: " + page.getTotalElements());
		System.out.println("Total Paginas   " + page.getTotalPages());
		for (Categoria categoria : page.getContent()) {
			System.out.println(categoria.getId() + "  " + categoria.getNombre());
		}

	}

	/**
	 * Metodo findAll [con Paginacion] - Interfaz PagingAndSortingRepository
	 */

	private void buscarTodosPaginacion() {
		// obtiene el numero de pagina (desde el registro que empieza) y los elementos
		// por pagina ( en este caso del elemento 1 al 5)
		Page<Categoria> page = repositorioCategorias.findAll(PageRequest.of(0, 5));
		// Estos elementos van configurados en funcion de la variable page (de su
		// PageRequest)
		System.out.println("Total Registros: " + page.getTotalElements());
		System.out.println("Total Paginas   " + page.getTotalPages());
		for (Categoria categoria : page.getContent()) {
			System.out.println(categoria.getId() + "  " + categoria.getNombre());
		}

	}

	/**
	 * Metodo findAll [Ordenados por un campo] - Interfaz PagingAndSortingRepository
	 */

	private void buscarTodosOrdenados() {
		// Ordenado por el nombre del atributo de la clase, con descending las ordena
		// descendente
		List<Categoria> categorias = repositorioCategorias.findAll(Sort.by("nombre").descending());
		for (Categoria categoria : categorias) {
			System.out.println(categoria.getId() + "  " + categoria.getNombre());
		}
	}

	/**
	 * Metodo saveAll - Interfaz CrudRepository
	 */

	private void guardarTodos() {
		List<Categoria> categorias = getListaCategorias();
		repositorioCategorias.saveAll(categorias);

	}

	/**
	 * Metodo existsById - Interfaz CrudRepository
	 */

	private void existeId() {
		boolean existe = repositorioCategorias.existsById(2);
		System.out.println("La categoria existe: " + existe);
	}

	/**
	 * Metodo findAll - Interfaz JpaRepository
	 */

	private void buscarTodosJpa() {
		List<Categoria> categorias = repositorioCategorias.findAll();
		for (Categoria categoria : categorias) {
			System.out.println(categoria.getId() + "  " + categoria.getNombre());
		}
	}

	/**
	 * Metodo findAll - Interfaz CrudRepository
	 */

	private void buscarTodos() {
		Iterable<Categoria> categorias = repositorioCategorias.findAll();
		for (Categoria categoria : categorias) {
			System.out.println(categoria);
		}
	}

	/**
	 * Metodo findAllById - Interfaz CrudRepository
	 */

	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(4);

		//
		Iterable<Categoria> categorias = repositorioCategorias.findAllById(ids);
		for (Categoria Categorias : categorias) {
			System.out.println(Categorias);
		}

	}

	// Hago un update a BD usando el optional y siguiendo el metodo ifPresentOrElse
	private void modificar() {

		Optional<Categoria> opcional = repositorioCategorias.findById(1);
		opcional.ifPresentOrElse((value) -> {
			Categoria catTmp = value;
			catTmp.setNombre("Backend");
			catTmp.setDescripcion("Desarrollo backend");
			repositorioCategorias.save(catTmp);
			System.out.println("Categoria:  " + value);

		}, () -> {
			System.out.println("Categoria no encontrada ");
		});

	}

	private void buscarPorId() {
		// optional encapsula un objeto para hacer ciertas operaciones, almacena valor
		Optional<Categoria> opcional = repositorioCategorias.findById(1);
		opcional.ifPresentOrElse((value) -> {
			System.out.println("Categoria:  " + value);
		}, () -> {
			System.out.println("Categoria no encontrada ");
		});

	}

	private void guardar() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Front end");
		categoria.setDescripcion("Trabajos de frontal de desarrollo web");
		repositorioCategorias.save(categoria);
		System.out.println(categoria);
	}

	/**
	 * Metodo count - Interfaz CrudRepository
	 */

	private void conteo_entidades() {
		long count = repositorioCategorias.count();
		System.out.println("Total Categorias: " + count);
	}

	/**
	 * Metodo deleteById - Interfaz CrudRepository
	 */

	private void eliminar() {
		int idCategoria = 1;
		repositorioCategorias.deleteById(idCategoria);
	}

	/**
	 * Metodo deleteAllInBatch - Interfaz JpaRepository
	 */

	private void eliminarTodosJpa() {
		repositorioCategorias.deleteAllInBatch();
	}

	/**
	 * Metodo deleteAll - Interfaz CrudRepository
	 */

	private void eliminarTodos() {
		repositorioCategorias.deleteAll();
	}

	/**
	 * Metodo que devuelve una lista de 3 Categorias
	 */
	private List<Categoria> getListaCategorias() {
		List<Categoria> lista = new LinkedList<Categoria>();

		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador blockchain");
		cat1.setDescripcion("Trabajos relacionados con criptomoneda");

		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setNombre("Programador React");
		cat2.setDescripcion("Trabajos relacionados con React Framework");

		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setNombre("Programador Vue.js");
		cat3.setDescripcion("Trabajos relacionados con Vue.js");

		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);

		return lista;
	}

	/**
	 * Metodo que devuelve una lista de objetos Perfil que representa los diferentes
	 * Perfiles o Roles que tendremos en la aplicacion empleos
	 */
	private List<Perfil> getPerfilesAplicacion() {
		List<Perfil> lista = new LinkedList<Perfil>();
		Perfil perfil1 = new Perfil();
		perfil1.setPerfil("SUPERVISOR");

		Perfil perfil2 = new Perfil();
		perfil2.setPerfil("ADMINISTRADOR");

		Perfil perfil3 = new Perfil();
		perfil3.setPerfil("USUARIO");

		lista.add(perfil1);
		lista.add(perfil2);
		lista.add(perfil3);

		return lista;
	}
}
