package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioTest {

    @Test
    void deveCriarTipoUsuarioValido() {
        TipoUsuario tipo = new TipoUsuario(1L, "ADMIN");

        assertEquals("ADMIN", tipo.getNomeTipo());
    }

    @Test
    void naoDeveCriarTipoUsuarioSemNome() {
        assertThrows(DomainException.class,
                () -> new TipoUsuario(1L, " ")
        );
    }
}