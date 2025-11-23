package br.com.techchallenge.fase2.domain.repositories;

import br.com.techchallenge.fase2.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository {

    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> buscarTodos();

    void deletarPorId(Long id);

    List<Restaurante> buscarPorDono(Long idDono);
}
