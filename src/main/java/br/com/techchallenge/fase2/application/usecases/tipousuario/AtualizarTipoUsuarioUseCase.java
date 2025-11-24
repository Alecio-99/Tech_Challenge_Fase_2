package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarTipoUsuarioUseCase {

    private final TipoUsuarioGateway repository;

    public AtualizarTipoUsuarioUseCase(TipoUsuarioGateway repository) {
        this.repository = repository;
    }

    public TipoUsuario executar(Long id, TipoUsuario tipoUsuarioAtualizado) {
        // TODO: implementar atualização
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}
