package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;

public class Endereco {

    private String rua;
    private String cep;
    private int numero;
    private String bairro;
    private String cidade;


    public Endereco(String rua, String cep, int numero, String bairro, String cidade) {
        validar(rua, cep, numero, bairro, cidade);
        this.rua = rua.trim();
        this.cep = cep.trim();
        this.numero = numero;
        this.bairro = bairro.trim();
        this.cidade = cidade.trim();
    }

    private void validar(String rua, String cep, int numero, String bairro, String cidade) {
        if (rua == null || rua.trim().isEmpty())
            throw new DomainException("Rua do endereço é obrigatória.");
        if (cep == null || cep.trim().isEmpty())
            throw new DomainException("CEP do endereço é obrigatório.");
        if (numero <= 0)
            throw new DomainException("Número do endereço deve ser positivo.");
        if (bairro == null || bairro.trim().isEmpty())
            throw new DomainException("Bairro do endereço é obrigatório.");
        if (cidade == null || cidade.trim().isEmpty())
            throw new DomainException("Cidade do endereço é obrigatória.");
    }

    public String getRua() {
        return rua;
    }

    public String getCep() {
        return cep;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }
}
