package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioSpringDataRepositoryTest extends JpaRepository<TipoUsuarioJpaTest, Long> {}