package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;

public class Restaurante {

    private final Long id;
    private final String nome;
    private final String endereco;
    private final String tipoCozinha;
    private final String horarioFuncionamento;
    private final Usuario dono;

    public Restaurante(
            Long id,
            String nome,
            String endereco,
            String tipoCozinha,
            String horarioFuncionamento,
            Usuario dono
    ) {
        validar(nome, endereco, tipoCozinha, horarioFuncionamento, dono);
        this.id = id;
        this.nome = nome.trim();
        this.endereco = endereco.trim();
        this.tipoCozinha = tipoCozinha.trim();
        this.horarioFuncionamento = horarioFuncionamento.trim();
        this.dono = dono;
    }

    private void validar(
            String nome,
            String endereco,
            String tipoCozinha,
            String horarioFuncionamento,
            Usuario dono
    ) {
        if (nome == null || nome.trim().isEmpty())
            throw new DomainException("Nome do restaurante é obrigatório.");

        if (endereco == null || endereco.trim().isEmpty())
            throw new DomainException("Endereço é obrigatório.");

        if (tipoCozinha == null || tipoCozinha.trim().isEmpty())
            throw new DomainException("Tipo de cozinha é obrigatório.");

        if (horarioFuncionamento == null || horarioFuncionamento.trim().isEmpty())
            throw new DomainException("Horário de funcionamento é obrigatório.");

        if (dono == null)
            throw new DomainException("Restaurante deve ter um dono.");
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public Usuario getDono() {
        return dono;
    }
}
