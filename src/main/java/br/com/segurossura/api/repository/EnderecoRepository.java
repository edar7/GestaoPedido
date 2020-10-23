package br.com.segurossura.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.segurossura.api.domains.endereco.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
