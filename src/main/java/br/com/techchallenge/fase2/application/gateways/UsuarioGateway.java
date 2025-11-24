package br.com.techchallenge.fase2.application.gateways;

import br.com.techchallenge.fase2.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> buscarTodos();

    void deletarPorId(Long id);
}
