package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	//Objeto que descobre o idioma do cliente
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class) //pegara toda exception desse tipo
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // mesmo tratando o retorno sera status 400
	public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDTO> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); //Pega todos os campos com erro e suas mensagens. Retorna isso para uma lista.
		
		fieldErrors.forEach(e -> {
			String mensagemDeErro = messageSource.getMessage(e, LocaleContextHolder.getLocale()); //Deixando a msg no idioma correto
			ErroDeFormularioDTO erro = new ErroDeFormularioDTO(e.getField(), mensagemDeErro); //Criando um dto de resposta com cada campo errado e sua mensagem.
			dto.add(erro);
		});
		
		return dto;
	}

}
