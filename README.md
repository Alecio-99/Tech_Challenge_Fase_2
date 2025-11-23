# TechChallenge — Fase 2

Sistema de Gestão de Restaurantes (FIAP — Pós em Arquitetura e Desenvolvimento Java)

Este projeto foi desenvolvido como parte do Tech Challenge da Fase 2 da pós-graduação FIAP, com foco em:
- Arquitetura Limpa (Clean Architecture)
- DDD (Domain-Driven Design)
- Spring Boot 3
- Java 17
- Docker
- MySQL
- Swagger

O objetivo é fornecer uma API para gestão compartilhada de restaurantes, permitindo que múltiplos estabelecimentos utilizem um sistema unificado com custos reduzidos. O sistema permite cadastro de tipos de usuário, usuários, restaurantes e itens de cardápio.

---

## Arquitetura Utilizada

🔹 Domain-Driven Design (DDD)

🔹 Clean Architecture

🔹 Separação clara em camadas:

src/main/java/

├── domain           # Entidades, regras e contratos

├── application      # Casos de uso (UseCases)

├── interfaces       # Controllers + DTOs (APIs REST)

└── infrastructure   # Persistência, JPA, Docker, DB adapters

✔ O domínio não depende de framework algum

✔ Frameworks só existem nas camadas externas

---

##  Funcionalidades da API
Recurso	Operações:

- Tipo de Usuário	CRUD completo 
- Usuário	CRUD completo 
- Restaurante	CRUD completo + vínculo com Dono 
- Item de Cardápio	CRUD completo + vínculo com Restaurante

---

## Perfis da Aplicação
Ambiente	Banco	Como executar
DEV	H2 (memória)	mvn spring-boot:run
PROD	MySQL (Docker)	docker-compose up --build

### Seleção via application.yml:

spring:
profiles:
active: dev   # dev ou prod

#### Configuração do Banco
🔧 Ambiente DEV — H2

Não necessita Docker.
Acesse o console (opcional):

http://localhost:8080/h2-console

#### Ambiente PROD — Docker + MySQL

📌 Arquivo: docker-compose.yml

docker-compose up --build

💡 A API sobe automaticamente com SPRING_PROFILES_ACTIVE=prod.

---

## Documentação da API — Swagger

Depois de instalar o Swagger (será gerado na sequência), acesse:

http://localhost:8080/swagger-ui.html

💡 O link será ativado após adicionar as dependências do SpringDoc.

---

## Como Rodar o Projeto (Passo a Passo)
▶️ Ambiente de Desenvolvimento (H2)
mvn spring-boot:run

🐳 Ambiente de Produção (Docker + MySQL)
docker-compose up --build

🧪 Executar Testes
mvn test

---

## Estrutura de Pastas (Resumo)
infrastructure/
├── persistence/
│   ├── jpa/              # Entidades JPA (banco)
│   ├── repositoryimpl/   # Implementações dos Repositórios
│   └── springdata/       # Interfaces Spring Data
├── config/               # Configurações extras
└── docker/               # Docker (opcional)

domain/
├── entities/             # Entidades do negócio
├── exceptions/           # Regras de domínio
└── repositories/         # Interfaces de persistência

application/
└── usecases/             # Casos de uso (lógica)

interfaces/
├── api/                  # Controllers REST
└── dtos/                 # DTOs Request/Response

---

## Desenvolvedores
Nome	Papel
Giovana Leite Scalabrini	Backend / Arquitetura
Alecio Silveira Araujo	Backend / Infraestrutura

---

## Instituição

FIAP — Pós-Graduação em Arquitetura e Desenvolvimento Java

---