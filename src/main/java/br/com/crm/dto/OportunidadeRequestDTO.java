package br.com.crm.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OportunidadeRequestDTO {

    private String titulo;
    private Double valor;
    private Long clienteId;
    private LocalDate dataFechamentoPrevista;

}
