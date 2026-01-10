package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;

public class ExcluirTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public ExcluirTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public void executar(Long id) {
        tipoUsuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de usuário com ID " + id + " não encontrado."));
        tipoUsuarioGateway.deletarPorId(id);
    }
}