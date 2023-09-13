package br.com.confitec.teste.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.confitec.teste.dto.CoberturaDTO;
import br.com.confitec.teste.dto.DadosParcelamentoDTO;
import br.com.confitec.teste.dto.OpcaoParcelamentoDTO;
import br.com.confitec.teste.dto.ParcelamentoRequestDTO;
import br.com.confitec.teste.dto.ParcelamentoResponseDTO;

/**
 * 
 *  Classe responsável por concentrar as regras de negócio das opções de parcelamento de uma apólice
 *  de seguro.
 *  
 *  Regras de negócio: O resto da divisão do rateio é alocado na primeira
 *  parcela; 
 *  A taxa de juros deverá ser aplicada ao valor total (soma das coberturas), utilizando a regra juros compostos: 
 *  	P = V*(1 + i)^t, onde: 
 *  		P = Valor a ser pago 
 *  		V = Valor total 
 *  		i = Taxa de juros 
 *  		t = Quantidade de parcelas 
 *  O parcelamento não se aplica para taxas de juros ou quantidade de parcelas negativas.
 * 
 * A implementação dessas regras podem ser observadas no método <code>calcularParcelas</code> dessa classe.
 * 
 * @author Matheus Martins Baptista
 * 
 * @version 1.0
 */
@Service
public class ApoliceService {

	/**
	 * Método responsável por calcular as parcelas de uma apólice, aplicando as
	 * regras de negócio necessárias.
	 * 
	 * @param 
	 *  requisicao Recebe um DTO {@link ParcelamentoRequestDTO}
	 * @return 
	 *  Retorna um objeto do DTO {@link ParcelamentoResponseDTO} com os dados do parcelamento.
	 */
	public ParcelamentoResponseDTO calcularParcelas(ParcelamentoRequestDTO requisicao) {
		List<DadosParcelamentoDTO> dados = new ArrayList<>();
		
		validarParcelamento(requisicao);

		BigDecimal valorTotalCoberturas = requisicao.getListCobertura().stream().map(CoberturaDTO::getValor)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		for (OpcaoParcelamentoDTO opcao : requisicao.getListOpcaoParcelamento()) {
			for (int i = opcao.getQuantidadeMinimaParcelas(); i <= opcao.getQuantidadeMaximaParcelas(); i++) {
				BigDecimal juros = opcao.getJuros();

				// Aplicando fórmula de juros compostos
				BigDecimal valorParcelamentoTotal = valorTotalCoberturas.multiply((BigDecimal.ONE.add(juros)).pow(i))
						.setScale(2, RoundingMode.HALF_UP);
				BigDecimal valorParcelaBase = valorParcelamentoTotal.divide(BigDecimal.valueOf(i), 2,
						RoundingMode.DOWN);
				BigDecimal resto = valorParcelamentoTotal.subtract(valorParcelaBase.multiply(BigDecimal.valueOf(i)));

				DadosParcelamentoDTO dadosParcelamento = new DadosParcelamentoDTO();
				dadosParcelamento.setQuantidadeParcelas(i);
				dadosParcelamento.setValorParcelamentoTotal(valorParcelamentoTotal);

				dadosParcelamento
						.setValorPrimeiraParcela(valorParcelaBase.add(resto).setScale(2, RoundingMode.HALF_UP));
				if (i > 1) {
					dadosParcelamento.setValorDemaisParcelas(valorParcelaBase.setScale(2, RoundingMode.HALF_UP));
				}

				dados.add(dadosParcelamento);
			}
		}

		dados.sort(Comparator.comparing(DadosParcelamentoDTO::getQuantidadeParcelas)
				.thenComparing(DadosParcelamentoDTO::getValorPrimeiraParcela));

		return new ParcelamentoResponseDTO(dados);
	}

	/**
	 * Método responsável por validar o parcelamento {@link ParcelamentoRequestDTO},
	 * lançando exceções conforme cada caso.
	 * 
	 * @param 
	 *  Recebe um DTO do tipo {@link ParcelamentoRequestDTO}
	 */
	private void validarParcelamento(ParcelamentoRequestDTO requisicao) {

		if (!Optional.ofNullable(requisicao).isPresent()) {
			throw new IllegalArgumentException("A requisição não pode ser nula.");
		}

		if (!Optional.ofNullable(requisicao.getListCobertura()).isPresent()
				|| Optional.ofNullable(requisicao.getListCobertura()).isEmpty()) {
			throw new IllegalArgumentException("A lista de coberturas não pode ser nula ou vazia.");
		}

		for (CoberturaDTO cobertura : requisicao.getListCobertura()) {
			if (cobertura.getValor().signum() < 0) {
				throw new IllegalArgumentException("Os valores das coberturas não podem ser negativos.");
			}
		}

		if (!Optional.ofNullable(requisicao.getListOpcaoParcelamento()).isPresent()
				|| Optional.ofNullable(requisicao.getListOpcaoParcelamento()).isEmpty()) {
			throw new IllegalArgumentException("A lista de opções de parcelamento não pode ser nula ou vazia.");
		}
	}
}
