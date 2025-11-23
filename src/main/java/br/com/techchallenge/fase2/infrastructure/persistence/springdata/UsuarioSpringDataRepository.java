package br.com.techchallenge.fase2.infrastructure.persistence.springdata;

import br.com.techchallenge.fase2.infrastructure.persistence.jpa.UsuarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSpringDataRepository extends JpaRepository<UsuarioJpa, Long> {}