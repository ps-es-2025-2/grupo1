## Classes de Análise para o Aplicativo Vagas Van.

---

### A. Classes de Entidade 

Estas classes representam os principais conceitos e dados que o sistema deve armazenar e gerenciar a longo prazo.

| Classe | Propósito no Sistema | Exemplos de Atributos Chave | Casos de Uso Relacionados |
| :--- | :--- | :--- | :--- |
| **Passageiro** | Identificação e dados de contato/localização do usuário da van. | `id_passageiro (PK)`, `nome`, `endereco_coleta`, `telefone`, `device_token` | CU 1, 2, 3, 4, 5, 6 |
| **Motorista** | Identificação e dados de acesso do usuário gestor das viagens. | `id_motorista (PK)`, `nome`, `email`, `senha_hash`, `endereco_base` | CU 3, 4, 5, 6, 7 |
| **Turno** | Representa uma viagem organizada (ex: 'Turno Manhã'). | `id_turno (PK)`, `id_motorista (FK)`, `nome_turno`, `horario`, `capacidade_vagas` | CU 1, 2, 3, 5, 7 |
| **Confirmacao** | O registro da participação de um Passageiro em um Turno específico. | `id_confirmacao (PK)`, `id_passageiro (FK)`, `id_turno (FK)`, `status_confirmacao`, `data_registro` | CU 1, 3, 6 |
| **Notificacao** | Armazena os dados dos lembretes agendados e enviados. | `id_notificacao (PK)`, `id_passageiro (FK)`, `mensagem`, `data_agendamento`, `status_envio` | CU 2 |

---

### B. Classes de Controle 

São responsáveis por orquestrar a execução dos Casos de Uso mais complexos, aplicando as Regras de Negócio e mediando a comunicação entre Fronteira e Entidade.

| Classe | Função de Orquestração | Responsabilidades e Regras de Negócio | Casos de Uso Envolvidos |
| :--- | :--- | :--- | :--- |
| **ControladorConfirmacao** | Gerencia o processo de registro de participação. | Recebe dados da tela, verifica **limites de vagas e horário** (RN2 do CU1) e atualiza a Entidade `Confirmacao`. | CU 1 |
| **GerenciadorTurnos** | Controla a manutenção dos grupos de viagem e associações. | Cria/edita/exclui `Turnos`, e aplica a **RN2 do CU7** (Não excluir turno com passageiros associados). | CU 5, CU 7 |
| **CalculadorOtimizacaoRota** | Implementa a lógica de otimização de rota e simulação de percurso. | Interage com a Entidade `Passageiro` e o `ServicoGeolocalizacao` para calcular a **ordem mais eficiente** (RN1 do CU4). | CU 4, CU 6 |
| **GeradorLembretes** | Automação do processo de notificação. | Consulta a Entidade `Confirmacao` para identificar pendências e aciona o `ServicoNotificacaoPush`. | CU 2 |

---

### C. Classes de Fronteira 

Representam a interface com os Atores (telas) ou com serviços externos necessários para o funcionamento do sistema.

| Classe | Responsabilidade | Atores/Serviços Envolvidos | Relacionamento Principal |
| :--- | :--- | :--- | :--- |
| **TelaConfirmacaoViagem** | Interface para o Passageiro confirmar sua presença. | Passageiro | CU 1 |
| **TelaDashboardMotorista** | Exibe a lista de confirmados, a rota otimizada e o resumo da simulação. | Motorista | CU 3, 4, 6 |
| **TelaGerenciamentoTurnos** | Interface para o Motorista configurar e editar turnos/grupos de passageiros. | Motorista | CU 5, CU 7 |
| **ServicoNotificacaoPush** | Abstração para envio de notificações (Ex: Firebase Cloud Messaging). | Sistema / Passageiro | CU 2 |
| **ServicoGeolocalizacao** | Abstração da API de mapas (Ex: Google Maps) para cálculo de rota e geolocalização. | Sistema / Motorista | CU 4, 6 |
