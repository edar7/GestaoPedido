package br.com.segurossura.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.segurossura.api.domains.produto.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	Categoria findByDescricao(String nome);
}
