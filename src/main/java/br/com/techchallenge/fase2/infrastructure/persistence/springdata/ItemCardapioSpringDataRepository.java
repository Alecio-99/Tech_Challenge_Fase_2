package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.ItemCardapioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioSpringDataRepository extends JpaRepository<ItemCardapioJpa, Long> {}