package Membri.springBoot.empleosApp.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Membri.springBoot.empleosApp.model.Vacante;

public interface IVacanteService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	void guardar (Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar (Integer idVacante);
	//Metodo del filtro, consulta SQL
	List<Vacante> buscarByExample(Example<Vacante> example);
	Page<Vacante>buscarTodas(Pageable page);
}
