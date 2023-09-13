package br.com.confitec.teste.dto;

import java.util.List;

import lombok.Data;

/**
 * Representa uma requisição para calcular o parcelamento de uma apólice de seguro.
 * <p>
 * Esta classe encapsula as informações necessárias para calcular as opções de parcelamento,
 * incluindo as coberturas associadas à apólice e as opções de parcelamento disponíveis.
 * </p>
 * 
 * @author Matheus Martins Baptista
 * @version 1.0
 */


@Data
public class ParcelamentoRequestDTO {

	private List<CoberturaDTO> listCobertura;
	private List<OpcaoParcelamentoDTO> listOpcaoParcelamento;
	
	public ParcelamentoRequestDTO() {}

	public ParcelamentoRequestDTO(List<CoberturaDTO> listCobertura, List<OpcaoParcelamentoDTO> listOpcaoParcelamento) {
		this.listCobertura = listCobertura;
		this.listOpcaoParcelamento = listOpcaoParcelamento;
	}
}
