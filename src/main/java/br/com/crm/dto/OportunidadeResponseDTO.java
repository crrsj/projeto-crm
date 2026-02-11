package br.com.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OportunidadeResponseDTO {

    private Long id;
    @NotBlank(message = "não pode estare em branco.")
    private String titulo;
    @NotNull(message = "não pode ser nulo.")
    private Double valor;
    @NotBlank(message = "não pode estare m branco.")
    private String status;
    @NotBlank(message = "não pode estare em branco.")
    private String nomeCliente;
    private LocalDate dataFechamentoPrevista;
}
