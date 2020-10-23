package br.com.segurossura.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.segurossura.api.domains.endereco.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
