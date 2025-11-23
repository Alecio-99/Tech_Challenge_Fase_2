package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.RestauranteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteSpringDataRepository extends JpaRepository<RestauranteJpa, Long> {}