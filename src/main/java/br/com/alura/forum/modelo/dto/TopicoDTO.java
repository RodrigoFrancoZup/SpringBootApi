package br.com.alura.forum.modelo.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.alura.forum.modelo.Topico;

public class TopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime data;

	// Construtor para criar um TopicoDTO através de um Topico
	public TopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.data = topico.getDataCriacao();
	}

	// Metodo que recebe uma lista de Topico e transforma em lista de topicoDto
	public static List<TopicoDTO> converter(List<Topico> topicos) {
		return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
