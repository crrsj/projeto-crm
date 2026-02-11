package br.com.crm.controller;

import br.com.crm.entity.Usuario;
import br.com.crm.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario, UriComponentsBuilder uriBuilder) {

        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);

        repository.save(usuario);

        var uri = uriBuilder.path("/api/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body("Usuário cadastrado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        var usuarios = repository.findAll();
        return ResponseEntity.ok(usuarios);
    }
}
