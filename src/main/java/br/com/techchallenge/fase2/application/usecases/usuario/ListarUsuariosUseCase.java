package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuariosUseCase {

    private final UsuarioGateway repository;

    public ListarUsuariosUseCase(UsuarioGateway repository) {
        this.repository = repository;
    }

    public List<Usuario> executar() {
        return repository.buscarTodos();
    }
}