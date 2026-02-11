package br.com.crm.controller;

import br.com.crm.dto.TarefaRequestDTO;
import br.com.crm.dto.TarefaResponseDTO;
import br.com.crm.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService service;


    @PostMapping
    public ResponseEntity<TarefaResponseDTO> cadastrar(@RequestBody @Valid TarefaRequestDTO dto) {
        var tarefa = service.criar(dto);
        return ResponseEntity.ok(tarefa);
    }


    @GetMapping("/pendentes/{usuarioId}")
    public ResponseEntity<List<TarefaResponseDTO>> listarPendentes(@PathVariable Long usuarioId) {
        var tarefas = service.listarPendentesDoVendedor(usuarioId);
        return ResponseEntity.ok(tarefas);
    }


    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Void> concluir(@PathVariable Long id) {
        service.concluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
