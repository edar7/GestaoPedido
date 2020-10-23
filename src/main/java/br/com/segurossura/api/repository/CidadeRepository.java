package br.com.segurossura.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.segurossura.api.domains.endereco.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long>{

	/**
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.codigo = :codigo ORDER BY obj.nome")
	public List<Cidade> findCidades(@Param("estadoId") Integer codigo);
	**/
}
