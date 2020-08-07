package Membri.springBoot.empleosApp.service;

import java.util.List;

import Membri.springBoot.empleos.model.Vacante;

public interface IVacanteService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	
}
