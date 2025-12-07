# 📚 Sistema de Biblioteca – Spring Boot


![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)

---

Este é um projeto de um sistema de gerenciamento de biblioteca, desenvolvido em Java com o framework Spring Boot.

## Tecnologias Utilizadas

*   **Java 17:** Versão da linguagem de programação.
*   **Spring Boot 3.3.0:** Framework principal para o desenvolvimento da aplicação.
    *   **Spring Web:** Para a criação de APIs REST e aplicações web.
    *   **Spring Data JPA:** Para a persistência de dados em um banco de dados relacional.
    *   **Spring Security:** Para a autenticação e autorização de usuários.
*   **Thymeleaf:** Motor de templates para a renderização de páginas HTML no lado do servidor.
*   **MySQL:** Banco de dados relacional para o armazenamento dos dados.
*   **Liquibase:** Para o versionamento e gerenciamento de alterações no esquema do banco de dados.
*   **Maven:** Ferramenta de automação de compilação e gerenciamento de dependências.
*   **Docker:** Para a criação de ambientes de desenvolvimento e produção em contêineres.
*   **Lombok:** Para a redução de código boilerplate (getters, setters, construtores, etc.).
*   **MapStruct:** Para a geração de mapeamentos entre objetos de diferentes camadas da aplicação.
*   **SpringDoc (Swagger UI):** Para a documentação e teste interativo das APIs REST.

## Configuração do Ambiente de Desenvolvimento

### Pré-requisitos

*   JDK 17 ou superior
*   Maven 3.6 ou superior
*   Docker e Docker Compose

### Passos para a Configuração

1.  **Clone o repositório:**

    ```bash
    git clone <url-do-repositorio>
    cd sistema-biblioteca
    ```

2.  **Inicie o banco de dados com Docker Compose:**

    O serviço do MySQL é definido no arquivo `docker-compose.yml`. Para iniciá-lo, execute o seguinte comando na raiz do projeto:

    ```bash
    docker-compose up -d
    ```

    Isso irá iniciar um contêiner Docker com o MySQL na porta `3306`. As configurações do banco de dados são:
    *   **Database:** `biblioteca`
    *   **Usuário:** `user`
    *   **Senha:** `password`
    *   **Senha do root:** `root`

3.  **Configure a aplicação:**

    As configurações da aplicação, como a conexão com o banco de dados, estão no arquivo `src/main/resources/application.properties`. Verifique se as configurações correspondem às do serviço do MySQL.

    **Exemplo de `application.properties`:**

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
    spring.datasource.username=user
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=none

    spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
    ```

4.  **Execute a aplicação:**

    Você pode executar a aplicação de duas maneiras:

    *   **Através da sua IDE:** Execute a classe principal `SistemaBibliotecaApplication.java`.
    *   **Através do Maven:**

        ```bash
        mvn spring-boot:run
        ```

    A aplicação estará disponível em `http://localhost:8080`.

## Build e Deploy com Docker

O projeto inclui um `Dockerfile` que define os estágios de build e execução da aplicação em um contêiner.

1.  **Construa a imagem Docker:**

    ```bash
    docker build -t sistema-biblioteca .
    ```

2.  **Execute o contêiner:**

    ```bash
    docker run -p 8080:8080 --name sistema-biblioteca-app --link mysql-biblioteca:mysql -d sistema-biblioteca
    ```

    Este comando executa a aplicação e a conecta à rede do contêiner do MySQL.

## Documentação da API

A documentação da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse o seguinte endereço no seu navegador:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
