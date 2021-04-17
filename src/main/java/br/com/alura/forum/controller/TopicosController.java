package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.dto.TopicoDTO;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {

	// Criando objeto de TopicoRepository
	// @Autowired da new no objeto para gente
	@Autowired
	TopicoRepository topicoRepositorioRepository;

	@GetMapping("/topicos")
	public List<TopicoDTO> lita() {
		// Criando um topico
		// Topico topico = new Topico("Dúvida XPTO", "Como fazer tal coisa?", new
		// Curso("Java", "Programacao"));

		/*
		 * Colocando em uma lista o mesmo tópico 3x Arrays.asList(topico, topico,
		 * topico);
		 */

		// Converte lista de topico para lista de topicoDTO e retorna

		return TopicoDTO.converter(topicoRepositorioRepository.findAll());
	}

	@GetMapping("/topicosPorCurso")
	public List<TopicoDTO> buscaTopicoPorNomeDeCurso(String nomeCurso) {

		/* BUSCANDO PELA JPQL
		List<Topico> topicos = topicoRepositorioRepository.buscaTopicoPorNomeDeCurso(nomeCurso);
		*/
		
		List<Topico> topicos = topicoRepositorioRepository.findByCursoNome(nomeCurso);
		List<TopicoDTO> topicosDTO = TopicoDTO.converter(topicos);

		return topicosDTO;
	}

}
