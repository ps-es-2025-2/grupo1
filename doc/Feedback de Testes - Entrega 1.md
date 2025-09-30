# Feedback de Testes - Entrega 1

## 1. Visão Geral

O sistema implementa parcialmente o modelo proposto para o aplicativo de gestão de vagas em van. A interface (view) utiliza apenas dados mockados, e as ações dos botões ainda não possuem lógica implementada. Portanto, a avaliação se concentra na implementação das classes de modelo (model), na estrutura da interface e na cobertura dos principais casos de uso definidos na documentação.

## 2. Aderência à Modelagem e Casos de Uso

### 2.1. Casos de Uso e Cobertura na Implementação

Os principais casos de uso previstos na documentação são:

- **CU1: Passageiro confirma participação em um turno**
	- Parcialmente implementado. O modelo `Passageiro` possui atributo de confirmação, mas não há fluxo de confirmação real nem registro de confirmação vinculado ao turno.
- **CU2: Envio de notificação de lembrete ao passageiro**
	- Não implementado. Não há lógica de notificação nem entidade ou serviço correspondente.
- **CU3: Motorista visualiza lista de passageiros confirmados**
	- Parcialmente implementado. A tela exibe listas mockadas de passageiros confirmados, mas não há integração com o modelo real nem atualização dinâmica.
- **CU4: Motorista visualiza ordem otimizada de coleta**
	- Parcialmente implementado. O botão existe na interface, mas não há lógica de otimização nem integração com dados reais.
- **CU5: Motorista gerencia grupos de passageiros/turnos**
	- Parcialmente implementado. O botão existe, mas não há tela ou lógica de gerenciamento.
- **CU6: Motorista simula corrida**
	- Parcialmente implementado. O modelo `SimulacaoCorrida` permite simulação via código, mas não há integração com a interface.
- **CU7: Motorista edita ou exclui turnos**
	- Não implementado.

### 2.2. Classes de Entidade

- **Motorista**: Implementada com atributos `id`, `nome`, `enderecoOrigem` e lista de `Turno`. Está de acordo com a modelagem, exceto pelo nome do atributo de endereço (`enderecoOrigem` em vez de `endereco_base`), o que não compromete a funcionalidade.
- **Passageiro**: Implementada com atributos `id`, `nome`, `enderecoColeta` e status de confirmação. Está de acordo com a modelagem, de forma parcial pois não possui atributos como `telefone` ou `device_token`. Há previsão de adicionar esses atributos nas entregas subsequentes.
- **Turno**: Implementada com atributos `id`, `nome`, `horario`, `capacidadeVagas`, `horarioLembrete`, além de listas de passageiros associados e confirmados. Está aderente à modelagem.
- **SimulacaoCorrida**: Implementada para simular uma corrida, com referência ao turno, distância, tempo estimado e ordem otimizada de coleta. Não está prevista explicitamente na modelagem, mas é útil para testes e simulações.
- **Confirmacao** e **Notificacao**: Não foram implementadas como classes separadas, apenas representadas indiretamente por atributos e listas nas outras classes. Isso pode impactar a implementação dos casos de uso de confirmação e notificação.

### 2.3. Classes de Controle e Fronteira

- Não há implementação das classes de controle (ex: `ControladorConfirmacao`, `GerenciadorTurnos`) nem das classes de fronteira (ex: telas específicas para confirmação, gerenciamento de turnos, etc). A interface implementada é apenas a tela do motorista (`DriverDashboard.fxml`), que exibe listas de passageiros mockados. Para cobertura total dos casos de uso, será necessária a implementação dessas classes.

## 3. Funcionalidades Implementadas e Relação com Casos de Uso

- **Testes automatizados JUnit**: O projeto conta com testes unitários automatizados (`GerenciadorVansTest.java`) utilizando JUnit 5, que validam a lógica de criação, atualização, deleção de passageiros e turnos, além de regras de negócio do backend. Esses testes garantem que parte dos casos de uso (como manipulação de passageiros e turnos) está corretamente implementada na camada de serviço, mesmo sem integração com a interface.
- **Visualização de passageiros confirmados (CU3)**: A tela exibe listas de passageiros para turnos manhã e noite, porém os dados são mockados e não refletem o estado real do modelo.
- **Ações do motorista (CU4, CU5, CU6, CU7)**: Os botões para otimizar rota, simular corrida, gerenciar passageiros e turnos estão presentes, mas não possuem lógica implementada.
- **Simulação de modelos (relacionado a CU6)**: O arquivo `TesteModels.java` permite simular a criação de passageiros, turnos, motorista e simulação de corrida, mas não integra com a interface.

---


## 4. O que está de acordo com a modelagem e casos de uso

- Estrutura básica das entidades principais (Motorista, Passageiro, Turno) está implementada e aderente à documentação.
- A interface inicial do motorista está presente, com as principais ações previstas no MVP.
- Alguns casos de uso estão parcialmente representados na interface (ex: visualização de listas), mas sem lógica real.
- A lógica de backend para manipulação de passageiros e turnos está sendo validada por testes unitários, aumentando a confiabilidade do sistema mesmo sem integração com a interface.

---


## 5. O que está parcial ou não implementado (modelagem e casos de uso)

- **Dados mockados**: Não há integração entre a interface e os modelos reais. Exibe apenas dados estáticos.
- **Ações dos botões**: Contém apenas mensagens de log, sem lógica implementada.
- **Classes de controle e fronteira**: Não implementadas, o que impede a realização dos fluxos completos dos casos de uso.
- **Entidades Confirmacao e Notificacao**: Não implementadas como classes, apesar de serem previstas no diagrama de classes de análise e necessárias para os casos de uso de confirmação e notificação.
- **Regras de negócio**: Não implementadas (ex: limite de vagas, confirmação, notificações, otimização real de rota), o que inviabiliza a execução dos casos de uso principais.
- **Persistência de dados**: Não implementada.

## 6. Resumo

A entrega contempla a estrutura inicial do modelo e da interface e está suficientemente aderente à modelagem. Para as próximas entregas, deverá ser priorizada a integração entre interface e modelo, bem como a implementação das ações dos botões presentes na interface, a fim de atender aos casos de uso propostos e entregar funcionalidades ao cliente.
