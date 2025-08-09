package br.com.alura.literalura.principal;

import br.com.alura.literalura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final LivroService livroService;

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        var opcao = -1;
        while (opcao != 0) {

            var menu = """
                    ----------------------------------------------
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - lisar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    
                    0 - sair
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    livroService.buscarEsalvarLivro(scanner);
                    break;
                case 2:
                    livroService.listarLivrosRegistrados();
                    break;
                case 3:
                    livroService.listarAutoresRegistrados();
                    break;
                case 4:
                    livroService.listarAutoresVivosEmAno(scanner);
                    break;
                case 5:
                    livroService.listarLivrosPorIdioma(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        }
    }
}
