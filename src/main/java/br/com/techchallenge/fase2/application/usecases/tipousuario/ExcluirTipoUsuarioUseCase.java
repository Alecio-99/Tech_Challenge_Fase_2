package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class ExcluirTipoUsuarioUseCase {

    private final TipoUsuarioGateway repository;

    public ExcluirTipoUsuarioUseCase(TipoUsuarioGateway repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}