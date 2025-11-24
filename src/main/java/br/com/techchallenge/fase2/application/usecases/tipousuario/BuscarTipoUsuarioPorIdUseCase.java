package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarTipoUsuarioPorIdUseCase {

    private final TipoUsuarioGateway repository;

    public BuscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway repository) {
        this.repository = repository;
    }

    public Optional<TipoUsuario> executar(Long id) {
        // TODO: implementar busca por id
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
