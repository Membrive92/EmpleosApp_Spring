package Membri.springBoot.empleosApp.service.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Membri.springBoot.empleosApp.model.Usuario;
import Membri.springBoot.empleosApp.repository.UsuariosRepository;
import Membri.springBoot.empleosApp.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepo;

	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

}
