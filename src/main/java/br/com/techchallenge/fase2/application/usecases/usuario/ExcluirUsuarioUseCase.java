package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.application.gateways.UsuarioGateway;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUseCase {

    private final UsuarioGateway repository;

    public ExcluirUsuarioUseCase(UsuarioGateway repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deletarPorId(id);
    }
}