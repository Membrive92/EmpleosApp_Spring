package Membri.springBoot.empleosApp.service;

import java.util.List;

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


}
