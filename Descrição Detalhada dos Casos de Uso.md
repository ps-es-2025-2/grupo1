# Descrição detalhada dos Casos de Uso #
# Grupo 1
| Aluno | Github |
|-------------|-------------|
|Gabriel Freitas dos Reis | GabrielFRails
|Gabriel Rodrigues Silva | Gabriellrs
|Laura Martins Vieira Gonçalves | lauramvg1821
|Léia Santos Costa | Leia27
|Tallya Jesus Sousa Barbosa | tallya01

---

### 1. Confirmar participação na viagem

#### Nome
Confirmar participação na viagem

#### Descrição Sucinta
Permite ao passageiro selecionar um turno e confirmar sua presença na viagem, notificando o sistema e o motorista.

#### Atores
**Passageiro**: Usuário que utiliza o serviço e precisa confirmar sua participação.

#### Pré-condição
* O passageiro deve estar logado no aplicativo.
* O passageiro deve estar associado a um grupo de viagem com turnos criados pelo motorista.

#### Pós-condições
* A participação do passageiro para o turno selecionado é registrada no sistema.
* O nome do passageiro é adicionado à lista de confirmados para a respectiva viagem, que fica visível para o motorista.

#### Fluxo Básico
1.  O passageiro acessa o aplicativo.
2.  O sistema exibe os turnos disponíveis para o passageiro (ex: "Manhã", "Noite").
3.  O passageiro seleciona o turno desejado.
4.  O passageiro aciona a opção "Confirmar participação".
5.  O sistema processa a confirmação e exibe uma mensagem de sucesso.
6.  O caso de uso é concluído.

#### Fluxos Alternativos
* **A1: Cancelar confirmação:** Após selecionar um turno, o passageiro pode cancelar a ação em vez de confirmar a participação

#### Fluxos de Exceção
* **E1: Falha na conexão:** Se a conexão com a internet for perdida durante o processo, o sistema exibe uma mensagem de erro e solicita que o passageiro tente novamente quando a conexão for restabelecida.

#### Estrutura de Dados
* **Passageiro**:
    * ID do passageiro
    * Confirmação da viagem (booleano ou status)
* **Viagem/Turno**:
    * ID do turno
    * Lista de passageiros confirmados

#### Regras de Negócio
* **RN1:** O passageiro só pode confirmar a participação em turnos nos quais está cadastrado.
* **RN2:** A confirmação deve ser feita até um horário limite definido pelo motorista ou pelo sistema para garantir a organização da rota.

#### Observações
* Esta funcionalidade é central para a proposta do MVP, pois substitui a comunicação manual via WhatsApp.
* Este caso de uso está diretamente relacionado aos requisitos de alto nível **RF01 - Gestão de Turnos e Vagas** e **RF04 - Comunicação Centralizada**.

---

### 2. Receber lembrete de notificação

#### Nome
Receber lembrete de notificação

#### Descrição Sucinta
Envia notificações push programadas em horários predefinidos para o dispositivo do passageiro, lembrando-o de confirmar sua participação na viagem.

#### Atores
**Passageiro**: O destinatário da notificação.

#### Pré-condição
* O passageiro deve ter o aplicativo instalado e as permissões de notificação habilitadas no dispositivo.
* O passageiro ainda não deve ter confirmado sua participação para o próximo turno.
* Deve haver um horário predefinido para o envio do lembrete.

#### Pós-condições
* O passageiro recebe uma notificação push em seu dispositivo.

#### Fluxo Básico
1.  O sistema identifica que o horário predefinido para o envio de lembretes foi atingido.
2.  O sistema verifica a lista de passageiros de um determinado turno que ainda não confirmaram a participação.
3.  O sistema dispara uma notificação push para cada um desses passageiros.
4.  O passageiro visualiza a notificação em seu dispositivo.
5.  O caso de uso é concluído.

#### Fluxos Alternativos
* **A1: Passageiro já confirmado:** No passo 2, se o sistema identificar que o passageiro já confirmou a viagem, ele não será incluído na lista de destinatários do lembrete.

#### Fluxos de Exceção
* **E1: Falha no serviço de notificação:** Caso o serviço externo de push (ex: Firebase, APNS) falhe, a notificação não será enviada. O sistema pode registrar o erro para análise.
* **E2: Notificações desabilitadas pelo usuário:** Se o passageiro desabilitou as notificações para o aplicativo nas configurações do seu sistema operacional, ele não receberá o lembrete.

#### Estrutura de Dados
* **Passageiro**:
    * ID do dispositivo (device token) para envio da notificação
* **Turno**:
    * Horário programado para o envio do lembrete

#### Regras de Negócio
* **RN1:** A notificação só deve ser enviada aos passageiros que ainda não confirmaram a participação na viagem.

#### Observações
* Esta funcionalidade visa aumentar a taxa de confirmação com antecedência, um dos critérios de sucesso do projeto.
* Este caso de uso está diretamente ligado ao requisito de alto nível **RF03 - Sistema de Notificações**.

---

### 3. Visualizar lista de passageiros confirmados

#### Nome
Visualizar lista de passageiros confirmados

#### Descrição Sucinta
Permite ao motorista acessar e visualizar a lista de todos os passageiros que confirmaram sua participação em um turno específico.

#### Atores
**Motorista**: Usuário que gerencia a viagem e precisa saber quem irá embarcar.

#### Pré-condição
* O motorista deve estar logado no aplicativo.
* Deve existir pelo menos um turno criado no sistema.

#### Pós-condições
* A lista de passageiros confirmados para o turno selecionado é exibida na tela do motorista.

#### Fluxo Básico
1.  O motorista acessa o painel de gerenciamento no aplicativo.
2.  O motorista seleciona a opção para visualizar os passageiros de uma viagem.
3.  O sistema solicita que o motorista selecione o turno desejado.
4.  O motorista seleciona o turno.
5.  O sistema recupera e exibe a lista com os nomes dos passageiros que confirmaram a participação para aquele turno.
6.  O caso de uso é concluído.

#### Fluxos Alternativos
* **A1: Nenhum passageiro confirmado:** No passo 5, se nenhum passageiro confirmou a participação, o sistema exibe uma mensagem informativa, como "Nenhum passageiro confirmado para este turno".

#### Fluxos de Exceção
* **E1: Falha na conexão:** Em qualquer passo, se a conexão com a internet for perdida, o sistema exibe uma mensagem de erro e orienta o motorista a tentar novamente.

#### Estrutura de Dados
* **Turno**:
    * ID do turno
    * Lista de passageiros confirmados (associada)
* **Passageiro**:
    * ID do passageiro
    * Nome do passageiro

#### Regras de Negócio
* **RN1:** A lista deve exibir apenas os passageiros que realizaram a ação de "Confirmar participação na viagem" para o turno específico.

#### Observações
* Esta funcionalidade é essencial para o motorista e serve como base para outros casos de uso, como "Visualizar ordem otimizada de coleta" e "Simular corrida".
* É um requisito fundamental do MVP, pois elimina a necessidade de o motorista verificar manualmente as confirmações em grupos de WhatsApp.

### **4. Visualizar Ordem Otimizada de Coleta**

#### **Nome**
Visualizar Ordem Otimizada de Coleta

#### **Descrição Sucinta**
Permite ao **motorista** visualizar a ordem de coleta de passageiros, otimizada para minimizar o tempo e a distância de percurso.

#### **Atores**
**Motorista**: O usuário que visualiza a ordem de coleta.

#### **Pré-condição**
* O motorista deve estar logado no aplicativo.
* Pelo menos um passageiro deve ter confirmado a participação em um turno.
* O sistema deve ter acesso à localização de cada passageiro confirmado.

#### **Pós-condições**
* A ordem de coleta otimizada é exibida na tela do motorista.

#### **Fluxo Básico**
1. O motorista acessa a tela principal do aplicativo.
2. O motorista seleciona a opção para visualizar a lista de passageiros de um turno.
3. O sistema recupera a lista de passageiros confirmados para o turno.
4. O sistema calcula a melhor sequência de paradas para a coleta, usando um algoritmo de otimização de rota.
5. O sistema exibe a lista de passageiros em uma ordem numerada, representando a sequência de coleta.
6. O caso de uso é concluído.

#### **Fluxos Alternativos**
* **A1: Nenhum passageiro confirmado:** No passo 3, se não houver passageiros confirmados para o turno, o sistema informa ao motorista que a lista está vazia e, portanto, a ordem de coleta não pode ser otimizada.
* **A2: Falha na otimização da rota:** No passo 4, se o cálculo da rota falhar, o sistema exibe uma mensagem de erro e, como alternativa, pode apresentar os passageiros em ordem alfabética ou de cadastro, sem a otimização.

#### **Fluxos de Exceção**
* **E1: Falha na conexão:** Em qualquer passo, se a conexão com a internet for perdida, o sistema exibe uma mensagem de erro e informa ao motorista para verificar sua conexão.

#### **Estrutura de Dados**
* **Passageiro**:
  * ID do passageiro (chave primária)
  * Endereço de coleta (geolocalização)
  * Status de confirmação
* **Viagem**:
  * ID do turno (chave primária)
  * Lista de passageiros confirmados

#### **Regras de Negócio**
* **RN1**: O cálculo da rota deve considerar a localização de todos os passageiros confirmados para o turno selecionado.
* **RN2**: A ordem de coleta deve ser apresentada de forma clara e numerada para facilitar a navegação do motorista.

#### **Observações**
* Este caso de uso é fundamental para o principal objetivo do aplicativo, que é a otimização da rota.

---

### **5. Adicionar/Remover Passageiros**

#### **Nome**
Adicionar e Remover Passageiros

#### **Descrição Sucinta**
Permite ao **motorista** gerenciar manualmente os passageiros associados a um grupo de viagem ou turno.

#### **Atores**
**Motorista**: O usuário que adiciona ou remove passageiros.

#### **Pré-condição**
* O motorista deve estar logado no aplicativo.
* Um ou mais turnos devem ter sido criados.
* A lista de passageiros deve estar disponível no sistema.

#### **Pós-condições**
* O passageiro é adicionado a um grupo de viagem ou removido dele.

#### **Fluxo Básico**
1. O motorista acessa o painel de gerenciamento no aplicativo e seleciona "Gerenciar Passageiros".
2. O sistema exibe a lista de turnos existentes e os passageiros associados a eles.
3. O motorista pode escolher entre as seguintes ações:
   a) **Adicionar um passageiro:**
      i. O motorista seleciona um turno.
      ii. O sistema exibe a lista de passageiros que ainda não estão no turno.
      iii. O motorista seleciona o passageiro desejado e clica em "Adicionar".
      iv. O sistema adiciona o passageiro ao grupo do turno e confirma a ação.
   b) **Remover um passageiro:**
      i. O motorista seleciona um turno.
      ii. O sistema exibe a lista de passageiros que estão no turno.
      iii. O motorista seleciona o passageiro que deseja remover e clica em "Remover".
      iv. O sistema solicita uma confirmação da remoção.
      v. Após a confirmação, o sistema remove o passageiro do grupo e confirma a ação.
4. O caso de uso é concluído.

#### **Fluxos Alternativos**
* **A1: Não há passageiros para adicionar:** No passo 3a, se todos os passageiros já estiverem associados a um turno, o sistema exibe uma mensagem informando que não há passageiros disponíveis para adicionar.

#### **Fluxos de Exceção**
* **E1: Falha no banco de dados:** Se ocorrer um erro ao tentar atualizar o banco de dados, o sistema exibe uma mensagem de erro e informa ao motorista que a ação não pôde ser concluída.

#### **Estrutura de Dados**
* **Passageiro**:
  * ID do passageiro (chave primária)
  * Nome do passageiro
* **Turno**:
  * ID do turno (chave primária)
  * Lista de passageiros associados

#### **Regras de Negócio**
* **RN1**: Um passageiro pode ser associado a um ou mais turnos.
* **RN2**: A remoção de um passageiro de um turno não o exclui do sistema, apenas daquele grupo.

#### **Observações**
* Este caso de uso complementa o **Caso de Uso 7**, permitindo que o motorista tenha controle total sobre a composição de cada grupo de viagem.

### 6. Simular corrida

#### Nome
Simular Corrida

#### Descrição Sucinta
Permite ao motorista visualizar a simulação de rota com a ordem otimizada para a coleta de passageiros, e prever o tempo de percurso com base nos endereços de todos os passageiros que confirmaram a viagem.

#### Atores
**Motorista**: O usuário principal que solicita a simulação de rota.

#### Pré-condição
* O motorista deve ter a lista de passageiros para o turno desejado.
* Os passageiros devem ter confirmado a participação na viagem.
* As informações de endereço de coleta de cada passageiro devem estar disponíveis no sistema.

#### Pós-condições
* Uma rota otimizada é exibida na tela do motorista.
* O tempo de percurso e a distância total são calculados e exibidos.

#### Fluxo Básico
1. O motorista acessa o aplicativo e seleciona a opção "Simular corrida".
2. O sistema solicita ao motorista que selecione o turno para a simulação.
3. O motorista seleciona o turno desejado.
4. O sistema recupera a lista de passageiros que confirmaram a participação para aquele turno.
5. O sistema processa os endereços de coleta dos passageiros para gerar a rota mais eficiente em termos de tempo e distância.
6. O sistema exibe a rota otimizada em um mapa.
7. O sistema apresenta um resumo da simulação, incluindo a ordem de coleta dos passageiros, o tempo de percurso e a distância total da rota.
8. O caso de uso é concluído.

#### Fluxos Alternativos
* **A1: Nenhum passageiro confirmado:** No passo 4, se não houver passageiros confirmados para o turno selecionado, o sistema informa ao motorista que não é possível simular a corrida e sugere que ele verifique a lista de passageiros ou selecione outro turno.
* **A2: Falha na otimização da rota:** No passo 5, se o sistema de cálculo de rota falhar (por exemplo, devido à falta de dados de endereço), o sistema notifica o motorista sobre o problema e oferece uma rota padrão (por exemplo, ordem de cadastro dos passageiros), ou pede para tentar novamente.

#### Fluxos de Exceção
* **E1: Falha na conexão:** Em qualquer passo, se a conexão com a internet for perdida, o sistema exibe uma mensagem de erro e informa ao motorista para verificar sua conexão e tentar novamente.

#### Estrutura de Dados
* **Passageiro**:
    * ID do passageiro (chave primária)
    * Endereço de coleta
    * Confirmação da viagem
* **Viagem**:
    * ID do turno (chave primária)
    * Lista de passageiros confirmados

#### Regras de Negócio
* **RN1:** A rota deve ser calculada a partir de uma origem (endereço do motorista) até o último passageiro.
* **RN2:** A otimização da rota deve priorizar a economia de tempo e combustível.

#### Observações
* A funcionalidade depende do acesso a serviços de geolocalização e APIs de mapas para o cálculo e exibição da rota.
* Este caso de uso está diretamente relacionado ao requisito de alto nível RF02 - Otimização de Rota e ao critério de sucesso de redução de tempo e combustível.

---

### 7. Gerenciar Turnos e Vagas

#### Nome
Gerenciar Turnos e Vagas

#### Descrição Sucinta
Permite ao motorista criar, editar e organizar os turnos de viagem, definindo a capacidade de passageiros para cada um.

#### Atores
**Motorista**: O usuário principal que gerencia as configurações da viagem.

#### Pré-condição
O motorista deve estar logado no aplicativo.

#### Pós-condições
* Um novo turno é criado no sistema.
* As informações de um turno existente são atualizadas.

#### Fluxo Básico
1. O motorista acessa o painel de gerenciamento no aplicativo.
2. O motorista seleciona a opção "Gerenciar turnos e vagas".
3. O sistema exibe a lista de turnos existentes.
4. O motorista pode:
   a) **Criar um novo turno:**
      * O motorista clica em "Adicionar novo turno".
      * O sistema solicita as informações do novo turno (nome, horário, capacidade de vagas).
      * O motorista preenche as informações e salva.
      * O sistema confirma a criação do novo turno.
   b) **Editar um turno existente:**
      * O motorista seleciona um turno da lista.
      * O sistema exibe os detalhes do turno.
      * O motorista edita as informações (nome, horário, capacidade).
      * O motorista salva as alterações.
      * O sistema confirma a atualização do turno.
5. O caso de uso é concluído.

#### Fluxos Alternativos
* **A1: Excluir um turno:** No passo 4, o motorista seleciona um turno e opta por excluí-lo. O sistema solicita uma confirmação e, se confirmada, remove o turno do sistema.

#### Fluxos de Exceção
* **E1: Dados de entrada inválidos:** No passo 4a, se o motorista inserir dados inválidos (por exemplo, um valor não numérico para a capacidade), o sistema exibe uma mensagem de erro e impede o salvamento até que os dados sejam corrigidos.

#### Estrutura de Dados
* **Turno**:
    * ID do turno (chave primária)
    * Nome (ex: "Manhã", "Almoço", "Tarde", "Noite").
    * Horário
    * Capacidade de vagas
    * Lista de passageiros associados

#### Regras de Negócio
* **RN1:** A capacidade de vagas deve ser um valor numérico positivo.
* **RN2:** Não é possível excluir um turno se houver passageiros associados a ele.

#### Observações
* Este caso de uso está diretamente ligado ao requisito de alto nível RF01 - Gestão de Turnos e Vagas, pois é a ferramenta que o motorista utiliza para organizar a oferta de viagens.
