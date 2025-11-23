package br.com.techchallenge.fase2.application.usecases.tipousuario;

import br.com.techchallenge.fase2.domain.repositories.TipoUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirTipoUsuarioUseCase {

    private final TipoUsuarioRepository repository;

    public ExcluirTipoUsuarioUseCase(TipoUsuarioRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        // TODO: implementar exclusão
        throw new UnsupportedOperationException("Não implementado ainda");
    }
}