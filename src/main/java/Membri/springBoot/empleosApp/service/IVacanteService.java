package Membri.springBoot.empleosApp.service;

import java.util.List;

import Membri.springBoot.empleosApp.model.Vacante;

public interface IVacanteService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	
	void guardar (Vacante vacante);
}
