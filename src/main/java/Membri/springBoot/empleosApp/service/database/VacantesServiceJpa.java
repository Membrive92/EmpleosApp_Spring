package Membri.springBoot.empleosApp.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Membri.springBoot.empleosApp.model.Vacante;
import Membri.springBoot.empleosApp.repository.VacantesRepository;
import Membri.springBoot.empleosApp.service.IVacanteService;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService {
	
	@Autowired
	private VacantesRepository vacantesRepository;

	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepository.findAll();
		
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> opcional = vacantesRepository.findById(idVacante);
		return opcional.orElse(null);
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepository.save(vacante);

	}

	@Override
	public List<Vacante> buscarDestacadas() {
		return vacantesRepository.findByEstadoAndDestacadoOrderByIdDesc("Aprobada", 1);
	}

	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepository.deleteById(idVacante);
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
	
		return vacantesRepository.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		return vacantesRepository.findAll(page);
	}

}
