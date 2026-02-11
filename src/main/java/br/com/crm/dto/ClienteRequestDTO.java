package br.com.crm.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nome;
    private String empresa;
    private String email;
    private String telefone;
    private Long responsavelId;
}
