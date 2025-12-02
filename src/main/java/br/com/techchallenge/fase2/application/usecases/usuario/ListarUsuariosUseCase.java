package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.Usuario;

import java.util.List;

public class ListarUsuariosUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariosUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public List<Usuario> executar() {
        return usuarioGateway.buscarTodos();
    }
}