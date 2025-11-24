package br.com.techchallenge.fase2.infrastructure.persistence.adapters;

import br.com.techchallenge.fase2.domain.entities.ItemCardapio;
import br.com.techchallenge.fase2.domain.entities.Restaurante;
import br.com.techchallenge.fase2.domain.entities.TipoUsuario;
import br.com.techchallenge.fase2.domain.entities.Usuario;
import br.com.techchallenge.fase2.application.gateways.ItemCardapioGateway;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.ItemCardapioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.RestauranteJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.jpa.UsuarioJpa;
import br.com.techchallenge.fase2.infrastructure.persistence.springdata.ItemCardapioSpringDataRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemCardapioGatewayImpl implements ItemCardapioGateway {

    private final ItemCardapioSpringDataRepository jpaRepository;

    public ItemCardapioGatewayImpl(ItemCardapioSpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item) {
        Restaurante r = item.getRestaurante();
        Usuario dono = r.getDono();
        TipoUsuario tipo = dono.getTipoUsuario();

        ItemCardapioJpa jpa = new ItemCardapioJpa(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.isSomenteNoLocal(),
                item.getFotoPath(),
                new RestauranteJpa(
                        r.getId(),
                        r.getNome(),
                        r.getEndereco(),
                        r.getTipoCozinha(),
                        r.getHorarioFuncionamento(),
                        new UsuarioJpa(
                                dono.getId(),
                                dono.getNome(),
                                new TipoUsuarioJpa(tipo.getId(), tipo.getNomeTipo())
                        )
                )
        );

        ItemCardapioJpa salvo = jpaRepository.save(jpa);

        return new ItemCardapio(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco(),
                salvo.isSomenteNoLocal(),
                salvo.getFotoPath(),
                r
        );
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(jpa -> new ItemCardapio(
                        jpa.getId(),
                        jpa.getNome(),
                        jpa.getDescricao(),
                        jpa.getPreco(),
                        jpa.isSomenteNoLocal(),
                        jpa.getFotoPath(),
                        new Restaurante(
                                jpa.getRestaurante().getId(),
                                jpa.getRestaurante().getNome(),
                                jpa.getRestaurante().getEndereco(),
                                jpa.getRestaurante().getTipoCozinha(),
                                jpa.getRestaurante().getHorarioFuncionamento(),
                                new Usuario(
                                        jpa.getRestaurante().getDono().getId(),
                                        jpa.getRestaurante().getDono().getNome(),
                                        new TipoUsuario(
                                                jpa.getRestaurante().getDono().getTipoUsuario().getId(),
                                                jpa.getRestaurante().getDono().getTipoUsuario().getNomeTipo()
                                        )
                                )
                        )
                ));
    }

    @Override
    public List<ItemCardapio> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(jpa -> new ItemCardapio(
                        jpa.getId(),
                        jpa.getNome(),
                        jpa.getDescricao(),
                        jpa.getPreco(),
                        jpa.isSomenteNoLocal(),
                        jpa.getFotoPath(),
                        new Restaurante(
                                jpa.getRestaurante().getId(),
                                jpa.getRestaurante().getNome(),
                                jpa.getRestaurante().getEndereco(),
                                jpa.getRestaurante().getTipoCozinha(),
                                jpa.getRestaurante().getHorarioFuncionamento(),
                                new Usuario(
                                        jpa.getRestaurante().getDono().getId(),
                                        jpa.getRestaurante().getDono().getNome(),
                                        new TipoUsuario(
                                                jpa.getRestaurante().getDono().getTipoUsuario().getId(),
                                                jpa.getRestaurante().getDono().getTipoUsuario().getNomeTipo()
                                        )
                                )
                        )
                )).toList();
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<ItemCardapio> buscarPorRestaurante(Long idRestaurante) {
        return buscarTodos().stream()
                .filter(i -> i.getRestaurante().getId().equals(idRestaurante))
                .toList();
    }
}