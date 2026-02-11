package br.com.crm.controller;

import br.com.crm.dto.LoginDTO;
import br.com.crm.dto.TokenDTO;
import br.com.crm.entity.Usuario;
import br.com.crm.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDTO dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());


        var authentication = manager.authenticate(authenticationToken);


        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());


        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
