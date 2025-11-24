package br.com.techchallenge.fase2.infrastructure.persistence.adapters;

import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.application.gateways.RestauranteGateway;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.RestauranteJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.UsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.springdata.RestauranteSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteGatewayImpl implements RestauranteGateway {

    private final RestauranteSpringDataRepository jpaRepository;

    public RestauranteGatewayImpl(RestauranteSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        Usuario dono = restaurante.getDono();
        UsuarioJpa donoJpa = new UsuarioJpa(dono.getId(), dono.getNome(),
                new TipoUsuarioJpa(dono.getTipoUsuario().getId(), dono.getTipoUsuario().getNomeTipo()));

        RestauranteJpa jpa = new RestauranteJpa(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getEndereco(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                donoJpa
        );

        RestauranteJpa salvo = jpaRepository.save(jpa);

        return new Restaurante(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEndereco(),
                salvo.getTipoCozinha(),
                salvo.getHorarioFuncionamento(),
                dono
        );
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(jpa -> new Restaurante(
                        jpa.getId(),
                        jpa.getNome(),
                        jpa.getEndereco(),
                        jpa.getTipoCozinha(),
                        jpa.getHorarioFuncionamento(),
                        new Usuario(
                                jpa.getDono().getId(),
                                jpa.getDono().getNome(),
                                new TipoUsuario(jpa.getDono().getTipoUsuario().getId(), jpa.getDono().getTipoUsuario().getNomeTipo())
                        )
                ));
    }

    @Override
    public List<Restaurante> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(jpa -> new Restaurante(
                        jpa.getId(),
                        jpa.getNome(),
                        jpa.getEndereco(),
                        jpa.getTipoCozinha(),
                        jpa.getHorarioFuncionamento(),
                        new Usuario(
                                jpa.getDono().getId(),
                                jpa.getDono().getNome(),
                                new TipoUsuario(jpa.getDono().getTipoUsuario().getId(), jpa.getDono().getTipoUsuario().getNomeTipo())
                        )
                ))
                .toList();
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Restaurante> buscarPorDono(Long idDono) {
        return jpaRepository.findAll().stream()
                .filter(r -> r.getDono().getId().equals(idDono))
                .map(jpa -> new Restaurante(
                        jpa.getId(),
                        jpa.getNome(),
                        jpa.getEndereco(),
                        jpa.getTipoCozinha(),
                        jpa.getHorarioFuncionamento(),
                        new Usuario(
                                jpa.getDono().getId(),
                                jpa.getDono().getNome(),
                                new TipoUsuario(jpa.getDono().getTipoUsuario().getId(), jpa.getDono().getTipoUsuario().getNomeTipo())
                        )
                ))
                .toList();
    }
}
