package br.com.techchallenge.fase2.infrastructure.config;

import br.com.techchallenge.fase2.application.gateways.*;
import br.com.techchallenge.fase2.application.usecases.itemcardapio.*;
import br.com.techchallenge.fase2.application.usecases.restaurante.*;
import br.com.techchallenge.fase2.application.usecases.tipousuario.*;
import br.com.techchallenge.fase2.application.usecases.usuario.*;
import br.com.techchallenge.fase2.infrastructure.persistence.adapters.*;
import br.com.techchallenge.fase2.infrastructure.persistence.springdata.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    // Gateways
    @Bean
    public TipoUsuarioGateway tipoUsuarioGateway(TipoUsuarioSpringDataRepository repository) {
        return new TipoUsuarioGatewayImpl(repository);
    }

    @Bean
    public UsuarioGateway usuarioGateway(UsuarioSpringDataRepository repository) {
        return new UsuarioGatewayImpl(repository);
    }

    @Bean
    public RestauranteGateway restauranteGateway(RestauranteSpringDataRepository repository) {
        return new RestauranteGatewayImpl(repository);
    }

    @Bean
    public ItemCardapioGateway itemCardapioGateway(ItemCardapioSpringDataRepository repository) {
        return new ItemCardapioGatewayImpl(repository);
    }

    // Use Cases - TipoUsuario
    @Bean
    public CriarTipoUsuarioUseCase criarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new CriarTipoUsuarioUseCase(gateway);
    }

    @Bean
    public AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new AtualizarTipoUsuarioUseCase(gateway);
    }

    @Bean
    public BuscarTipoUsuarioPorIdUseCase buscarTipoUsuarioPorIdUseCase(TipoUsuarioGateway gateway) {
        return new BuscarTipoUsuarioPorIdUseCase(gateway);
    }

    @Bean
    public ListarTiposUsuarioUseCase listarTiposUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new ListarTiposUsuarioUseCase(gateway);
    }

    @Bean
    public ExcluirTipoUsuarioUseCase excluirTipoUsuarioUseCase(TipoUsuarioGateway gateway) {
        return new ExcluirTipoUsuarioUseCase(gateway);
    }

    // Use Cases - Usuario
    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        return new CriarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioGateway usuarioGateway, TipoUsuarioGateway tipoUsuarioGateway) {
        return new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(UsuarioGateway gateway) {
        return new BuscarUsuarioPorIdUseCase(gateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway gateway) {
        return new ListarUsuariosUseCase(gateway);
    }

    @Bean
    public ExcluirUsuarioUseCase excluirUsuarioUseCase(UsuarioGateway gateway) {
        return new ExcluirUsuarioUseCase(gateway);
    }

    // Use Cases - Restaurante
    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        return new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(RestauranteGateway restauranteGateway, UsuarioGateway usuarioGateway) {
        return new AtualizarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteGateway gateway) {
        return new BuscarRestaurantePorIdUseCase(gateway);
    }

    @Bean
    public ListarRestaurantesUseCase listarRestaurantesUseCase(RestauranteGateway gateway) {
        return new ListarRestaurantesUseCase(gateway);
    }

    @Bean
    public ExcluirRestauranteUseCase excluirRestauranteUseCase(RestauranteGateway gateway) {
        return new ExcluirRestauranteUseCase(gateway);
    }

    // Use Cases - ItemCardapio
    @Bean
    public CriarItemCardapioUseCase criarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        return new CriarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(ItemCardapioGateway itemCardapioGateway, RestauranteGateway restauranteGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    @Bean
    public BuscarItemCardapioPorIdUseCase buscarItemCardapioPorIdUseCase(ItemCardapioGateway gateway) {
        return new BuscarItemCardapioPorIdUseCase(gateway);
    }

    @Bean
    public ListarItensCardapioUseCase listarItensCardapioUseCase(ItemCardapioGateway gateway) {
        return new ListarItensCardapioUseCase(gateway);
    }

    @Bean
    public ExcluirItemCardapioUseCase excluirItemCardapioUseCase(ItemCardapioGateway gateway) {
        return new ExcluirItemCardapioUseCase(gateway);
    }
}
