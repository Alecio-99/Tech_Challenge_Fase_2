package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.UsuarioJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSpringDataRepositoryTest extends JpaRepository<UsuarioJpaTest, Long> {}