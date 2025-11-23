package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CriarRestauranteUseCase {

    private final RestauranteRepository repository;

    public CriarRestauranteUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante executar(Restaurante restaurante) {
        // TODO: implementar criação
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
