
#  3.8. Boas Práticas e Padrões de Projeto (Guia de Implementação)

Este documento estabelece as diretrizes e os padrões obrigatórios para a implementação do projeto Vagas Van, garantindo a conformidade com a arquitetura Domain-Driven Design (DDD) adotada.

## 1. Padrões Arquiteturais Fundamentais (DDD)

Estes padrões governam a estrutura e a organização do código em camadas:

| Padrão | Definição no Projeto | Regra de Uso |
| :--- | :--- | :--- |
| **Agregado (Aggregate)** | Uma unidade de Entidades e Value Objects tratada como um conjunto coeso. | **`Turno`** é um Agregado Raiz (Root). O acesso a qualquer dado interno (`confirmadosIds`) só pode ser feito através da raiz (`Turno`). |
| **Repositório (Repository)** | Interface que simula uma coleção em memória para recuperação e armazenamento de Agregados. | As interfaces (`TurnoRepository`, `PassageiroRepository`) residem na camada de **Domínio**. A implementação concreta (ex: JDBC, JPA) reside estritamente na camada de **Infraestrutura**. |
| **Serviço de Aplicação / Use Case** | Classe responsável por orquestrar a execução de uma funcionalidade específica (Caso de Uso). | Classes como **`ConfirmarParticipacaoUseCase`** são responsáveis por buscar Agregados, chamar métodos de domínio e persistir o resultado. **Não contêm regras de negócio.** |

## 2. Padrões de Design de Código

Estes padrões são essenciais para a manutenibilidade e robustez da camada de Domínio.

### A. Value Object (Objeto de Valor)

* **Finalidade:** Representar conceitos descritivos do domínio definidos pela **igualdade de seus atributos**, não pela identidade.
* **Diretrizes:**
    * Todos os Value Objects (ex: `CapacidadeVagas`, `Endereco`, `Horario`) devem ser **imutáveis**.
    * Os atributos devem ser declarados como `final`.
    * Deve-se implementar corretamente os métodos **`equals()`** e **`hashCode()`** para comparação por valor.

### B. Injeção de Dependência (Dependency Injection - DI)

* **Finalidade:** Desacoplar classes e facilitar a testabilidade.
* **Diretrizes:**
    * Dependências (interfaces de Repositório, serviços externos) devem ser passadas para as classes (principalmente **Use Cases**) via **construtor**.
    * O uso de padrões globais (como Singleton via `.getInstance()`) está **proibido** para objetos de serviço e repositório.

### C. Factory Method (Método de Fábrica)

* **Finalidade:** Encapsular a lógica de criação de objetos complexos e garantir a aplicação de regras de validação iniciais.
* **Diretrizes:**
    * Usado para criar Agregados Raiz (**`Turno`**, **`Passageiro`**), garantindo que os objetos sejam criados em um estado consistente.
    * O construtor da Entidade/Agregado deve ser privado, forçando o uso do Factory Method.

## 3. Boas Práticas de Implementação

| Prática | Aplicação Obrigatória |
| :--- | :--- |
| **Isolamento de Domínio** | A camada de **Domínio** (Entidades, Value Objects) **não pode** ter referências ou dependências de frameworks de persistência (ex: JPA) ou de interface (ex: JavaFX). |
| **Tratamento de Exceções** | Regras de negócio violadas no Domínio devem lançar **Exceções de Domínio** específicas (ex: `CapacidadeEsgotadaException`). O tratamento final e a conversão para mensagens de erro ficam na camada de **Apresentação**. |
| **TDD (Test-Driven Development)** | A lógica de negócio essencial no **Domínio** deve ser desenvolvida com foco em testes unitários para garantir que as regras e invariantes sejam mantidas. |
| **Nomenclatura** | Priorizar nomes que reflitam o Ubiquitous Language (Linguagem Ubíqua) do domínio (ex: `confirmarParticipacao()` em vez de `updateStatus()`). |

---
