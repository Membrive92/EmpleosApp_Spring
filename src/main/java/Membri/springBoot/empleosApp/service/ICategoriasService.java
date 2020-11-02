package Membri.springBoot.empleosApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Membri.springBoot.empleosApp.model.*;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	void eliminar (Integer idVacante);
	Page<Categoria>buscarTodas(Pageable page);
}
