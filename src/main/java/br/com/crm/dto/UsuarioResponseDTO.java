package br.com.crm.dto;

import br.com.crm.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    @NotBlank(message = "não pode estare em branco.")
    private String username;
    private Role role;
}
