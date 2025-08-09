package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.DadosLivros;
import br.com.alura.literalura.dto.DadosResultados;
import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final ConsumoApi consumoApi;
    private final ConverteDados converteDados;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, ConsumoApi consumoApi, ConverteDados converteDados) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.consumoApi = consumoApi;
        this.converteDados = converteDados;
    }

    public void buscarEsalvarLivro(Scanner scanner) {
        System.out.println("Digite o titulo do livro que quer buscar: ");
        String titulo = scanner.nextLine();
        String enderecoBusca = "https://gutendex.com/books?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String json = consumoApi.obterDados(enderecoBusca);

        DadosResultados dadosResultados = converteDados.obterDados(json, DadosResultados.class);
        Optional<DadosLivros> dadosLivro = dadosResultados.resultados().stream().findFirst();

        if (dadosLivro.isPresent()) {
            Livro livro = new Livro(dadosLivro.get());
            livroRepository.save(livro);
            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);
        } else {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            System.out.println("----- LIVROS REGISTRADOS -----");
            livros.forEach(System.out::println);
        }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            System.out.println("----- AUTORES REGISTRADOS -----");
            autores.forEach(System.out::println);
        }
    }

    public void listarAutoresVivosEmAno(Scanner scanner) {
        System.out.println("Digite o ano para buscar autores vivos:");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = autorRepository.findAutoresVivosEmAno(ano);
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para o ano de " + ano + ".");
        } else {
            System.out.println("----- AUTORES VIVOS EM " + ano + " -----");
            autoresVivos.forEach(System.out::println);
        }
    }

    public void listarLivrosPorIdioma(Scanner scanner) {
        System.out.println("Digite o idioma para a busca (ex: es, en, fr, pt):");
        String idioma = scanner.nextLine();

        List<Livro> livrosPorIdioma = livroRepository.findByLingua(idioma);
        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma '" + idioma + "'.");
        } else {
            System.out.println("----- LIVROS NO IDIOMA: " + idioma + " -----");
            livrosPorIdioma.forEach(System.out::println);
        }
    }
}