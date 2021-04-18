package br.com.alura.forum.modelo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtualizacaoTopicoForm {
	
	@NotNull @NotEmpty @Length(max = 50, min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(max = 255, min = 10)
	private String mensagem;

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
	


}
