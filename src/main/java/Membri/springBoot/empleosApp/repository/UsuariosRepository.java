package Membri.springBoot.empleosApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Membri.springBoot.empleosApp.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
