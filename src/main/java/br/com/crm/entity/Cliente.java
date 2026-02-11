package br.com.crm.entity;

import br.com.crm.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String empresa;
    private String email;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Oportunidade> oportunidades;

    private LocalDateTime dataCriacao = LocalDateTime.now();
}
