package br.com.techchallenge.fase2.infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_usuario")
public class TipoUsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeTipo;

    public TipoUsuarioJpa() {}

    public TipoUsuarioJpa(Long id, String nomeTipo) {
        this.id = id;
        this.nomeTipo = nomeTipo;
    }

    public Long getId() {
        return id;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }
}