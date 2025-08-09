# Literalura - Catálogo de Livros

![Status](https://img.shields.io/badge/status-concluído-brightgreen)

## 📖 Sobre o Projeto

**Literalura** é um catálogo de livros interativo que funciona via linha de comando (CLI). Este projeto foi desenvolvido com o objetivo de praticar o uso de **Java com Spring Boot** para construir uma aplicação que consome uma API externa, persiste dados em um banco de dados relacional e oferece uma interface de texto para o usuário interagir com os dados catalogados.

A aplicação se conecta à API gratuita **Gutendex** para buscar informações sobre livros e autores, permitindo ao usuário salvar seus livros preferidos em um banco de dados local (PostgreSQL) para consultas posteriores.

## ✨ Funcionalidades

O menu da aplicação oferece as seguintes funcionalidades:

1.  **Buscar e registrar livros:** Busca um livro por título na API Gutendex e o salva no banco de dados local.
2.  **Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar autores registrados:** Exibe todos os autores dos livros que foram salvos.
4.  **Listar autores vivos:** Exibe uma lista de autores que estavam vivos em um determinado ano.
5.  **Listar livros por idioma:** Exibe uma lista de livros registrados em um idioma específico (espanhol, inglês, francês, português).

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias e conceitos:

* **Java 17+**
* **Spring Boot 3**
* **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
* **Hibernate:** Como implementação da JPA.
* **PostgreSQL:** Banco de dados relacional para armazenamento dos dados.
* **Jackson Databind:** Para desserialização do JSON da API em objetos Java.
* **Java `HttpClient`:** Para realizar as requisições HTTP para a API externa.
* **Maven:** Para o gerenciamento de dependências.

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo.

### Pré-requisitos

* **JDK 17** ou superior.
* **Maven** 3.8 ou superior.
* **PostgreSQL** instalado e em execução.
* Uma **IDE** de sua preferência (ex: IntelliJ IDEA, VS Code com Java Extension Pack, Eclipse).

### Passos para Execução

1.  **Clone o Repositório**
    ```bash
    git clone [https://github.com/seu-usuario/literalura.git](https://github.com/seu-usuario/literalura.git)
    cd literalura
    ```

2.  **Configure o Banco de Dados**
    * Crie um banco de dados no PostgreSQL com o nome `literalura`.
    * Abra o arquivo `src/main/resources/application.properties`.
    * Atualize as seguintes propriedades com seu usuário e senha do PostgreSQL:
        ```properties
        spring.datasource.username=seu_usuario_aqui
        spring.datasource.password=sua_senha_aqui
        ```

3.  **Execute a Aplicação**
    * Você pode executar o projeto de duas formas:
        * **Via Maven (no terminal):**
            ```bash
            mvn spring-boot:run
            ```
        * **Via IDE:**
            * Abra o projeto na sua IDE.
            * Execute a classe principal `LiteraluraApplication.java`.

4.  **Interaja com o Menu**
    * Após a inicialização, o menu interativo aparecerá no console, pronto para receber os comandos.

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas para separar as responsabilidades:

* **`principal`**: Camada de interação com o usuário (View da aplicação CLI).
* **`service`**: Camada que contém a lógica de negócio da aplicação.
    * `ConsumoApi`: Responsável por fazer a chamada HTTP para a API externa.
    * `ConverteDados`: Responsável por desserializar o JSON para DTOs.
    * `LivroService`: Orquestra as operações entre a API, o banco de dados e a classe principal.
* **`repository`**: Camada de acesso a dados, com interfaces que estendem `JpaRepository`.
* **`model`**: Contém as entidades JPA (`Livro`, `Autor`) que mapeiam as tabelas do banco de dados.
* **`dto`**: Contém os `records` (`DadosLivros`, etc.) que servem como Data Transfer Objects para os dados vindos da API Gutendex.

## 🌐 API Utilizada

Este projeto utiliza a API gratuita **[Gutendex](https://gutendex.com/)**, que fornece dados de livros do acervo do Project Gutenberg.
