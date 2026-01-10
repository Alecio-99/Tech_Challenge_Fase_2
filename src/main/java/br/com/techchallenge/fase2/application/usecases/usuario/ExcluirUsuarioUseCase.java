package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;

public class ExcluirUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public ExcluirUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void executar(Long id) {
        usuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
        usuarioGateway.deletarPorId(id);
    }
}