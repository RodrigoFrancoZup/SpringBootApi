package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.dto.TopicoDTO;
import br.com.alura.forum.modelo.form.TopicoForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	// Criando objeto de TopicoRepository
	// @Autowired da new no objeto para gente
	@Autowired
	TopicoRepository topicoRepositorio;

	@Autowired
	CursoRepository cursoRepositorio;

	@GetMapping
	public List<TopicoDTO> lita() {
		// Criando um topico
		// Topico topico = new Topico("Dúvida XPTO", "Como fazer tal coisa?", new
		// Curso("Java", "Programacao"));

		/*
		 * Colocando em uma lista o mesmo tópico 3x Arrays.asList(topico, topico,
		 * topico);
		 */

		// Converte lista de topico para lista de topicoDTO e retorna

		return TopicoDTO.converter(topicoRepositorio.findAll());
	}

	@GetMapping("/topicosPorCurso")
	public List<TopicoDTO> buscaTopicoPorNomeDeCurso(String nomeCurso) {

		/*
		 * BUSCANDO PELA JPQL List<Topico> topicos =
		 * topicoRepositorioRepository.buscaTopicoPorNomeDeCurso(nomeCurso);
		 */

		List<Topico> topicos = topicoRepositorio.findByCursoNome(nomeCurso);
		List<TopicoDTO> topicosDTO = TopicoDTO.converter(topicos);

		return topicosDTO;
	}

	//UriComponentsBuilder é para saber a URI que estamos e como cirar a nova, no caso a do recurso salvo!
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.conversorParaTopico(cursoRepositorio);
		topicoRepositorio.save(topico);

		// . Pega a uri que estamos e adiciona mais o path
		// . adiciona na variavel {id} o id do recurso criado
		// . transforma tudo em URI
		URI uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
		
		// . Cria o retorno com a nova URI
		// . Adiciona no corpo da resposta a representacao do recurso criado.
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

}
