package Membri.springBoot.empleosApp.service;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import Membri.springBoot.empleos.model.Categoria;
import Membri.springBoot.empleos.model.Vacante;

@Service
public class CategoriasServiceImpl implements ICategoriasService {
	private List<Categoria> lista = null;

	public CategoriasServiceImpl() {
		
		lista = new LinkedList<Categoria>();
		
		Categoria categoria1 = new Categoria();
		categoria1.setNombre("Recursos Humanos");
		categoria1.setDescripcion("Trabajos relacionados con el area de RRHH");

		
		Categoria categoria2 = new Categoria();
		categoria2.setNombre("Arquitectura");
		categoria2.setDescripcion("Diseño de planos en general y trabajos relacionados.");

		
		Categoria categoria3 = new Categoria();
		categoria3.setNombre("Ventas");
		categoria3.setDescripcion("Ofertas de trabajo relacionado con ventas.");
		
		lista.add(categoria1);
		lista.add(categoria2);
		lista.add(categoria3);
	

	}

	public List<Categoria> buscarTodas() {
// TODO Auto-generated method stub
		return lista;
	}

	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria categoriaBuscada : lista) {
			if (categoriaBuscada.getId() == idCategoria) {
				return categoriaBuscada;
			}

		}
		return null;
	}

	public void guardar(Categoria categoria) {
		lista.add(categoria);

	}

}
