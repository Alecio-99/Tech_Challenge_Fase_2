package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;

public class AtualizarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public AtualizarUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public Usuario executar(AtualizarUsuarioDTO dto) {
        usuarioGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + dto.id() + " não encontrado."));

        TipoUsuario tipoUsuario = tipoUsuarioGateway.buscarPorId(dto.tipoUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de usuário com ID " + dto.tipoUsuarioId() + " não encontrado."));

        Usuario usuarioAtualizado = new Usuario(dto.id(), dto.nome(), tipoUsuario);
        return usuarioGateway.salvar(usuarioAtualizado);
    }
}