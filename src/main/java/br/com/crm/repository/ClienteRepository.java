package br.com.crm.repository;

import br.com.crm.entity.Cliente;
import br.com.crm.enums.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByResponsavelId(Long usuarioId);
    List<Cliente> findByStatus(LeadStatus status);
    List<Cliente> findByNomeContainingIgnoreCaseOrEmpresaContainingIgnoreCase(String nome, String empresa);
    long countByStatus(LeadStatus status);
    long countByDataCriacaoAfter(LocalDateTime data);
    boolean existsByEmail(String email);

}
