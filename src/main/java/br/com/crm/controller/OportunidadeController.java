package br.com.crm.controller;


import br.com.crm.dto.OportunidadeRequestDTO;
import br.com.crm.dto.OportunidadeResponseDTO;
import br.com.crm.enums.OportunidadeStatus;
import br.com.crm.service.OportunidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/oportunidades")
    @CrossOrigin(origins = "*")
    public class OportunidadeController {

        @Autowired
        private OportunidadeService service;


        @PostMapping
        public ResponseEntity<OportunidadeResponseDTO> cadastrar(@RequestBody @Valid OportunidadeRequestDTO dto) {
            var oportunidade = service.criar(dto);
            return ResponseEntity.ok(oportunidade);
        }


        @GetMapping
        public ResponseEntity<List<OportunidadeResponseDTO>> listar() {
            return ResponseEntity.ok(service.listarTodas());
        }


        @PatchMapping("/{id}/status")
        public ResponseEntity<Void> atualizarStatus(@PathVariable Long id, @RequestBody OportunidadeStatus novoStatus) {
            service.mudarStatus(id, novoStatus);
            return ResponseEntity.noContent().build();
        }


        @GetMapping("/total-vendas")
        public ResponseEntity<Double> obterTotalVendas() {
            return ResponseEntity.ok(service.calcularTotalVendasGanhas());
        }


}
