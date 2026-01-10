package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;

import java.math.BigDecimal;

public class ItemCardapio {

    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final boolean somenteNoLocal;
    private final String fotoPath;
    private final Restaurante restaurante;

    public ItemCardapio(
            Long id,
            String nome,
            String descricao,
            BigDecimal preco,
            boolean somenteNoLocal,
            String fotoPath,
            Restaurante restaurante
    ) {
        validar(nome, descricao, preco, fotoPath, restaurante);
        this.id = id;
        this.nome = nome.trim();
        this.descricao = descricao.trim();
        this.preco = preco;
        this.somenteNoLocal = somenteNoLocal;
        this.fotoPath = fotoPath.trim();
        this.restaurante = restaurante;
    }

    private void validar(String nome, String descricao, BigDecimal preco, String fotoPath, Restaurante restaurante) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DomainException("Nome do item do cardápio é obrigatório.");
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new DomainException("Descrição do item do cardápio é obrigatória.");
        }
        
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("Preço deve ser maior que zero.");
        }
        
        if (fotoPath == null || fotoPath.trim().isEmpty()) {
            throw new DomainException("Foto do prato deve ter um caminho válido.");
        }
        
        if (restaurante == null) {
            throw new DomainException("Item do cardápio deve pertencer a um restaurante.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public boolean isSomenteNoLocal() {
        return somenteNoLocal;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
}
