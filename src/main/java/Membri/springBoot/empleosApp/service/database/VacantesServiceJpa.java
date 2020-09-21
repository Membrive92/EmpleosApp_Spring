package Membri.springBoot.empleosApp.service.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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

}
