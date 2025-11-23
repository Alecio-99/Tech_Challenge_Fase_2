package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.TipoUsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioSpringDataRepository extends JpaRepository<TipoUsuarioJpa, Long> {}