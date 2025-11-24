package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway repository;

    public CriarTipoUsuarioUseCase(TipoUsuarioGateway repository) {
        this.repository = repository;
    }

    public TipoUsuario executar(TipoUsuario tipoUsuario) {
        // TODO: implementar regra de criação
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
