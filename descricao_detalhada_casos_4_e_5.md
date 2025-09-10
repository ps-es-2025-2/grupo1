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