package br.com.confitec.teste.dto;

import java.util.List;

import lombok.Data;

/**
 * Representa a resposta a uma requisição de parcelamento de uma apólice de seguro.
 * <p>
 * Esta classe encapsula as informações retornadas após calcular as opções de parcelamento
 * para uma apólice de seguro, fornecendo detalhes de cada plano de parcelamento possível.
 * </p>
 * 
 * @author Matheus Martins Baptista
 * @version 1.0
 */

@Data
public class ParcelamentoResponseDTO {
	
	private List<DadosParcelamentoDTO> dados;

	public ParcelamentoResponseDTO() {}
	
	public ParcelamentoResponseDTO(List<DadosParcelamentoDTO> dados) {
		this.dados = dados;
	}
}
