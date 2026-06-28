# Sistema de Controle de Vendas

Sistema de gerenciamento de vendas
com cadastro de clientes,
produtos, vendas,
controle de caixa e autenticação JWT.

## Tecnologias
- **Backend:** Java 21 + Spring Boot 3.4.3
- **Banco de Dados:** PostgreSQL 17
- **Upload de Imagens:** Cloudinary
- **Autenticação:** JWT (JSON Web Token)
- **Build:** Maven
- **Mapeamento:** MapStruct + Lombok
- **Containerização:** Docker / Docker Compose

## Pré-requisitos

- Java 21+
- Maven 3.8+
- Docker

## Instalação

### 1. Clonar o projeto

```bash
git clone https://github.com/gustavomazur/Controlle-de-Caixa.git
cd Controle-venda
```

### 2. Configurar variáveis de ambiente

Copie o arquivo de exemplo:

```bash
cp envs/.env.example envs/.env.local
```
Edite o `envs/.env.local` com suas configurações:

```env
SPRING_PROFILES_ACTIVE=local
DB_URL=jdbc:postgresql://localhost:5432/sistema_de_loja_db
DB_USER=app_user
DB_PASSWORD=sua_senha
JWT_SECRET=seu_secreto_jwt_aqui
CLOUDINARY_CLOUD_NAME=seu_cloud_name
CLOUDINARY_API_KEY=sua_api_key
CLOUDINARY_API_SECRET=sua_api_secret
```

### 3. Credenciais do Cloudinary (opcional)
Se quiser salvar foto, é necessário

Para ativar o upload de imagens:
1. Acesse https://cloudinary.com/console
2. Crie uma conta gratuita
3. Copie as credenciais para o `envs/.env.local`

### 4. Configurar os Git Hooks (recomendado)

```bash
git config core.hooksPath .githooks
```

Isso ativa a validação de mensagens de commit (Conventional Commits) e verificações antes do push.

## Rodar o projeto

```bash
docker-compose up -d
```

Isso sobe o PostgreSQL e a aplicação. A aplicação ficará disponível em `http://localhost:8080`.


## Endpoints da API

### Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/auth/cadastro` | Cadastrar novo usuário |
| POST | `/api/auth/login` | Fazer login e obter token JWT |

### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/clientes` | Listar todos os clientes |
| GET | `/api/clientes/{id}` | Buscar cliente por ID |
| GET | `/api/clientes/nome/{nome}` | Buscar cliente por nome |
| POST | `/api/clientes` | Cadastrar cliente (multipart/form-data) |
| PUT | `/api/clientes/{id}` | Atualizar cliente |
| DELETE | `/api/clientes/{id}` | Deletar cliente |

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produto` | Cadastrar produto |
| PUT | `/produto` | Atualizar produto (por código de barras ou nome) |
| DELETE | `/produto` | Deletar produto (por código de barras ou nome) |

### Vendas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/vendas` | Registrar venda |

### Caixa

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/caixa/saldo` | Consultar saldo atual |
| POST | `/api/caixa/entrada` | Registrar entrada no caixa |
| POST | `/api/caixa/saida` | Registrar saída do caixa |
| GET | `/api/caixa/historico` | Histórico de movimentações (por período) |

## Estrutura do projeto

```
#Controle-venda
.githooks/
├── commit-msg              # Valida Conventional Commits
└── pre-push                # Verifica formatação, estilo e testes

envs/
├── .env.example            # Template de variáveis de ambiente
└── .env.local              # Configurações locais (gitignorado)


src/
├── main/
│   ├── java/br/com/contadora/contadora_api/
│   │   ├── config/          # Configurações (Security, Cloudinary, JWT)
│   │   ├── controller/      # REST Controllers
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── mapper/          # Mapeamento DTO <-> Entidade (MapStruct)
│   │   ├── model/           # Entidades JPA
│   │   │   ├── caixa/       # Caixa e movimentações
│   │   │   ├── cliente/     # Cliente e endereço
│   │   │   ├── produto/     # Produto
│   │   │   ├── usuario/     # Usuário
│   │   │   └── venda/       # Venda e itens
│   │   ├── repository/      # Repositórios JPA
│   │   └── service/         # Lógica de negócio
│   └── resources/
│       └── application.properties
└── test/
```

## Fluxo de autenticação

1. Usuário se cadastra via `POST /api/auth/cadastro`
2. Sistema cria usuário com senha criptografada (BCrypt)
3. Usuário faz login via `POST /api/auth/login`
4. Sistema retorna token JWT (válido por 24h)
5. Token é enviado no header `Authorization: Bearer <token>` em todas as requisições `/api/**`
6. Se token expirar, retorna 401 não autorizado

## Git Hooks

O projeto inclui hooks git em `.githooks/` para manter a qualidade do código:

- **commit-msg:** Valida se a mensagem do commit segue o padrão Conventional Commits (não bloqueante, apenas avisa)
- **pre-push:** Executa verificações antes do push:
  1. Formatação do código (Spotless)
  2. Padrão de código (Checkstyle)
  3. Testes unitários

Para ativar:

```bash
git config core.hooksPath .githooks
```

## Importante

- **NUNCA** commite o arquivo `envs/.env.local` com dados reais
- O `.gitignore` já protege arquivos sensíveis
- Use `envs/.env.example` como template
