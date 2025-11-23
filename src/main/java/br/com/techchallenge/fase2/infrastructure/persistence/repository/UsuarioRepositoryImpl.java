package br.com.techchallenge.fase2.infrastructure.persistence.repository;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.domain.repositories.UsuarioRepository;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.UsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.springdata.UsuarioSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioSpringDataRepository jpaRepository;

    public UsuarioRepositoryImpl(UsuarioSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        TipoUsuarioJpa tipoJpa = new TipoUsuarioJpa(usuario.getTipoUsuario().getId(), usuario.getTipoUsuario().getNomeTipo());
        UsuarioJpa jpa = new UsuarioJpa(usuario.getId(), usuario.getNome(), tipoJpa);

        UsuarioJpa salvo = jpaRepository.save(jpa);

        TipoUsuario tipo = new TipoUsuario(salvo.getTipoUsuario().getId(), salvo.getTipoUsuario().getNomeTipo());
        return new Usuario(salvo.getId(), salvo.getNome(), tipo);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(jpa -> {
                    TipoUsuario tipo = new TipoUsuario(jpa.getTipoUsuario().getId(), jpa.getTipoUsuario().getNomeTipo());
                    return new Usuario(jpa.getId(), jpa.getNome(), tipo);
                });
    }

    @Override
    public List<Usuario> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(jpa -> new Usuario(
                        jpa.getId(),
                        jpa.getNome(),
                        new TipoUsuario(jpa.getTipoUsuario().getId(), jpa.getTipoUsuario().getNomeTipo())
                ))
                .toList();
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }
}