package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirRestauranteUseCase {

    private final RestauranteRepository repository;

    public ExcluirRestauranteUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}