package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	/*
	 * Busca por JPQL
	 * 
	 * @Query("SELECT t from Topico t WHERE t.curso.nome = :nomeCurso") 
	 * public List<Topico> buscaTopicoPorNomeDeCurso (@Param("nomeCurso")String nome);
	 */

	public List<Topico> findByCursoNome(String nome);

}
