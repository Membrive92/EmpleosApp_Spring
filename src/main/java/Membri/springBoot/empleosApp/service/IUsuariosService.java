package Membri.springBoot.empleosApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Membri.springBoot.empleosApp.model.Categoria;
import Membri.springBoot.empleosApp.model.Usuario;

public interface IUsuariosService {
	List<Usuario> buscarTodos();
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	Page<Usuario>buscarTodas(Pageable page);
		
	
}
