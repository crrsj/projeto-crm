package br.com.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;
    @NotBlank(message = "não pode estare em branco.")
    private String nome;
    @NotBlank(message = "não pode estare em branco.")
    private String empresa;
    @Email
    private String email;
    @NotBlank(message = "não pode estare em branco.")
    private String status;
    @NotBlank(message = "não pode estare em branco.")
    private String nomeResponsavel;
}
