package com.desafio.castgroup.validate;

import java.util.Base64;

import com.desafio.castgroup.entity.DadoEntity;
import com.desafio.castgroup.models.Dado;

public class DadoValidate {

	public static boolean ValidaBase64(String dado) {

		try {
			if (dado == null)
				return false;
			Base64.getDecoder().decode(dado);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public static void validaDado(Dado dado) throws Excessao {
		if (dado == null) {
			throw new Excessao("Objeto nulo!", 1);
		} else if (!DadoValidate.ValidaBase64(dado.getDado())) {
			throw new Excessao("Erro: O valor não esta criptografado com base64!", 2);
		} else if (dado.getId() == null) {
			throw new Excessao("Id do objeto é inválido!", 3);
		}
	}
	
	public static void validaDado(DadoEntity dadoEntity) throws Excessao {
		if (dadoEntity.getLeft().isEmpty()) {
			throw new Excessao("Atenção: É obrigatorio o valor da ESQUERDA para realizar a avaliação!", 4);
		}

		if (dadoEntity.getRight().isEmpty()) {
			throw new Excessao("Atenção: É obrigatorio o valor da DIREITA para realizar a avaliação!", 4);
		}
	}
	
}
