package br.com.crm.service;

import br.com.crm.dto.UsuarioRequestDTO;
import br.com.crm.dto.UsuarioResponseDTO;
import br.com.crm.entity.Usuario;
import br.com.crm.enums.Role;
import br.com.crm.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Este nome de usuário já está em uso.");
        }

        Usuario usuario = modelMapper.map(dto, Usuario.class);


        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));


        if (usuario.getRole() == null) {
            usuario.setRole(Role.USER);
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return modelMapper.map(salvo, UsuarioResponseDTO.class);
    }


    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }


    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }


    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setUsername(dto.getUsername());


        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return modelMapper.map(usuarioRepository.save(usuarioExistente), UsuarioResponseDTO.class);
    }


    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }
}

