package br.com.crm.dto;


import br.com.crm.enums.Prioridade;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TarefaRequestDTO {

    private String descricao;
    private LocalDateTime dataVencimento;
    private Prioridade prioridade;
    private Long clienteId;
}
