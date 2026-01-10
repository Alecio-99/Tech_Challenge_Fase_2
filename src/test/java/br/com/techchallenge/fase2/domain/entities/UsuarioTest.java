package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioValido() {
        TipoUsuario tipo = new TipoUsuario(1L, "CLIENTE");

        Usuario usuario = new Usuario(1L, "João", tipo);

        assertEquals("João", usuario.getNome());
    }

    @Test
    void naoDeveCriarUsuarioSemTipo() {
        assertThrows(DomainException.class,
                () -> new Usuario(1L, "João", null)
        );
    }

    @Test
    void naoDeveCriarUsuarioSemNome() {
        TipoUsuario tipo = new TipoUsuario(1L, "CLIENTE");

        assertThrows(DomainException.class,
                () -> new Usuario(1L, " ", tipo)
        );
    }
}