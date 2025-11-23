package br.com.techchallenge.fase2.application.usecases.restaurante;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarRestauranteUseCase {

    private final RestauranteRepository repository;

    public AtualizarRestauranteUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante executar(Long id, Restaurante restauranteAtualizado) {
        // TODO: implementar atualização
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
