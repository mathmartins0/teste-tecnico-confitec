package br.com.confitec.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.confitec.teste.dto.DadosParcelamentoDTO;
import br.com.confitec.teste.dto.ParcelamentoRequestDTO;
import br.com.confitec.teste.dto.ParcelamentoResponseDTO;
import br.com.confitec.teste.service.ApoliceService;

/**
 * 
 * @author Matheus Martins Baptista
 * 
 * Classe controladora para interceptar requisições sobre {@link ParcelamentoRequestDTO}s
 *
 */

@RestController
@RequestMapping("/confitec/teste")
public class ApoliceController {
	
    @Autowired
    private ApoliceService apoliceService;

    /**
     * Recebe uma requisição de parcelamento, através de um {@link ParcelamentoRequestDTO}
     * 
     * @param parcelamento
     * 	Corpo da requisição deve ser um DTO {@link ParcelamentoRequestDTO}
     * @return
     * 	Retorna um DTO {@link ParcelamentoResponseDTO}, com uma lista do parcelamento {@link DadosParcelamentoDTO}
     */
    @PostMapping("/parcelamento")
    public ResponseEntity<ParcelamentoResponseDTO> obterParcelas(@RequestBody ParcelamentoRequestDTO parcelamento) {
        return new ResponseEntity<ParcelamentoResponseDTO>(
        		apoliceService.calcularParcelas(parcelamento), HttpStatus.OK);
    }
}
