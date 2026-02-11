package br.com.crm.service;

import br.com.crm.dto.OportunidadeRequestDTO;
import br.com.crm.dto.OportunidadeResponseDTO;
import br.com.crm.entity.Cliente;
import br.com.crm.entity.Oportunidade;
import br.com.crm.enums.OportunidadeStatus;
import br.com.crm.repository.ClienteRepository;
import br.com.crm.repository.OportunidadeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public OportunidadeResponseDTO criar(OportunidadeRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para esta oportunidade"));

        Oportunidade oportunidade = modelMapper.map(dto, Oportunidade.class);
        oportunidade.setCliente(cliente);
        oportunidade.setStatus(OportunidadeStatus.ABERTA);

        return modelMapper.map(oportunidadeRepository.save(oportunidade), OportunidadeResponseDTO.class);
    }


    public List<OportunidadeResponseDTO> listarTodas() {
        return oportunidadeRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OportunidadeResponseDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public void mudarStatus(Long id, OportunidadeStatus novoStatus) {
        Oportunidade op = oportunidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oportunidade não encontrada"));
        op.setStatus(novoStatus);
        oportunidadeRepository.save(op);
    }


    public Double calcularTotalVendasGanhas() {
        return oportunidadeRepository.findByStatus(OportunidadeStatus.GANHA).stream()
                .mapToDouble(Oportunidade::getValor)
                .sum();
    }
}
