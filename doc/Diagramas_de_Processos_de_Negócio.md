##  Detalhamento em Texto: Gerenciamento de Viagens e Passageiros

| Passo | Ator | Elemento BPMN | Descrição da Ação / Regra de Negócio |
| :---: | :--- | :--- | :--- |
| **Início** | Motorista | Evento de Início | Motorista acessa o painel de gestão do aplicativo. |
| 1 | Motorista | Atividade | Selecionar a opção de **Gerenciar Turnos** (CU 7) ou **Gerenciar Passageiros** (CU 5). |
| 2 | Sistema | Gateway (XOR) | O sistema direciona o fluxo para a gestão de Turnos ou Passageiros. |
| **Gestão de Turnos (CU 7)** | | | |
| 3 | Sistema | Gateway (XOR) | Deseja **Criar/Editar** ou **Excluir** um Turno? |
| 4 | Motorista | Atividade | (Se Criar/Editar) Informar **Nome**, **Horário** e **Capacidade** (RN1: Capacidade > 0). |
| 5 | Sistema | Atividade | Validar e Salvar Configurações do Turno. |
| 6 | Sistema | Gateway (XOR) | (Se Excluir) **Existem Passageiros Associados** ao Turno? |
| 7 | Sistema | **Fluxo de Exceção** | **Se SIM (RN2):** Exibir erro e **retornar ao Passo 3** (Impede exclusão de turno ativo). |
| 8 | Sistema | Atividade | **Se NÃO:** Remover Turno do Sistema. |
| **Gestão de Passageiros (CU 5)** | | | |
| 9 | Motorista | Atividade | Selecionar Turno para Gerenciamento de Lista (RN1: Passageiro associado ao Turno). |
| 10 | Sistema | Gateway (XOR) | Deseja **Adicionar** ou **Remover** Passageiro do Turno? |
| 11 | Sistema | Atividade | (Se Adicionar) Associar Passageiro ao Turno. |
| 12 | Sistema | Atividade | (Se Remover) Desassociar Passageiro do Turno. |
| 13 | Sistema | Atividade | **Notificar** que a lista do Turno foi atualizada. |
| **Fim** | Sistema | Evento de Fim | Estrutura de Viagem Configurada e Pronta. |


## Diagrama de Processos de Negócio (BPM)

<img width="2751" height="2777" alt="Untitled diagram-2025-12-01-011333" src="https://github.com/user-attachments/assets/3961fd53-f2cf-40c8-bc9e-b4256e5cd20a" />
