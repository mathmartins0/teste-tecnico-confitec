package br.com.confitec.teste.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Representa uma opção de parcelamento oferecida para uma apólice de seguro.
 * <p>
 * Esta classe é utilizada para transportar informações sobre uma opção de parcelamento específica,
 * detalhando a faixa de parcelas permitidas e a taxa de juros associada.
 * </p>
 * 
 * @author Matheus Martins Baptista
 * @version 1.0
 */

@Data
public class OpcaoParcelamentoDTO {

    private Integer quantidadeMinimaParcelas;
    private Integer quantidadeMaximaParcelas;
    private BigDecimal juros;
    
	public OpcaoParcelamentoDTO() {}
    
	public OpcaoParcelamentoDTO(Integer quantidadeMinimaParcelas, Integer quantidadeMaximaParcelas, BigDecimal juros) {
		this.quantidadeMinimaParcelas = quantidadeMinimaParcelas;
		this.quantidadeMaximaParcelas = quantidadeMaximaParcelas;
		this.juros = juros;
	}
}
