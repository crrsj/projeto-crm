package br.com.crm.repository;

import br.com.crm.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {

    List<Tarefa> findByClienteIdAndConcluidaFalse(Long clienteId);
    List<Tarefa> findByDataVencimentoBetween(LocalDateTime inicio, LocalDateTime fim);
}
