package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.RestauranteJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteSpringDataRepositoryTest extends JpaRepository<RestauranteJpaTest, Long> {}