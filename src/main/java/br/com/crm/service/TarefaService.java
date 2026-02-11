package br.com.crm.service;

import br.com.crm.dto.TarefaRequestDTO;
import br.com.crm.dto.TarefaResponseDTO;
import br.com.crm.entity.Tarefa;
import br.com.crm.repository.TarefaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public TarefaResponseDTO criar(TarefaRequestDTO dto) {
        Tarefa tarefa = modelMapper.map(dto, Tarefa.class);
        tarefa.setConcluida(false);
        return modelMapper.map(tarefaRepository.save(tarefa), TarefaResponseDTO.class);
    }


    public List<TarefaResponseDTO> listarPendentesDoVendedor(Long usuarioId) {

        return tarefaRepository.findAll().stream()
                .filter(t -> !t.isConcluida())
                .map(t -> modelMapper.map(t, TarefaResponseDTO.class))
                .collect(Collectors.toList());
    }


    public void concluirTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setConcluida(true);
        tarefaRepository.save(tarefa);
    }
}
