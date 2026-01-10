package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.application.dtos.CriarTipoUsuarioDTO;
import br.com.techchallenge.fase2.application.exceptions.EntityAlreadyExistsException;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;

public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarTipoUsuarioUseCase(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public TipoUsuario executar(CriarTipoUsuarioDTO dto) {
        tipoUsuarioGateway.buscarPorNome(dto.nomeTipo())
                .ifPresent(tipo -> {
                    throw new EntityAlreadyExistsException("Tipo de usuário com nome '" + dto.nomeTipo() + "' já existe.");
                });

        TipoUsuario novoTipoUsuario = new TipoUsuario(null, dto.nomeTipo());
        return tipoUsuarioGateway.salvar(novoTipoUsuario);
    }
}
