package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteTest {

    @Test
    void deveCriarRestauranteValido() {
        TipoUsuario tipo = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(1L, "Carlos", tipo);

        Restaurante restaurante = new Restaurante(
                1L,
                "Restaurante X",
                "Rua A",
                "Italiana",
                "08:00-22:00",
                dono
        );

        assertEquals("Restaurante X", restaurante.getNome());
    }

    @Test
    void naoDeveCriarRestauranteSemDono() {
        assertThrows(DomainException.class, () ->
                new Restaurante(1L, "Nome", "End", "Tipo", "Horario", null)
        );
    }

    @Test
    void naoDeveCriarRestauranteSemNome() {
        TipoUsuario tipo = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(1L, "Carlos", tipo);

        assertThrows(DomainException.class, () ->
                new Restaurante(1L, " ", "End", "Tipo", "Horario", dono)
        );
    }
}