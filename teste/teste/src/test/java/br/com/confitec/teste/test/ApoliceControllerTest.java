package br.com.confitec.teste.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.confitec.teste.dto.CoberturaDTO;
import br.com.confitec.teste.dto.OpcaoParcelamentoDTO;
import br.com.confitec.teste.dto.ParcelamentoRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class ApoliceControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testObterParcelas() throws Exception {
		ParcelamentoRequestDTO request = criarRequest();

		String jsonRequest = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/confitec/teste/parcelamento")
				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.dados").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.dados[0].quantidadeParcelas").value(1));
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

}
