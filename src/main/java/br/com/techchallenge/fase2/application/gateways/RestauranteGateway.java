package br.com.techchallenge.fase2.application.gateways;

import br.com.techchallenge.fase2.domain.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante salvar(Restaurante restaurante);

    Optional<Restaurante> buscarPorId(Long id);

    List<Restaurante> buscarTodos();

    void deletarPorId(Long id);

    List<Restaurante> buscarPorDono(Long idDono);
}
