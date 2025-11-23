package br.com.techchallenge.fase2.infrastructure.persistence.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
public class RestauranteJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dono_id")
    private UsuarioJpa dono;

    public RestauranteJpa() {}

    public RestauranteJpa(Long id, String nome, String endereco, String tipoCozinha, String horarioFuncionamento, UsuarioJpa dono) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dono = dono;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public UsuarioJpa getDono() { return dono; }
}