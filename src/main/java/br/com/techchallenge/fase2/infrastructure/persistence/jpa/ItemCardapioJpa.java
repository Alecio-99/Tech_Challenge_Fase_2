package br.com.techchallenge.fase2.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_cardapio")
public class ItemCardapioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean somenteNoLocal;
    private String fotoPath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurante_id")
    private RestauranteJpa restaurante;

    public ItemCardapioJpa() {}

    public ItemCardapioJpa(Long id, String nome, String descricao, BigDecimal preco, boolean somenteNoLocal, String fotoPath, RestauranteJpa restaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.somenteNoLocal = somenteNoLocal;
        this.fotoPath = fotoPath;
        this.restaurante = restaurante;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public boolean isSomenteNoLocal() { return somenteNoLocal; }
    public String getFotoPath() { return fotoPath; }
    public RestauranteJpa getRestaurante() { return restaurante; }
}
