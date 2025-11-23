package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirUsuarioUseCase {

    private final UsuarioRepository repository;

    public ExcluirUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        repository.deletarPorId(id);
    }
}