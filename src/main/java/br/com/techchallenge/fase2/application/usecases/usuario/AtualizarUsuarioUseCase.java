package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioGateway repository;

    public AtualizarUsuarioUseCase(UsuarioGateway repository) {
        this.repository = repository;
    }

    public Usuario executar(Usuario usuarioAtualizado) {
        return repository.salvar(usuarioAtualizado);
    }
}