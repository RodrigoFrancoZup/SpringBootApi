package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.dto.DetalheDeTopicoDTO;
import br.com.alura.forum.modelo.dto.TopicoDTO;
import br.com.alura.forum.modelo.form.AtualizacaoTopicoForm;
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
		// Topico topico = new Topico("Dúvida XPTO", "Como fazer tal coisa?", new Curso("Java", "Programacao"));

		/*
		 * Colocando em uma lista o mesmo tópico 3x Arrays.asList(topico, topico, topico);
		 */

		// Converte lista de topico para lista de topicoDTO e retorna

		return TopicoDTO.converter(topicoRepositorio.findAll());
	}

	@GetMapping("/topicosPorCurso")
	public List<TopicoDTO> buscaTopicoPorNomeDeCurso(String nomeCurso) {

		/*
		 * BUSCANDO PELA JPQL List<Topico> topicos = topicoRepositorioRepository.buscaTopicoPorNomeDeCurso(nomeCurso);
		 */

		List<Topico> topicos = topicoRepositorio.findByCursoNome(nomeCurso);
		List<TopicoDTO> topicosDTO = TopicoDTO.converter(topicos);

		return topicosDTO;
	}

	// UriComponentsBuilder é para saber a URI que estamos e como cirar a nova, no caso a do recurso salvo!
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

	@GetMapping("/{id}")
	public ResponseEntity<DetalheDeTopicoDTO> detalhar(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepositorio.findById(id); // Retorna um optional com Topico ou com Null caso não exciste topico com ID passado
		if (optional.isPresent()) { // Verifica se há topico dentro de optional
			Topico topico = optional.get(); // pega o topico de dentro de optional
			return ResponseEntity.ok(new DetalheDeTopicoDTO(topico));
		} else {
			return ResponseEntity.notFound().build(); // Retornando o 404 - Not Full
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Topico> optional = topicoRepositorio.findById(id);
		if (optional.isPresent()) {
			Topico topico = optional.get();
			topicoRepositorio.delete(topico);
			return ResponseEntity.ok("O Topico de ID = " + id + " foi deletado.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
		Optional<Topico> optional = topicoRepositorio.findById(id);
		if(optional.isPresent()) {
			Topico topico = optional.get();
			topico.setMensagem(form.getMensagem());
			topico.setTitulo(form.getTitulo());
			return ResponseEntity.ok(new TopicoDTO(topico));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
