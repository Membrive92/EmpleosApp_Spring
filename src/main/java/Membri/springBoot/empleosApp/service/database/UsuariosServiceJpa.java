package Membri.springBoot.empleosApp.service.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Membri.springBoot.empleosApp.model.Categoria;
import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.repository.UsuariosRepository;
import Membri.springBoot.empleosApp.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepositorio;

	public void guardar(Usuario usuario) {
		usuariosRepositorio.save(usuario);
	}

	public void eliminar(Integer idUsuario) {
		usuariosRepositorio.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepositorio.findAll();
	}

	@Override
	public Page<Usuario> buscarTodas(Pageable page) {
		return usuariosRepositorio.findAll(page);
	}

}
