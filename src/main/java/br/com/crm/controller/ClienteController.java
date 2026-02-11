package br.com.crm.controller;

import br.com.crm.dto.ClienteRequestDTO;
import br.com.crm.dto.ClienteResponseDTO;
import br.com.crm.enums.LeadStatus;
import br.com.crm.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO>criar(@RequestBody @Valid ClienteRequestDTO dto){
        var cliente = service.criar(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> buscar(@RequestParam String termo) {
        return ResponseEntity.ok(service.buscarPorNomeOuEmpresa(termo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>>listarClientes(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ClienteResponseDTO>> filtrarPorStatus(@PathVariable LeadStatus status) {
        return ResponseEntity.ok(service.listarPorStatus(status));
    }


    @GetMapping("/dashboard/resumo")
    public ResponseEntity<Map<String, Long>> getResumo() {
        return ResponseEntity.ok(service.obterResumoDashboard());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO dto){
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO>buscarClientePorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody LeadStatus novoStatus) {
        service.atualizarStatus(id, novoStatus);
        return ResponseEntity.noContent().build();
    }
}
