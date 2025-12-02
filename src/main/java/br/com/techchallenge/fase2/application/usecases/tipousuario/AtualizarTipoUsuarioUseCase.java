package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.dtos.AtualizarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityNotFoundException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;

public class AtualizarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public AtualizarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuario executar(AtualizarTipoUsuarioDTO dto) {
        TipoUsuario tipoExistente = tipoUsuarioGateway.buscarPorId(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de usuário com ID " + dto.id() + " não encontrado."));

        TipoUsuario tipoAtualizado = new TipoUsuario(dto.id(), dto.nomeTipo());
        return tipoUsuarioGateway.salvar(tipoAtualizado);
    }
}
