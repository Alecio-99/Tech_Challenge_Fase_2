package br.com.techchallenge.fase2.infrastructure.persistence.adapters;

import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.application.gateways.TipoUsuarioGateway;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.springdata.TipoUsuarioSpringDataRepository;

import java.util.List;
import java.util.Optional;

public class TipoUsuarioGatewayImpl implements TipoUsuarioGateway {

    private final TipoUsuarioSpringDataRepository jpaRepository;

    public TipoUsuarioGatewayImpl(TipoUsuarioSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public TipoUsuario salvar(TipoUsuario tipoUsuario) {
        TipoUsuarioJpa jpa = new TipoUsuarioJpa(tipoUsuario.getId(), tipoUsuario.getNomeTipo());
        TipoUsuarioJpa salvo = jpaRepository.save(jpa);
        return new TipoUsuario(salvo.getId(), salvo.getNomeTipo());
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(jpa -> new TipoUsuario(jpa.getId(), jpa.getNomeTipo()));
    }

    @Override
    public List<TipoUsuario> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(jpa -> new TipoUsuario(jpa.getId(), jpa.getNomeTipo()))
                .toList();
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<TipoUsuario> buscarPorNome(String nomeTipo) {
        return jpaRepository.findAll().stream()
                .filter(t -> t.getNomeTipo().equalsIgnoreCase(nomeTipo))
                .findFirst()
                .map(jpa -> new TipoUsuario(jpa.getId(), jpa.getNomeTipo()));
    }
}