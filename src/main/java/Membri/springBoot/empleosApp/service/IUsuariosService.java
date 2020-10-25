package Membri.springBoot.empleosApp.service;

import java.util.List;

import Membri.springBoot.empleosApp.model.Usuario;

public interface IUsuariosService {
	List<Usuario> buscarTodos();
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	
		
	
}
