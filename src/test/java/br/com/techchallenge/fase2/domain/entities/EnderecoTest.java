package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoValido() {
        Endereco endereco = new Endereco(
                "Rua A", "12345-000", 10, "Centro", "São Paulo"
        );

        assertEquals("Rua A", endereco.getRua());
    }

    @Test
    void naoDeveCriarEnderecoSemRua() {
        assertThrows(DomainException.class, () ->
                new Endereco(null, "12345", 10, "Centro", "SP")
        );
    }

    @Test
    void naoDeveCriarEnderecoComNumeroInvalido() {
        assertThrows(DomainException.class, () ->
                new Endereco("Rua", "12345", 0, "Centro", "SP")
        );
    }
}