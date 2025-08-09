package br.com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importe esta anotação
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultados(@JsonAlias("results") List<DadosLivros> resultados) {
}
