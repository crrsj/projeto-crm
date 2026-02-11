package br.com.crm.repository;

import br.com.crm.entity.Oportunidade;
import br.com.crm.enums.OportunidadeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OportunidadeRepository extends JpaRepository<Oportunidade,Long> {
    List<Oportunidade> findByStatusAndValorGreaterThan(OportunidadeStatus status, Double valor);
    List<Oportunidade> findByClienteId(Long clienteId);
    @Query("SELECT SUM(o.valor) FROM Oportunidade o WHERE o.status = :status")
    Double somarValorTotalPorStatus(@Param("status") OportunidadeStatus status);
    List<Oportunidade> findByStatus(OportunidadeStatus status);

}
