package br.com.techchallenge.fase2.infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_usuario_id")
    private TipoUsuarioJpa tipoUsuario;

    public UsuarioJpa() {}

    public UsuarioJpa(Long id, String nome, TipoUsuarioJpa tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public TipoUsuarioJpa getTipoUsuario() { return tipoUsuario; }
}