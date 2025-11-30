## 1. Detalhamento das Etapas Chave

Este diagrama BPMN modela o fluxo de trabalho do negócio, mostrando a automação do ciclo de confirmação e a otimização da rota, garantindo que o aplicativo resolva os problemas de comunicação e eficiência.

| Etapa | Participante | Ação e Padrão | Objetivo de Negócio |
| :--- | :--- | :--- | :--- |
| **1** | Motorista | **Gerenciar Turnos (CU7)** | Define a estrutura da oferta (horários, capacidade) que o sistema irá automatizar. |
| **3** | App Vagas Van | **Enviar Lembrete (CU2)** | Dispara o evento de confirmação para o Passageiro, reduzindo o trabalho manual do Motorista. |
| **4** | Passageiro | **Confirmar Participação (CU1)** | Ação central do MVP: substitui a comunicação por WhatsApp, alimentando o sistema com dados. |
| **5, 6** | App Vagas Van | **Registrar e Verificar Limite (RN2)** | Aplica regras de negócio essenciais (RNs) para manter a integridade da vaga e o prazo para a rota. |
| **10** | App Vagas Van | **Calcular Rota Otimizada (CU4)** | A principal proposta de valor: usa as confirmações para resolver o problema da ineficiência da rota. |
| **13** | Motorista | **Executar Coleta** | O Motorista segue a ordem final e otimizada fornecida pelo sistema (CU4). |
