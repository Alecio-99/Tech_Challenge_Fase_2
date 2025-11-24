package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.ItemCardapioJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioSpringDataRepositoryTest extends JpaRepository<ItemCardapioJpaTest, Long> {}