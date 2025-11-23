# 🍽️ Tech Challenge – Fase 2 (FIAP)
> API para gerenciamento de restaurantes, cardápios e usuários  
> Desenvolvido em Java + Spring Boot + MySQL + Docker

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 17 | Linguagem principal |
| Spring Boot | 3+ / 4+ | Framework para API |
| JPA/Hibernate | 7+ | Persistência de dados |
| MySQL | 8 | Banco relacional |
| Docker + Docker Compose | Latest | Deploy e containers |
| Maven | 3.9+ | Gerenciamento de dependências |

---

## 📂 Estrutura do Projeto

```text
src/main/java/br/com/techchallenge/fase2
├── application.usecases # Casos de uso (regras de negócio)
├── domain # Entidades e modelos de domínio
├── infrastructure
│ ├── config # Configurações (Swagger, DB etc.)
│ └── persistence # Repositórios JPA
└── interfaces/api # Controllers (REST)
```

---

## 🏗️ Como Rodar Localmente

### ✔️ Pré-requisitos
- **JDK 17**
- **Maven**
- **Docker Desktop**
- (Opcional) Postman

---

### ▶️ Executando com Maven (sem Docker)

Aponte a aplicação para o ambiente de Desenvolvimento - Dev no arquivo application.yml

Em seguida execute:

```bash
mvn clean package
mvn spring-boot:run
```
---

## Documentação

- 📌 Acesse o Swagger:
 http://localhost:8080/swagger-ui/index.html

- 📌 Acesse a Home da aplicação para Regras de Negócio:
 http://localhost:8080/

---
