# 🍽️ Tech Challenge – Fase 2 (FIAP)
> API para gerenciamento de restaurantes, cardápios e usuários  
> Desenvolvido em Java + Spring Boot + MySQL + Docker

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|----|-----|
| Java | 17 | Linguagem principal |
| Spring Boot | 3+ | Framework para API |
| JPA/Hibernate | 7+ | Persistência de dados |
| MySQL | 8 | Banco relacional |
| Docker + Docker Compose | Latest | Deploy e containers |
| Maven | 3.9+ | Gerenciamento de dependências |

---

## 📂 Estrutura do Projeto

```text
src/main/java/br/com/techchallenge/fase2
├── application
│   ├── gateways          # Interfaces que definem como a aplicação acessa o mundo externo (DB, APIs)
│   └── usecases          # Regras de negócio (casos de uso), independentes de tecnologia
│
├── domain
│   ├── entities          # Entidades do domínio
│   └── exceptions        # Exceções de domínio
│
├── infrastructure
│   ├── config            # Configurações gerais: Swagger, Beans, DB, Profiles etc.
│   └── persistence
│       ├── adapters      # Implementações dos Gateways
│       ├── jpa           # Interfaces Spring Data JPA
│       └── springdata    # Implementações/regras JPA adicionais
│
└── interfaces
    ├── api               # Controllers REST (entrada do sistema)
    │   ├── itemcardapio
    │   ├── restaurante
    │   ├── tipousuario
    │   └── usuario
    ├── presenters.dtos   # DTOs de saída (Response)
    └── api/requests      # DTOs de entrada (Request)
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

- 📌 Acesse a Home da aplicação para Regras de Negócio e Api Documentation com Swagger:
 http://localhost:8080/

---
