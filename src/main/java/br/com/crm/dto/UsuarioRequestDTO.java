package br.com.crm.dto;

import br.com.crm.enums.Role;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String username;
    private String password;
    private Role role;
}
