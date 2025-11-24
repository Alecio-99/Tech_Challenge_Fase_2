package br.com.techchallenge.fase2.application.gateways;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioGateway {

    TipoUsuario salvar(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> buscarPorId(Long id);

    List<TipoUsuario> buscarTodos();

    void deletarPorId(Long id);

    Optional<TipoUsuario> buscarPorNome(String nomeTipo);

}
