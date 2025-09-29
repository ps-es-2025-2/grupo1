# Documento de Visão – Aplicativo de Gestão de Vagas em Van

## 1. Introdução
1.1. Contexto do Problema
Atualmente, a organização de viagens de van é realizada de forma manual, com o motorista e os passageiros se comunicando por meio de grupos de WhatsApp. Esse processo consome muito tempo do motorista e dos passageiros, resultando em diversos problemas, como a perda de tempo e o alto consumo de combustível por não buscar os passageiros na ordem mais eficiente. Além disso, a falta de uma ferramenta centralizada pode levar a erros de comunicação, como o motorista enviar informações no grupo errado ou o passageiro avisar sua participação em um grupo de turno incorreto, causando atrasos e desconforto para todos.
1.2. Motivação e Objetivos
O projeto visa desenvolver um aplicativo móvel para automatizar e otimizar o processo de organização das corridas de van. O objetivo principal é oferecer uma plataforma centralizada onde motoristas possam organizar suas rotas de forma eficiente e passageiros possam confirmar sua participação de forma simples, reduzindo a carga de trabalho manual e minimizando erros de comunicação.

## 2. Descrição Geral
2.1. Perspectiva do Produto
O "App Vagas Van" será um aplicativo móvel (para Android e iOS) que conectará o motorista de van aos seus passageiros. Para os passageiros, funcionará como uma ferramenta de comunicação para confirmar sua participação nas viagens e receber notificações. Para o motorista, servirá como um painel de gerenciamento para visualizar os passageiros confirmados para cada turno, otimizar a rota de coleta e gerenciar as listas de passageiros.
2.2. Usuários Principais (Atores)
Motorista: Responsável por gerenciar as viagens, visualizar a lista de passageiros, otimizar a rota de coleta, e adicionar/remover passageiros dos grupos de viagem.
Passageiro: Pessoa que utiliza o serviço de van. Usa o aplicativo para confirmar sua participação em um determinado turno e receber lembretes de notificação.
2.3. Funcionalidades de Alto Nível
O sistema permitirá que os usuários realizem as seguintes ações:
Para o passageiro:
Avisar o turno e confirmar a participação na viagem.
Receber notificação programada (lembrete) em horário especificado para confirmar a viagem.
Para o motorista:
Visualizar uma lista com os passageiros que confirmaram a viagem em um determinado turno.
Visualizar a melhor ordem possível para a coleta dos passageiros, otimizando tempo e distância.
Adicionar e remover passageiros de grupos específicos.
Simular corridas.

## 3. Requisitos de Alto Nível (Features)
RF01 - Gestão de Turnos e Vagas: O motorista deve ser capaz de criar e gerenciar grupos de passageiros por turno, permitindo que os passageiros confirmem sua participação para cada viagem.
RF02 - Otimização de Rota: O sistema deve fornecer ao motorista a melhor ordem de coleta dos passageiros confirmados, com o objetivo de minimizar tempo e distância percorrida.
RF03 - Sistema de Notificações: O aplicativo deve enviar notificações push aos passageiros, em horários predefinidos, para lembrá-los de confirmar sua participação na viagem.
RF04 - Comunicação Centralizada: O aplicativo deve eliminar a necessidade de comunicação via múltiplos grupos de WhatsApp, centralizando as confirmações e informações em uma única plataforma.
RF05 - Gestão de Passageiros: O motorista deve ser capaz de adicionar ou remover passageiros dos grupos de viagem e simular corridas para prever a rota e o tempo de percurso.

## 4. Prioridades para o MVP (Minimum Viable Product)
Com base nos problemas e funcionalidades essenciais, a primeira versão do aplicativo (MVP) deve focar em:
1. Funcionalidade do passageiro para avisar o turno e a participação.
2.. Sistema de notificação de lembrete para o passageiro.
3. Funcionalidade do motorista para visualizar a lista de passageiros que confirmaram.
4. Funcionalidade do motorista para visualizar a ordem de coleta otimizada.
5. Funcionalidede do motorista de acreecentar e retirar passageiros.
6. Funcionalidade do motorista de simular trajetos a partir da ordem dos alunos.
Observações: Funcionalidades como pagamento dentro do aplicativo serão consideradas para versões futuras.

## 5. Restrições e Premissas
5.1. Restrições
O sistema deve ser um aplicativo móvel compatível com as plataformas Android e iOS.
A primeira versão não incluirá funcionalidade de pagamento dentro do aplicativo.
5.2. Premissas
Assume-se que os motoristas e passageiros possuem smartphones e estão dispostos a usar um aplicativo para organizar as viagens.
Assume-se que o motorista terá acesso a serviços de localização (GPS) para a funcionalidade de otimização de rota.
A premissa de que a otimização da rota deve ser a mais eficiente em termos de tempo e distância será baseada nos endereços de coleta fornecidos pelos passageiros.

## 6. Critérios de Sucesso
O sucesso do projeto será medido pelos seguintes critérios:
Motorista:
Redução de pelo menos 20% no tempo e no gasto de combustível na rota de coleta, devido à otimização.
Eliminação de 100% dos casos de passageiros esquecidos por não visualização da mensagem de confirmação.
Passageiro:
Redução de 80% das confusões de comunicação ao usar o aplicativo em vez de grupos de WhatsApp.
Aumento de 50% na taxa de confirmação de presença com antecedência, devido ao sistema de lembretes.
Negócio:
Aumento da satisfação e retenção de passageiros ao oferecer uma ferramenta mais confiável e organizada.
