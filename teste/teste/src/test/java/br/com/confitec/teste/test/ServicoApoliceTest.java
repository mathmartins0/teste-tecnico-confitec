package br.com.confitec.teste.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.confitec.teste.dto.CoberturaDTO;
import br.com.confitec.teste.dto.DadosParcelamentoDTO;
import br.com.confitec.teste.dto.OpcaoParcelamentoDTO;
import br.com.confitec.teste.dto.ParcelamentoRequestDTO;
import br.com.confitec.teste.dto.ParcelamentoResponseDTO;
import br.com.confitec.teste.service.ApoliceService;

@SpringBootTest
public class ServicoApoliceTest {

	private ApoliceService apoliceService;

	@BeforeEach
	void setUp() {
		apoliceService = new ApoliceService();
	}
	
    private ParcelamentoRequestDTO criarRequest() {
        List<CoberturaDTO> coberturas = new ArrayList<>();
        coberturas.add(new CoberturaDTO(1, new BigDecimal("123.12")));
        coberturas.add(new CoberturaDTO(4, new BigDecimal("345.45")));

        List<OpcaoParcelamentoDTO> opcoes = new ArrayList<>();
        opcoes.add(new OpcaoParcelamentoDTO(1, 6, BigDecimal.ZERO));
        opcoes.add(new OpcaoParcelamentoDTO(7, 9, new BigDecimal("0.01")));
        opcoes.add(new OpcaoParcelamentoDTO(10, 12, new BigDecimal("0.03")));

        return new ParcelamentoRequestDTO(coberturas, opcoes);
    }

	@Test
    public void testCalcularParcelas() {
        ParcelamentoRequestDTO request = criarRequest();

        ParcelamentoResponseDTO response = apoliceService.calcularParcelas(request);

        assertEquals(12, response.getDados().size());

        DadosParcelamentoDTO firstOption = response.getDados().get(0);
        assertEquals(1, firstOption.getQuantidadeParcelas());
        assertEquals(new BigDecimal("468.57"), firstOption.getValorPrimeiraParcela());
        assertEquals(new BigDecimal("468.57"), firstOption.getValorParcelamentoTotal());

    }
}
