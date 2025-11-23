package br.com.techchallenge.fase2.application.usecases.usuario;

import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioUseCase {

    private final UsuarioRepository repository;

    public AtualizarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Usuario usuarioAtualizado) {
        return repository.salvar(usuarioAtualizado);
    }
}