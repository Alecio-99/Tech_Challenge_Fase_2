package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.dtos.CriarUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;

public class CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public Usuario executar(CriarUsuarioDTO dto) {
        TipoUsuario tipoUsuario = tipoUsuarioGateway.buscarPorId(dto.tipoUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de usuário com ID " + dto.tipoUsuarioId() + " não encontrado."));

        Usuario novoUsuario = new Usuario(null, dto.nome(), tipoUsuario);
        return usuarioGateway.salvar(novoUsuario);
    }
}