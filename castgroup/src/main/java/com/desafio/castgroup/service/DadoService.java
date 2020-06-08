package com.desafio.castgroup.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.castgroup.entity.DadoEntity;
import com.desafio.castgroup.enums.DadoEnum;
import com.desafio.castgroup.models.Dado;
import com.desafio.castgroup.repository.DadoRepository;
import com.desafio.castgroup.resources.DadoResource;
import com.desafio.castgroup.validate.DadoValidate;
import com.desafio.castgroup.validate.Excessao;

@Service
public class DadoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DadoResource.class);

	@Autowired
	DadoRepository dataRepository;

	public DadoEntity SalvarDado(Dado dado, DadoEnum lado) throws Excessao {

		DadoValidate.validaDado(dado);
		DadoEntity dadoEntity = getDado(dado.getId());

		if (dadoEntity == null) {
			dadoEntity = new DadoEntity();
			dadoEntity.setId(dado.getId());
		}

		if (DadoEnum.LEFT.equals(lado)) {
			dadoEntity.setLeft(dado.getDado());
		} else if (DadoEnum.RIGHT.equals(lado)) {
			dadoEntity.setRight(dado.getDado());
		}

		return dataRepository.save(dadoEntity);

	}
	
	private DadoEntity getDado(Long id) throws Excessao {
		DadoEntity dadoEntity = null;
		try {
			Optional<DadoEntity> repositorioEntity = dataRepository.findById(id);
			if (repositorioEntity.isPresent()) {
				dadoEntity = repositorioEntity.get();
			}
			return dadoEntity;
		} catch (IllegalArgumentException e) {
			throw new Excessao(e.getLocalizedMessage(), 5);
		}
	}
	
	public String AvaliaDado(Long id) throws Excessao {

		DadoEntity dadoEntity = getDado(id);
		DadoValidate.validaDado(dadoEntity);

		byte[] leftBytes = Base64.getDecoder().decode(dadoEntity.getLeft());
		byte[] rightBytes = Base64.getDecoder().decode(dadoEntity.getRight());

		if (Arrays.equals(leftBytes, rightBytes)) {
			return "True";
		} else if (leftBytes.length != rightBytes.length) {
			return String.format("\r\n" + 
					"Tamanho esquerdo [%s] - \r\n" + 
					"Tamanho direito [%s]", leftBytes.length, rightBytes.length);
		} else {

			StringBuilder builder = new StringBuilder();
			builder.append("\r\n" + 
					"Deslocamento principal: ");
			for (int i = 0; i < rightBytes.length; i++) {
				byte leftByte = leftBytes[i];
				byte rightByte = rightBytes[i];

				int offset = leftByte ^ rightByte;

				if (offset != 0) {
					builder.append(offset).append(" Comprimento ").append(i).append(" - ");
				}
			}
			builder.replace(builder.length() - 2, builder.length(), "");
			return builder.toString();
		}
	}
}
