package br.com.alura.forum.modelo.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.forum.modelo.Resposta;
import br.com.alura.forum.modelo.StatusTopico;
import br.com.alura.forum.modelo.Topico;

public class DetalheDeTopicoDTO {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime data;
	private String nomeDoAutor = "Anonimo";
	private StatusTopico status;
	private List<RespostaDTO> respostas;

	public DetalheDeTopicoDTO(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.data = topico.getDataCriacao();
		if (topico.getAutor() != null) {
			this.nomeDoAutor = topico.getAutor().getNome();
		}
		this.status = topico.getStatus();
		this.respostas = new ArrayList<RespostaDTO>();
		for (Resposta resposta : topico.getRespostas()) {
			this.respostas.add(new RespostaDTO(resposta));
		}

	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getNomeDoAutor() {
		return nomeDoAutor;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

}
