package br.com.crm.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


import java.time.LocalDateTime;

@Data
public class TarefaResponseDTO {
    private Long id;
    @NotBlank(message = "não pode estare em branco.")
    private String descricao;
    @NotNull(message = "não pode ser nulo.")
    private LocalDateTime dataVencimento;
    @NotNull(message = "não pode ser nulo.")
    private boolean concluida;
    @NotBlank(message = "não pode estare em branco.")
    private String prioridade;
    @NotBlank(message = "não pode estare em branco.")
    private String nomeCliente;

}
