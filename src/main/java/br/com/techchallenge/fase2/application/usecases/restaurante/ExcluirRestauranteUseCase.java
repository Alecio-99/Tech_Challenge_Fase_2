package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;

public class ExcluirRestauranteUseCase {

    private final RestauranteGateway restauranteGateway;

    public ExcluirRestauranteUseCase(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public void executar(Long id) {
        restauranteGateway.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + id + " não encontrado."));
        restauranteGateway.deletarPorId(id);
    }
}