package com.javanauta.agendador_tarefas_usuario.infraestructure.repository;

import com.javanauta.agendador_tarefas_usuario.infraestructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
