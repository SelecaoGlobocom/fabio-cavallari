Desafio Android - Solution - DataTrackApp
===============================

O **DataTrackApp** é uma aplicação Android que simula a integração com uma API de analytics.  
O foco do projeto não é a interface do usuário (UI), mas sim a exploração de uma arquitetura sólida, baseada nos princípios da **Clean Architecture**, envolvendo também camadas de **network** e **persistência local**.

## 🎯 Objetivo

A proposta inicial consiste em uma tela com uma listagem de botões, onde cada botão direciona o usuário para uma nova tela. Isso permite simular o envio de dois tipos de métricas: `PAGE_VIEW` e `EVENT`.

Cada métrica enviada carrega informações básicas e um campo customizável para dados mais específicos (como dados do usuário). A comunicação com a API é feita por meio de modelos chamados `Hit`.

## 🛠️ Etapas do Desenvolvimento

Pensando em como esse app poderia se comportar em um cenário mais robusto, o desenvolvimento foi estruturado em etapas:

1. **Integração com a API e tratamento de erros**  
   Foi criada uma base sólida para a comunicação com a API, com tratamento adequado de falhas.

2. **Persistência local em caso de falta de conexão**  
   Implementação de armazenamento local dos `hits` para lidar com situações offline.

3. **Redução de chamadas excessivas à API**  
   Os `hits` são armazenados localmente e enviados em lote ao atingir um número definido, evitando sobrecarga na API.

4. **Automatização com WorkManager**  
   O `WorkManager` foi adicionado para enviar os dados periodicamente, mesmo que o app esteja em segundo plano, reduzindo o delay na coleta das métricas.

## ⚙️ Configurações de Build

Foram criadas duas configurações no `build.gradle` para controlar o comportamento de envio dos dados:

- `HIT_BATCH_THRESHOLD`: define o número mínimo de hits para envio em lote.
- `WORK_MANAGER_ENABLED`: habilita ou desabilita o uso do `WorkManager`.

> ⚠️ Em um cenário real, essas configurações deveriam ser controladas via **config manager remoto** (ex: Firebase Remote Config), evitando a necessidade de gerar um novo build para alterações.

## 🧰 Tecnologias e Conceitos Utilizados

- **Clean Architecture**
- **MVVM**
- **Jetpack Compose**
- **Navigation**
- **Koin** (injeção de dependência)
- **Retrofit** (comunicação com API)
- **MockK** (testes)
- **Room Database** (persistência local)
- **WorkManager** (tarefas em background)

## 🧪 Testes

- Testes **unitários** adicionados principalmente na camada de `data`.
- Teste **instrumental** simples para validar o uso do banco de dados com Room.

## 📌 Observações Finais

Este projeto tem como principal objetivo demonstrar a comunicação entre diferentes camadas dentro de uma arquitetura escalável.

Algumas decisões foram mantidas por fins didáticos:

- A existência de três modelos `Hit` com propriedades semelhantes visa ilustrar como diferentes camadas do app podem ser independentes.
- Os diversos `logs` espalhados pelo código, que normalmente seriam removidos em produção, foram mantidos para facilitar o entendimento durante o desenvolvimento.
- O projeto foi desenvolvido na branch `feature/data-track-app` e so mergeado na `main` na conclusão dele.

