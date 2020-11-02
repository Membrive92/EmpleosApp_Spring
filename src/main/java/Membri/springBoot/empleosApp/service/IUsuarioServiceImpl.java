package Membri.springBoot.empleosApp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Membri.springBoot.empleosApp.model.Categoria;
import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.model.Vacante;

public class IUsuarioServiceImpl implements IUsuariosService {
	
	private List<Usuario> lista = null;
	
	

	@Override
	public List<Usuario> buscarTodos() {
		
		return lista;
	}

	@Override
	public void guardar(Usuario usuario) {
	lista.add(usuario);
		
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}


}
