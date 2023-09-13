package br.com.confitec.teste.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * Representa uma cobertura de apólice com um identificador e um valor associado.
 * <p>
 * Esta classe é utilizada para transportar informações sobre uma cobertura específica 
 * que faz parte de uma apólice de seguro.
 * </p>
 * 
 * @author Matheus Martins Baptista
 * @version 1.0
 */

@Data
public class CoberturaDTO {

	private Integer cobertura;
    private BigDecimal valor;    
    
	public CoberturaDTO() {}

	public CoberturaDTO(Integer cobertura, BigDecimal valor) {
		this.cobertura = cobertura;
		this.valor = valor;
	}


    
    
}
