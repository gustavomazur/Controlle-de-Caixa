# Contadora API

API para gerenciamento financeiro e controle de estoque de uma loja.

## Descrição

A **Contadora API** é uma aplicação backend desenvolvida em Java com Spring Boot. Ela permite o cadastro de produtos, controle de estoque, registro de vendas e cálculo de lucros. O projeto é ideal para quem deseja entender como construir uma API RESTful completa com integração a banco de dados e containerização.

## Tecnologias

*   **Java 17**
*   **Spring Boot 3**
*   **Spring Data JPA**
*   **MySQL**
*   **Flyway** (Gerenciamento de Migrations)
*   **Docker & Docker Compose**
*   **Lombok**
*   **Maven**

## Pré-requisitos

Para rodar este projeto localmente, você precisará ter instalado:

*   **Java 17** ou superior
*   **Docker** e **Docker Compose**
*   **Git**

## Instalação e Execução

Siga os passos abaixo para rodar a aplicação no seu computador:

### 1. Clonar o repositório

Abra o terminal e execute:

```bash
git clone https://github.com/gustavomazur/contadora-api.git
cd contadora-api
```

### 2. Subir o Banco de Dados

O projeto utiliza um arquivo `docker-compose.yaml` para facilitar a configuração do banco de dados MySQL. Execute:

```bash
docker-compose up -d
```

Isso irá baixar a imagem do MySQL e iniciar o container na porta `3306`.

### 3. Rodar a Aplicação

Você não precisa ter o Maven instalado globalmente, pois o projeto inclui o Maven Wrapper.

**No Linux/macOS:**
```bash
./mvnw spring-boot:run
```

**No Windows (Prompt de Comando ou PowerShell):**
```cmd
mvnw spring-boot:run
```

A aplicação iniciará e estará disponível em `http://localhost:8080`.

## Configuração do Banco de Dados

As credenciais do banco de dados já estão configuradas por padrão para o ambiente de desenvolvimento:

*   **URL**: `jdbc:mysql://localhost:3306/contador_loja`
*   **Usuário**: `app_user`
*   **Senha**: `app_password`

Caso queira alterar, edite o arquivo `src/main/resources/application.properties` e o `docker-compose.yaml`.

## Endpoints da API

Aqui estão os principais endpoints disponíveis para testar (você pode usar o Postman ou Insomnia):

### Produtos

*   **Cadastrar Produto**
    *   `POST /produto`
    *   Body (JSON):
        ```json
        {
          "nome": "Camiseta",
          "preco": 49.90,
          "quantidade": 100
        }
        ```

*   **Listar Produtos**
    *   `GET /produto`

*   **Atualizar Produto**
    *   `PUT /produto`
    *   Body (JSON):
        ```json
        {
          "id": 1,
          "nome": "Camiseta Azul",
          "preco": 55.00
        }
        ```

*   **Deletar Produto**
    *   `DELETE /produto/{id}`

*   **Vender Produto**
    *   `POST /produto/{id}/vender/{quantidade}`
    *   Exemplo: `POST /produto/1/vender/2` (Vende 2 unidades do produto ID 1)

*   **Calcular Lucro Total**
    *   `GET /produto/lucro-total`

## Estrutura do Projeto

O código fonte principal encontra-se em `src/main/java/br/com/contadora/contadora_api`.

## Licença

Este projeto está licenciado sob a licença [MIT](LICENSE).
