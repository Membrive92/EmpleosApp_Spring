package Membri.springBoot.empleosApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Membri.springBoot.empleosApp.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	//Query Method buscar por estado "findBy" es palabra reservado y "Estado" es el campo den BD
	List<Vacante> findByEstado(String estado);
	
	//parametros en orden en cuanto al nombre del metodo
	List<Vacante> findByEstadoAndDestacadoOrderByIdDesc(String estado, int destacado);
	
	List<Vacante> findBySueldoBetweenOrderBySueldoDesc(double sueldo1, double sueldo2);
		
	List<Vacante> findByEstadoIn(String[] estado);
	
}
