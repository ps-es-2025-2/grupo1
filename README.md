# App Vagas Van - Sistema de Gestão de Viagens

> Sistema moderno de gerenciamento de transporte compartilhado com foco em otimização de rotas e comunicação eficiente entre motoristas e passageiros.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17.0.2-blue.svg)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.x-red.svg)](https://maven.apache.org/)
[![DDD](https://img.shields.io/badge/Architecture-DDD-green.svg)](https://martinfowler.com/bliki/DomainDrivenDesign.html)

---

## Índice

1. [Visão Geral](#vis%C3%A3o-geral)
2. [Arquitetura e Padrões](#arquitetura-e-padr%C3%B5es)
3. [Pré-requisitos](#pr%C3%A9-requisitos)
4. [Instalação e Execução](#instala%C3%A7%C3%A3o-e-execu%C3%A7%C3%A3o)
5. [Estrutura do Projeto](#estrutura-do-projeto)
6. [Documentação Técnica](#documenta%C3%A7%C3%A3o-técnica)
7. [Padrões de Projeto Implementados](#padr%C3%B5es-de-projeto-implementados)
8. [Decisões de Arquitetura](#decis%C3%B5es-de-arquitetura)
9. [Equipe](#equipe)

---

## Visão Geral

O **App Vagas Van** é um sistema desktop desenvolvido em JavaFX que automatiza e otimiza o processo de organização de viagens de van, substituindo a comunicação manual via WhatsApp por uma plataforma centralizada e eficiente.

### Problemas Resolvidos
- Perda de tempo com confirmações manuais
- Rotas não otimizadas gerando desperdício de combustível
- Comunicação fragmentada em múltiplos grupos
- Falta de visibilidade sobre passageiros confirmados

### Solução Oferecida
- Confirmação digital de participação
- Otimização automática de rotas
- Painel centralizado para o motorista
- Gestão completa de turnos e passageiros

**[Documento de Visão Completo](doc/Documento%20de%20Visão%20–%20Aplicativo%20de%20Gestão%20de%20Vagas%20em%20Van.md)**

---

## Arquitetura e Padrões

### Domain-Driven Design (DDD)

O projeto segue rigorosamente os princípios de **DDD**, com separação clara de responsabilidades em camadas:

```
┌─────────────────────────────────────────┐
│   Presentation Layer (JavaFX UI)       │  ← Controllers, ViewModels
├─────────────────────────────────────────┤
│   Application Layer (Use Cases)        │  ← Orquestração, DTOs
├─────────────────────────────────────────┤
│   Domain Layer (Business Logic)        │  ← Entidades, Value Objects, Domain Services
├─────────────────────────────────────────┤
│   Infrastructure Layer                 │  ← Repositórios, Persistência
└─────────────────────────────────────────┘
```

**Documentação detalhada:**
- [Modelagem de Classes](doc/Modelagem_Classes.md)
- [Diagramas de Sequência](doc/Modelagem%20de%20Interações.md)
- [Diagrama Físico](doc/DiagramFisico.plantuml)

### Princípios SOLID Aplicados

| Princípio | Implementação | Exemplo no Código |
|-----------|---------------|-------------------|
| **S**RP | Cada Use Case tem uma única responsabilidade | [`ConfirmarParticipacaoUseCase`](src/main/java/br/com/appvagasvan/application/usecase/ConfirmarParticipacaoUseCase.java) |
| **O**CP | Interfaces de repositório permitem extensão | [`PassageiroRepository`](src/main/java/br/com/appvagasvan/domain/repository/PassageiroRepository.java) |
| **L**SP | Implementações de repositório são substituíveis | [`PassageiroRepositoryImpl`](src/main/java/br/com/appvagasvan/infrastructure/repository/PassageiroRepositoryImpl.java) |
| **I**SP | Interfaces focadas e específicas | [`TurnoRepository`](src/main/java/br/com/appvagasvan/domain/repository/TurnoRepository.java) |
| **D**IP | Dependência de abstrações, não implementações | [`ServiceLocator`](src/main/java/br/com/appvagasvan/infrastructure/di/ServiceLocator.java) |

**[Boas Práticas e Padrões Detalhados](doc/Boas%20Práticas%20e%20Padrões%20de%20Projeto.md)**

---

## Pré-requisitos

| Ferramenta | Versão | Download |
|------------|--------|----------|
| Java JDK   | 17+    | [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| Maven      | 3.x    | [Apache Maven](https://maven.apache.org/download.cgi) |
| JavaFX     | 17.0.2 | Incluído via Maven |

obs: caso voçê use macos e veja erros na hora de rodar o JavaFX, mude a versão no pom.xml para 22

### Verificação de Instalação

```bash
# Verificar Java
java -version
# Deve exibir: java version "17.x.x"

# Verificar Maven
mvn -version
# Deve exibir: Apache Maven 3.x.x
```

---

## Instalação e Execução

### 1. Clonar o Repositório

```bash
git clone https://github.com/ps-es-2025-2/grupo1.git
cd grupo1
```

### 2. Compilar o Projeto

```bash
mvn clean install
```

**Saída esperada:**
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX.XXX s
```

### 3. Executar a Aplicação

```bash
mvn clean javafx:run
```

A aplicação será iniciada e a janela principal do **Painel do Motorista** será exibida.

### 4. Dados de Teste

O sistema é inicializado automaticamente com dados de exemplo:
- **3 turnos** (Manhã, Tarde, Noite)
- **6 passageiros** cadastrados
- **Confirmações** já registradas para demonstração

Veja: [`DataInitializer.java`](src/main/java/br/com/appvagasvan/DataInitializer.java)

---

## Estrutura do Projeto

```
app-vagas-van/
├── doc/                                    # Documentação completa
│   ├── Documento de Visão.md
│   ├── Casos de Uso Detalhados.md
│   ├── Modelagem_Classes.md
│   ├── Boas Práticas e Padrões.md
│   └── Relatório de Qualidade.md
│
├── src/main/java/br/com/appvagasvan/
│   ├── domain/                             # Camada de Domínio (Lógica de Negócio)
│   │   ├── passageiro/
│   │   │   ├── Passageiro.java            # Aggregate Root - Entidade principal
│   │   │   └── Endereco.java              # Value Object - Imutável
│   │   ├── turno/
│   │   │   ├── Turno.java                 # Aggregate Root - Gerencia vagas
│   │   │   ├── Horario.java               # Value Object
│   │   │   └── TipoTurno.java             # Enum
│   │   ├── motorista/
│   │   │   └── Motorista.java             # Entidade
│   │   ├── viagem/
│   │   │   └── Viagem.java                # Entidade de simulação
│   │   ├── repository/                     # Interfaces (abstração)
│   │   ├── services/
│   │   │   └── OtimizadorRota.java        # Domain Service
│   │   ├── events/                         # Domain Events
│   │   └── exception/                      # Exceções de domínio
│   │
│   ├── application/                        # Camada de Aplicação
│   │   ├── usecase/
│   │   │   ├── ConfirmarParticipacaoUseCase.java    # CU1
│   │   │   ├── VisualizarPassageirosConfirmadosUseCase.java  # CU3
│   │   │   ├── SimularCorridaUseCase.java           # CU6
│   │   │   └── CriarTurnoUseCase.java              # CU7
│   │   └── dto/                            # Data Transfer Objects
│   │
│   ├── infrastructure/                     # 🔌 Camada de Infraestrutura
│   │   ├── repository/
│   │   │   ├── PassageiroRepositoryImpl.java   # In-Memory
│   │   │   └── TurnoRepositoryImpl.java        # In-Memory
│   │   └── di/
│   │       └── ServiceLocator.java         # Injeção de Dependências
│   │
│   └── presentation/                       # Camada de Apresentação
│       ├── controller/
│       │   ├── DriverDashboardController.java
│       │   ├── GerenciarPassageirosController.java
│       │   └── GerenciarTurnosController.java
│       ├── viewmodel/                      # Adaptadores para JavaFX
│       └── converter/                      # DTO → ViewModel
│
├── src/main/resources/
│   └── br/com/appvagasvan/view/           # Arquivos FXML (UI)
│
├── src/test/java/                         # Testes Unitários
│   └── GerenciadorVansTest.java
│
├── pom.xml                                # Maven - Dependências
└── README.md                              # Este arquivo
```

---

## Documentação Técnica

### Casos de Uso Implementados

| ID | Caso de Uso | Status | Documentação | Implementação |
|----|-------------|--------|--------------|---------------|
| CU1 | Confirmar participação na viagem | ✅ Backend | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#1-confirmar-participação-na-viagem) | [`ConfirmarParticipacaoUseCase`](src/main/java/br/com/appvagasvan/application/usecase/ConfirmarParticipacaoUseCase.java) |
| CU2 | Receber lembrete de notificação | ⏳ Futuro | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#2-receber-lembrete-de-notificação) | - |
| CU3 | Visualizar lista de confirmados | ✅ Completo | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#3-visualizar-lista-de-passageiros-confirmados) | [`VisualizarPassageirosConfirmadosUseCase`](src/main/java/br/com/appvagasvan/application/usecase/VisualizarPassageirosConfirmadosUseCase.java) |
| CU4 | Visualizar ordem otimizada | ✅ Completo | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#4-visualizar-ordem-otimizada-de-coleta) | [`OtimizadorRota`](src/main/java/br/com/appvagasvan/domain/services/OtimizadorRota.java) |
| CU5 | Adicionar/Remover passageiros | ✅ Completo | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#5-adicionarremover-passageiros) | [`AdicionarPassageiroAoTurnoUseCase`](src/main/java/br/com/appvagasvan/application/usecase/AdicionarPassageiroAoTurnoUseCase.java) |
| CU6 | Simular corrida | ✅ Completo | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#6-simular-corrida) | [`SimularCorridaUseCase`](src/main/java/br/com/appvagasvan/application/usecase/SimularCorridaUseCase.java) |
| CU7 | Gerenciar turnos e vagas | ✅ Completo | [Detalhes](doc/Descrição%20Detalhada%20dos%20Casos%20de%20Uso.md#7-gerenciar-turnos-e-vagas) | [`CriarTurnoUseCase`](src/main/java/br/com/appvagasvan/application/usecase/CriarTurnoUseCase.java) |

**[Diagrama Global de Casos de Uso](doc/DiagramaGlobalCasosDeUso.md)**

### Modelagem

- [Classes de Análise](doc/Classes%20de%20Análise%20.md)
- [Máquina de Estados](doc/Modelagem%20de%20Estados.md)
- [Processos de Negócio (BPM)](doc/Diagramas_de_Processos_de_Negócio.md)

### Qualidade

- [Feedback de Testes - Entrega 1](doc/Feedback%20de%20Testes%20-%20Entrega%201.md)
- [Relatório de Qualidade - Entrega 2](doc/Relatório%20de%20Qualidade%20-%20Parte%202.md)

---

## Padrões de Projeto Implementados

### 1. **Aggregate Pattern** (DDD)

**Problema:** Como garantir a consistência de dados relacionados?

**Solução:** Turno age como Aggregate Root, controlando acesso aos passageiros.

```java
// ERRADO: Acesso direto
confirmadosIds.add(passageiroId);  // Viola invariantes!

// CORRETO: Através do Aggregate Root
turno.confirmarParticipacao(passageiroId);  // Aplica regras de negócio
```

**Exemplo:** [`Turno.java:70-89`](src/main/java/br/com/appvagasvan/domain/turno/Turno.java)

### 2. **Repository Pattern** (DDD)

**Problema:** Como abstrair a persistência de dados?

**Solução:** Interfaces na camada de domínio, implementação na infraestrutura.

```java
// Interface (Domain)
public interface PassageiroRepository {
    Passageiro salvar(Passageiro passageiro);
    Optional<Passageiro> buscarPorId(Integer id);
}

// Implementação (Infrastructure)
public class PassageiroRepositoryImpl implements PassageiroRepository {
    private final Map<Integer, Passageiro> passageiros = new ConcurrentHashMap<>();
    // ... implementação
}
```

**Benefício:** Permite trocar persistência (in-memory → JPA → MongoDB) sem afetar o domínio.

### 3. **Factory Method** (GoF)

**Problema:** Como garantir criação válida de objetos complexos?

**Solução:** Construtor privado + método estático de fábrica.

```java
public class Turno {
    private Turno(...) { /* privado */ }
    
    // Factory Method com validações
    public static Turno criar(Integer id, Motorista motorista, ...) {
        if (horarioLembrete != null && !horarioLembrete.isAntesDe(horario)) {
            throw new DomainException("Horário de lembrete inválido");
        }
        return new Turno(id, motorista, ...);
    }
}
```

**Exemplo:** [`Turno.java:35-47`](src/main/java/br/com/appvagasvan/domain/turno/Turno.java)

### 4. **Value Object Pattern** (DDD)

**Problema:** Como representar conceitos descritivos sem identidade?

**Solução:** Classes imutáveis com igualdade por valor.

```java
public class Horario {
    private final LocalTime hora;  // final = imutável
    
    private Horario(LocalTime hora) { /* construtor privado */ }
    
    @Override
    public boolean equals(Object o) {
        // Igualdade por valor, não por referência
        return hora.equals(((Horario) o).hora);
    }
}
```

**Exemplos:**
- [`Horario.java`](src/main/java/br/com/appvagasvan/domain/turno/Horario.java)
- [`Endereco.java`](src/main/java/br/com/appvagasvan/domain/passageiro/Endereco.java)

### 5. **Dependency Injection** (IoC)

**Problema:** Como desacoplar e facilitar testes?

**Solução:** Injeção via construtor (atualmente com Service Locator, futuramente Spring).

```java
public class ConfirmarParticipacaoUseCase {
    private final PassageiroRepository passageiroRepository;
    private final TurnoRepository turnoRepository;

    // Injeção via construtor
    public ConfirmarParticipacaoUseCase(
        PassageiroRepository passageiroRepository,
        TurnoRepository turnoRepository
    ) {
        this.passageiroRepository = passageiroRepository;
        this.turnoRepository = turnoRepository;
    }
}
```

**Configuração:** [`ServiceLocator.java`](src/main/java/br/com/appvagasvan/infrastructure/di/ServiceLocator.java)

### 6. **Domain Events** (DDD)

**Estrutura criada** para comunicação assíncrona entre agregados.

```java
// Evento de domínio
public class PassageiroConfirmouPresencaEvent {
    private final Integer passageiroId;
    private final LocalDateTime ocorridoEm;
}

// Publicação (no Agregado)
public void confirmarPresenca() {
    this.confirmado = true;
    this.domainEvents.add(new PassageiroConfirmouPresencaEvent(this.id));
}
```

**Nota:** Estrutura pronta, implementação de handlers será priorizada em entregas futuras.

**Eventos:** [`domain/events/`](src/main/java/br/com/appvagasvan/domain/events/)

### 7. **DTO Pattern** (Transferência de Dados)

**Problema:** Como transferir dados entre camadas sem expor entidades?

**Solução:** Objetos simples com apenas getters.

```java
public class PassageiroOutput {
    private final int id;
    private final String nome;
    
    // Construtor e getters públicos, sem setters
}
```

**Exemplos:** [`application/dto/`](src/main/java/br/com/appvagasvan/application/dto/)

### 8. **Adapter Pattern** (Conversão de Dados)

**Problema:** Como adaptar DTOs para JavaFX Properties?

**Solução:** Classe conversor dedicada.

```java
public class DTOToViewModelConverter {
    public static PassageiroViewModel toViewModel(PassageiroOutput dto) {
        return new PassageiroViewModel(
            dto.getId(),
            dto.getNome(),
            dto.getEndereco(),
            dto.getTelefone()
        );
    }
}
```

**Exemplo:** [`DTOToViewModelConverter.java`](src/main/java/br/com/appvagasvan/presentation/converter/DTOToViewModelConverter.java)

---

## Decisões de Arquitetura

### Por que DDD?

**Justificativa:** O domínio do problema (gestão de viagens com regras de negócio complexas) se beneficia de uma modelagem rica.

**Regras de Negócio Críticas:**
- Capacidade de vagas limitada
- Horário limite para confirmações
- Ordem otimizada de coleta
- Estados de confirmação (PENDENTE → CONFIRMADO → EMBARCADO)

[Modelagem de Estados Detalhada](doc/Modelagem%20de%20Estados.md)

### Por que Repositórios In-Memory no MVP?

**Decisão:** Usar `ConcurrentHashMap` ao invés de banco de dados.

**Justificativa:**
1. **Simplicidade:** MVP focado em validar regras de negócio
2. **Velocidade:** Desenvolvimento mais rápido
3. **Testabilidade:** Testes mais simples sem dependências externas
4. **Arquitetura:** Interface de repositório permite migração futura sem impacto

**Migração Futura:**
```java
// Basta trocar a implementação no ServiceLocator
- PassageiroRepository repo = new PassageiroRepositoryImpl();  // In-Memory
+ PassageiroRepository repo = new PassageiroRepositoryJPA();   // JPA
```

### Por que Service Locator em vez de Spring?

**Decisão:** Implementar DI manualmente com Service Locator.

**Justificativa:**
1. **Didático:** Controle total sobre injeção de dependências
2. **Peso:** Spring adiciona complexidade desnecessária para o MVP
3. **JavaFX:** Integração mais simples sem frameworks pesados

**Migração Futura:** A arquitetura está preparada para Spring Boot.

### Separação Presentation ↔ Domain

**Decisão:** ViewModels com JavaFX Properties, DTOs imutáveis, domínio puro.

**Justificativa:**
- **Testabilidade:** Domínio testável sem JavaFX
- **Isolamento:** Mudanças na UI não afetam negócio
- **Reuso:** Lógica de negócio reutilizável em outras interfaces (web, mobile)

```
Request → Controller → Use Case → Domain → Repository
                ↓         ↓
              DTO    ViewModel (JavaFX Properties)
```

---

## Equipe

| Nome | GitHub | Cargo | Responsabilidades |
|------|--------|-------|-------------------|
| Gabriel Freitas dos Reis | [@GabrielFRails](https://github.com/GabrielFRails) | Arquiteto/Tech Lead | Arquitetura DDD, Modelagem |
| Gabriel Rodrigues Silva | [@Gabriellrs](https://github.com/Gabriellrs) | Frontend | Controllers, FXML, UI/UX |
| Laura Martins Vieira Gonçalves | [@lauramvg1821](https://github.com/lauramvg1821) | Backend | Use Cases, Domain Services |
| Léia Santos Costa | [@Leia27](https://github.com/Leia27) | Analista de Requisitos | Casos de Uso, Documentação |
| Tallya Jesus Sousa Barbosa | [@tallya01](https://github.com/tallya01) | QA/Tester | Testes Unitários, Validação |