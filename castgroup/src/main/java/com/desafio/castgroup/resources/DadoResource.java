package com.desafio.castgroup.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.castgroup.enums.DadoEnum;
import com.desafio.castgroup.enums.ErroRetorno;
import com.desafio.castgroup.models.Dado;
import com.desafio.castgroup.service.DadoService;
import com.desafio.castgroup.validate.Excessao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class DadoResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(DadoResource.class);

	@Autowired
	private DadoService dadoService;
	
	@RequestMapping(path = "/v1/diff/{id}/left")
	public ResponseEntity<?> saveLeft(@PathVariable Long id, @RequestBody Dado dado) throws Excessao {
		try {
			dado.setId(id);
			dadoService.SalvarDado(dado, DadoEnum.LEFT);
			return ResponseEntity.ok("Sucesso!");
		} catch (Exception e) {
			throw new Excessao(e.getLocalizedMessage(), 0);
		}
	}
	
	@RequestMapping(path = "/v1/diff/{id}/right")
	public ResponseEntity<?> saveRight(@PathVariable Long id, @RequestBody Dado dado) throws Excessao {
		try {
			dado.setId(id);
			dadoService.SalvarDado(dado, DadoEnum.RIGHT);
			return ResponseEntity.ok("Sucesso!");
		} catch (Exception e) {
			throw new Excessao(e.getLocalizedMessage(), 0);
		}
	}
	
	@RequestMapping(path = "/v1/diff/{id}/avaliacao")
	public ResponseEntity<?> evaluate(@PathVariable Long id) throws Excessao {

		try {
			String resultado = dadoService.AvaliaDado(id);
			return ResponseEntity.ok(resultado);
		} catch (Exception e) {
			throw new Excessao(e.getLocalizedMessage(), 0);
		}
	}

	@ExceptionHandler({ Excessao.class })
	public ResponseEntity<Object> handleAuthenticationException(Excessao excessao) {
		ErroRetorno erro = new ErroRetorno();
		erro.setMensagem(excessao.getMensagem());
		erro.setCodigoErro(excessao.getCodigo());
		LOGGER.error(excessao.getMessage());
		return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
	}

}
