package Membri.springBoot.empleosApp.service;

import java.util.List;

import Membri.springBoot.empleos.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
}
