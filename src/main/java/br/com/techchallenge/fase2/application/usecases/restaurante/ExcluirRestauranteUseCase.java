package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import org.springframework.stereotype.Service;

@Service
public class ExcluirRestauranteUseCase {

    private final RestauranteGateway repository;

    public ExcluirRestauranteUseCase(RestauranteGateway repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}