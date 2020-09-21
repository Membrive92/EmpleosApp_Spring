package Membri.springBoot.empleosApp.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import Membri.springBoot.empleosApp.model.Categoria;
import Membri.springBoot.empleosApp.repository.CategoriasRepository;
import Membri.springBoot.empleosApp.service.ICategoriasService;

@Service
//se usa para determinar este bean como principal al inyectar ICategoriaService desde dos sitios.
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepositorio;

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepositorio.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepositorio.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> opcional = categoriasRepositorio.findById(idCategoria);
		return opcional.orElse(null);
		
	}

}
