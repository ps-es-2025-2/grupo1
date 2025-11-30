# 📝 3.7. Modelagem de Classes de Projeto (DDD)

Este artefato detalha a estrutura das classes do sistema, refinando as Classes de Análise (3.4) e aplicando os padrões da Arquitetura **Domain-Driven Design (DDD)**, conforme o Documento de Decisões Arquiteturais.

## 1. Diagrama de Classes de Projeto (UML)

O diagrama de classes de projeto (UML) é a representação visual da estrutura de código do sistema.

**Ajuste o caminho da imagem abaixo de acordo com onde você salvou o seu PNG:**
![Diagrama UML de Classes de Projeto (DDD)](assets/diagrama_classes_projeto.png)

*O código-fonte PlantUML deste diagrama (para edição) deve ser salvo em um arquivo separado: `diagrama_classes_projeto.puml`.*

---

## 2. Detalhamento Técnico das Classes (Especificação)

### A. Detalhamento do Agregado `Turno` (Agreggate Root)

| Elemento | Tipo DDD/UML | Tipo de Dados (Linguagem) | Detalhes e Invariantes |
| :--- | :--- | :--- | :--- |
| **`Turno`** | Aggregate Root | | Encapsula a lista de confirmados e garante a consistência do turno. |
| `id` | Value Object | `TurnoId` | Chave única e imutável do agregado. |
| `horario` | Value Object | `LocalTime` | Horário programado para o turno (HH:mm). |
| `motoristaId` | Atributo | `MotoristaId` | FK para o motorista responsável. |
| `confirmadosIds` | Atributo | `Set<PassageiroId>` | Coleção de IDs. Uso de `Set` garante IDs únicos. |
| **`adicionarConfirmacao(id)`** | Método | `void` | **Regra de Negócio (RN1 do CU1):** Verifica se `confirmadosIds.size() < capacidade`. Lança `CapacidadeEsgotadaException` se cheio. |
| **`removerConfirmacao(id)`** | Método | `void` | Remove o passageiro do `confirmadosIds`. |

### B. Detalhamento de Value Objects Chave

| Value Object | Atributo Interno | Tipo de Dados (Linguagem) | Regras de Invariância (Imutabilidade) |
| :--- | :--- | :--- | :--- |
| **`PassageiroId`** | `value` | `UUID` ou `Integer` | Deve ser não nulo. O objeto é **imutável** após a criação. |
| **`CapacidadeVagas`** | `valor` | `Integer` | **Regra:** `valor` deve ser maior que zero. Imutável. |
| **`Endereco`** | `rua`, `numero`, `coord` | `String`, `String`, `Coordenadas` | **Regra:** `rua` e `numero` não podem ser vazios. Imutáveis. |
| **`Horario`** | `value` | `LocalTime` | Deve ser um formato de hora válido (RN2 do CU1). Imutável. |

### C. Especificação da Camada de Aplicação

| Classe | Tipo DDD | Responsabilidades Chave | Dependências (Injetadas) |
| :--- | :--- | :--- | :--- |
| **`ConfirmarParticipacaoUseCase`** | Application Service | 1. Receber DTO de Input. 2. Buscar `Turno` pelo Repositório. 3. Chamar o método de domínio: `turno.adicionarConfirmacao()`. 4. Persistir o `Turno` atualizado. | `TurnoRepository`, `PassageiroRepository` |
| **`ConfirmarParticipacaoInput`** | DTO (Input) | Recebe os dados brutos da interface: `TurnoId` e `PassageiroId`. | Usado para transferir dados da **Apresentação** para a **Aplicação**. |


<img width="1862" height="672" alt="Captura de tela 2025-11-30 135020" src="https://github.com/user-attachments/assets/259f36f3-244c-4ad2-acb6-a60579d79e58" />
