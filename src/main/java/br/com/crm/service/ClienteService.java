package br.com.crm.service;

import br.com.crm.dto.ClienteRequestDTO;
import br.com.crm.dto.ClienteResponseDTO;
import br.com.crm.entity.Cliente;
import br.com.crm.enums.LeadStatus;
import br.com.crm.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Já existe um cliente cadastrado com este e-mail.");
        }

        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente.setStatus(LeadStatus.NOVO);
        cliente.setDataCriacao(LocalDateTime.now());
        return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
    }


    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }


    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }


    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        modelMapper.map(dto, clienteExistente);

        return modelMapper.map(clienteRepository.save(clienteExistente), ClienteResponseDTO.class);
    }


    public void excluir(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Não é possível excluir: Cliente inexistente");
        }
        clienteRepository.deleteById(id);
    }

    public List<ClienteResponseDTO> listarPorResponsavel(Long usuarioId) {
        return clienteRepository.findByResponsavelId(usuarioId).stream()
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }


    public List<ClienteResponseDTO> listarPorStatus(LeadStatus status) {
        return clienteRepository.findByStatus(status).stream()
                .map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }


    public Map<String, Long> obterResumoDashboard() {
        Map<String, Long> resumo = new HashMap<>();
        resumo.put("total", clienteRepository.count());
        resumo.put("novos", clienteRepository.countByStatus(LeadStatus.NOVO));
        resumo.put("emAtendimento", clienteRepository.countByStatus(LeadStatus.EM_CONTATO));
        resumo.put("convertidos", clienteRepository.countByStatus(LeadStatus.CONVERTIDO));
        return resumo;
    }

    @Transactional
    public void atualizarStatus(Long id, LeadStatus novoStatus) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setStatus(novoStatus);
        clienteRepository.save(cliente);
    }

    public List<ClienteResponseDTO> buscarPorNomeOuEmpresa(String termo) {

        return clienteRepository.findByNomeContainingIgnoreCaseOrEmpresaContainingIgnoreCase(termo, termo)
                .stream()
                .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }


    public boolean verificarSeEmailExiste(String email) {
        return clienteRepository.existsByEmail(email);
    }


    public long contarLeadsRecentes(int dias) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);
        return clienteRepository.countByDataCriacaoAfter(dataLimite);
    }
}
