package br.com.techchallenge.fase2.domain.entities;

import br.com.techchallenge.fase2.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ItemCardapioTest {

    @Test
    void deveCriarItemCardapioValido() {
        TipoUsuario tipo = new TipoUsuario(1L, "DONO");
        Usuario dono = new Usuario(1L, "Carlos", tipo);

        Restaurante restaurante = new Restaurante(
                1L, "Rest X", "Rua A", "Japonesa", "10:00-22:00", dono
        );

        ItemCardapio item = new ItemCardapio(
                1L,
                "Sushi",
                "Sushi especial",
                new BigDecimal("39.90"),
                false,
                "/sushi.png",
                restaurante
        );

        assertEquals("Sushi", item.getNome());
    }

    @Test
    void naoDeveCriarItemComPrecoZeroOuNegativo() {
        Restaurante restaurante = mock(Restaurante.class);

        assertThrows(DomainException.class, () ->
                new ItemCardapio(
                        1L,
                        "Item",
                        "Desc",
                        BigDecimal.ZERO,
                        false,
                        "/img.png",
                        restaurante
                )
        );
    }

    @Test
    void naoDeveCriarItemSemRestaurante() {
        assertThrows(DomainException.class, () ->
                new ItemCardapio(
                        1L,
                        "Item",
                        "Desc",
                        new BigDecimal("10"),
                        false,
                        "/img.png",
                        null
                )
        );
    }
}