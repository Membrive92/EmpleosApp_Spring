package Membri.springBoot.empleosApp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import Membri.springBoot.empleosApp.model.Vacante;

@Service
public class VacanteServiceImpl implements IVacanteService {
	
	private List<Vacante> lista = null;
	public VacanteServiceImpl() {
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//Similar al arraylist pero es mas facil de manipular
			lista = new LinkedList<Vacante>();
			
			try {
				
				//oferta 1
				Vacante vacante1 = new Vacante();
				vacante1.setId(1);
				vacante1.setNombre("Desarrollador Web");
				vacante1.setDescripcion("Solicitamos desarrollador web para proyecto Spring");
				vacante1.setFecha(sdf.parse("08-02-2019"));
				vacante1.setSueldo(12.500);
				vacante1.setDestacado(1);
				vacante1.setImagen("desarrollo.png");
				
				
				//oferta 2
				Vacante vacante2 = new Vacante();
				vacante2.setId(2);
				vacante2.setNombre("Dev Ops");
				vacante2.setDescripcion("Solicitamos un dev Ops para proyectos");
				vacante2.setFecha(sdf.parse("08-12-2016"));
				vacante2.setSueldo(15.500);
				vacante2.setDestacado(0);
				
				
				//oferta 3
				Vacante vacante3 = new Vacante();
				vacante3.setId(3);
				vacante3.setNombre("Testing");
				vacante3.setDescripcion("Solicitamos un QA para proyectos");
				vacante3.setFecha(sdf.parse("08-12-2014"));
				vacante3.setSueldo(17.500);
				vacante3.setDestacado(1);
				vacante3.setImagen("testing.png");
				
				//oferta 4
				Vacante vacante4 = new Vacante();
				vacante4.setId(4);
				vacante4.setNombre("Experto en Angular");
				vacante4.setDescripcion("Solicitamos una persona con experiencia en Angular 9");
				vacante4.setFecha(sdf.parse("08-12-2018"));
				vacante4.setSueldo(17.500);
				vacante4.setDestacado(0);
				
				lista.add(vacante1);
				lista.add(vacante2);
				lista.add(vacante3);
				lista.add(vacante4);
				
				
			}catch(ParseException e) {
				System.out.println("Error: " + e.getMessage());
			}
			
	}

	
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}


	@Override
	public Vacante buscarPorId(Integer idVacante) {
		for (Vacante vacanteBuscada : lista) {
			if(vacanteBuscada.getId() == idVacante) {
				return vacanteBuscada;
			}
			
		}
		return null;
	}


	
	public void guardar(Vacante vacante) {
		lista.add(vacante);
		
	}


	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

}
