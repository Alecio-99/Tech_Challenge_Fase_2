package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTiposUsuarioUseCase {

    private final TipoUsuarioGateway repository;

    public ListarTiposUsuarioUseCase(TipoUsuarioGateway repository) {
        this.repository = repository;
    }

    public List<TipoUsuario> executar() {
        // TODO: implementar listagem
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}