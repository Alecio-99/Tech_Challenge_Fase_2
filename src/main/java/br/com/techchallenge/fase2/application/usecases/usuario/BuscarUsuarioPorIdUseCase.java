package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioPorIdUseCase {

    private final UsuarioRepository repository;

    public BuscarUsuarioPorIdUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Usuario> executar(Long id) {
        return repository.buscarPorId(id);
    }
}