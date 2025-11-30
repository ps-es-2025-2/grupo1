#  Plano de Implementação e Testes (WIP)

Este documento estabelece a ordem prioritária para a codificação e a estratégia de testes, garantindo que a implementação siga estritamente a Modelagem de Classes de Projeto (3.7) e as Boas Práticas de Padrões (3.8).

## 1. Estratégia de Codificação: De Dentro para Fora

A implementação deve começar pela camada mais interna (Domínio), que contém as regras de negócio puras, e avançar para as camadas externas (Apresentação), que dependem dela.

### Fase 1: Domínio (Regras de Negócio Puras)

**Objetivo:** Implementar o Agregado Turno e suas regras de validade.

| Sub-Item | Classes Envolvidas (3.7) | Ação de Codificação | Teste Obrigatório |
| :--- | :--- | :--- | :--- |
| **1.1. Value Objects** | `CapacidadeVagas`, `TurnoId`, `Endereco`. | Criar as classes como **Imutáveis** (`final`) e implementar validações no construtor (Ex: Capacidade > 0). | **Testes Unitários:** Verificar se `CapacidadeVagas` lança exceção se for zero ou negativo. |
| **1.2. Agregado Turno** | `Turno`. | Implementar os métodos de domínio: `adicionarConfirmacao()`, `removerConfirmacao()`. | **Testes Unitários (TDD):** Garantir que `Turno` lança a `CapacidadeEsgotadaException` e a `DomainException` quando as regras são violadas. |
| **1.3. Exceções** | `CapacidadeEsgotadaException`, `TurnoNaoEncontradoException`. | Definir a hierarquia de exceções customizadas para a camada de Domínio. | N/A |

### Fase 2: Infraestrutura e Interfaces

**Objetivo:** Criar os contratos de persistência para as classes de Domínio.

| Sub-Item | Classes Envolvidas (3.7) | Ação de Codificação | Teste Obrigatório |
| :--- | :--- | :--- | :--- |
| **2.1. Interfaces** | `TurnoRepository`, `PassageiroRepository`. | Criar as interfaces na camada de Domínio. | N/A |
| **2.2. Mock/In-Memory** | `TurnoRepositoryInMemory` (exemplo). | Criar uma implementação simples do Repositório (usando `HashMap` ou `List`) para simular o banco de dados e facilitar os testes da Aplicação. | **Testes de Repositório:** Verificar se `save()` e `findById()` funcionam corretamente na implementação em memória. |

### Fase 3: Aplicação (Use Cases)

**Objetivo:** Implementar a lógica de orquestração do Caso de Uso principal.

| Sub-Item | Classes Envolvidas (3.7) | Ação de Codificação | Teste Obrigatório |
| :--- | :--- | :--- | :--- |
| **3.1. DTOs** | `ConfirmarParticipacaoInput`, `TurnoOutput`. | Criar os objetos simples para transporte de dados (Input/Output). | N/A |
| **3.2. Use Case Central** | `ConfirmarParticipacaoUseCase`. | Codificar o fluxo completo do **Diagrama de Sequência (3.9)**: carregar o Agregado, chamar o método de domínio e salvar o Agregado. | **Testes de Aplicação:** Testar o `ConfirmarParticipacaoUseCase` injetando os Repositórios Mockados (Fase 2). Deve-se verificar se ele trata e lança corretamente as exceções do Domínio. |

### Fase 4: Apresentação (Interface)

**Objetivo:** Conectar a tela aos Use Cases da aplicação.

| Sub-Item | Classes Envolvidas (3.7) | Ação de Codificação | Teste Obrigatório |
| :--- | :--- | :--- | :--- |
| **4.1. Controller** | `TelaConfirmacaoViagem`. | O Controller deve receber o `ConfirmarParticipacaoUseCase` via Injeção de Dependência e ser o responsável por: 1) Converter dados da tela para o DTO de Input. 2) Chamar o `UseCase.execute()`. 3) Tratar o `ErroOutput` e exibir a mensagem na tela. | **Testes de Componente:** Verificar se o Controller chama o Use Case correto e se a conversão de DTOs funciona. |

## 2. Padrões de Teste Obrigatórios

| Tipo de Teste | Camada de Foco | Responsabilidade |
| :--- | :--- | :--- |
| **Unitário (TDD)** | **Domínio** | 100% de cobertura da lógica de negócio. **Deve ser escrito antes do código de produção** para Entidades e Value Objects. |
| **Integração** | **Aplicação** | Validar se o fluxo do **UseCase** (3.9) funciona corretamente, garantindo que o Repositório e o Domínio se comunicam sem falhas. |
