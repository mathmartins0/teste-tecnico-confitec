package br.com.confitec.teste.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Representa os detalhes do parcelamento associados a uma apólice de seguro.
 * <p>
 * Esta classe é utilizada para transportar informações sobre o plano de parcelamento,
 * incluindo a quantidade de parcelas, o valor da primeira parcela, o valor das demais
 * parcelas (se aplicável) e o valor total do parcelamento.
 * </p>
 * 
 * @author Matheus Martins Baptista
 * @version 1.0
 */

@Data
public class DadosParcelamentoDTO {
	
    private Integer quantidadeParcelas;
    private BigDecimal valorPrimeiraParcela;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorDemaisParcelas;
    
    private BigDecimal valorParcelamentoTotal;
    
    public DadosParcelamentoDTO() {}

	public DadosParcelamentoDTO(Integer quantidadeParcelas, BigDecimal valorPrimeiraParcela,
			BigDecimal valorDemaisParcelas, BigDecimal valorParcelamentoTotal) {
		this.quantidadeParcelas = quantidadeParcelas;
		this.valorPrimeiraParcela = valorPrimeiraParcela;
		this.valorDemaisParcelas = valorDemaisParcelas;
		this.valorParcelamentoTotal = valorParcelamentoTotal;
	}
    
    
}
