
# Modelagem de Interações (Diagrama de Sequência)

Este artefato descreve o comportamento dinâmico do sistema, mostrando a sequência de chamadas de método (mensagens) trocadas entre os objetos para realizar o Caso de Uso principal: **Confirmar participação na viagem (CU1)**.

## 1. Detalhamento das Interações (Mensagens Chave)

Esta seção descreve a responsabilidade de cada objeto na execução do Caso de Uso, validando o princípio de Separação de Responsabilidades (SRP).

| Passo | Mensagem/Método | Camadas Envolvidas | Responsabilidade |
| :--- | :--- | :--- | :--- |
| **2** | `execute(input)` | Apresentação -> **Aplicação** | Inicia o Caso de Uso, passando o DTO de entrada com os dados do usuário. |
| **3, 5** | `findById()` | Aplicação -> **Infraestrutura** | O **UseCase** busca o **Agregado Raiz** (`Turno`) para carregá-lo na memória. |
| **7** | `adicionarConfirmacao(P1)` | Aplicação -> **Domínio** | Chamada Crítica: O **UseCase** delega a execução da regra de negócio (verificar capacidade) ao **Agregado** (`Turno`). |
| **7.1** | `verificaDisponibilidade()` | **Domínio (Interno)** | **Regra de Negócio (RN):** O Agregado verifica se a vaga está disponível. Se não estiver, lança uma `DomainException`. |
| **9** | `save(turno)` | Aplicação -> **Infraestrutura** | O **UseCase** utiliza o **Repository** para persistir o novo estado consistente do Agregado no banco de dados. |

---


<img width="1415" height="870" alt="PLATUML" src="https://github.com/user-attachments/assets/5a892b56-7ac4-4f9b-a06a-3fc5215c2779" />

