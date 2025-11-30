## 1. Detalhamento da Máquina de Estados

A máquina de estados modela o ciclo de vida da entidade **Confirmação** (participação do Passageiro em um Turno), garantindo que as transições sejam governadas pelas Regras de Negócio (RNs).

### 2.1. Tabela de Estados e Regras

| Estado | Descrição | Regras de Negócio e Impacto |
| :--- | :--- | :--- |
| **PENDENTE** | O passageiro está associado ao turno, mas ainda não confirmou sua presença. | O sistema deve enviar lembretes de notificação (CU2). Se o limite for atingido, move para **NAO\_CONFIRMADO**. |
| **CONFIRMADO** | O passageiro garantiu sua vaga na viagem. | O Agregado `Turno` decrementa a contagem de vagas disponíveis (RN1 do CU1). O passageiro é incluído na `Ordem Otimizada` (CU4, CU6). |
| **CANCELADO** | O passageiro cancelou sua presença ou foi removido pelo motorista. | A vaga é liberada (incrementa `CapacidadeVagas`). Não é incluído na rota de coleta. |
| **EMBARCADO** | O motorista registrou que o passageiro foi coletado e iniciou a viagem. | **Estado Final de Sucesso.** |
| **NAO\_CONFIRMADO** | O prazo limite para confirmação (RN2 do CU1) foi ultrapassado sem ação do passageiro. | **Estado Final de Falha.** Não é incluído na rota. |

### 1.2. Tabela de Transições

| Transição (Evento) | De | Para | Disparador / Condição |
| :--- | :--- | :--- | :--- |
| **`Passageiro Confirma`** | PENDENTE / CANCELADO | CONFIRMADO | Ação do usuário **antes** do Horário Limite (RN2 do CU1). |
| **`Motorista Adiciona`** | PENDENTE | CONFIRMADO | Ação do Motorista (CU5) – ignora limites. |
| **`Limite Excedido`** | PENDENTE | NAO\_CONFIRMADO | Acionada por um serviço agendado (job) após o horário limite. |
| **`Passageiro Cancela`** | CONFIRMADO | CANCELADO | Ação do usuário (A1 do CU1). |
| **`Motorista Marca`** | CONFIRMADO | EMBARCADO | Ação do Motorista ao finalizar a coleta (Pós-Viagem). |

<img width="1228" height="757" alt="Captura de tela 2025-11-30 142924" src="https://github.com/user-attachments/assets/43dcdd47-3557-4a57-b587-7040cc50d186" />
