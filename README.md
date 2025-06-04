Desafio Android - Data Platform
===============================

## Considerações Gerais

Sua solução deve ser simples de ser executada, seguindo as condições abaixo:

* Seu aplicativo deve ser possível executar com o emulador do Android Studio.
* Deverá ser escrito na linguagem Kotlin.
* Registre no arquivo SOLUTION.md a arquitetura do projeto e as ideias que gostaria de implementar se tivesse mais tempo e explique como você as faria.

## O Problema

No time de DataPlatform nós somos responsáveis pela coleta de dados de todos os produtos digitais da Globo, isso inclui (globoplay, gshow, ge, bbb, etc.). O seu desafio consiste em criar um aplicativo que coletará dados de uso e irá enviar eventos a uma API que esterá disponível neste repositório.

>Exemplo: Vocë pode implementar um botão que ao ser clicado gere um evento e depois envie. Ou abra uma tela e a abertura da tela envie o evento. Como o evento vai ser disparado cabe a você decidir.

Voce deve pensar em como suas requests vão ser enviados.

>Exemplo: se eu gerar 500 eventos, enviarei 500 requests? E se meu dispositivo ficar sem internet? Vou perder os eventos?

Você é livre para usar qualquer biblioteca que ajude na resolução do problema.

**Obs**: Não ha necessidade em perder tempo com a UI, o app deve apenas ser capaz de enviar eventos por meio de botões ou navegação. O mais importante aqui é como será implementado o mecanismo de request.

## API

Para enviar os eventos, será disponibilizado neste repositório uma API de teste que apenas expoe um endpoint e printa a request enviada. Não há necessidade de se preocupar com ela, ela serve apenas como mock para integração.

Para realizar o download e requisitos necessários para a API, basta executar

```bash
cd api
make setup
```

E para executar a API:

```bash
make run
```

## Avaliação

1. Você deverá entregar seu código e uma documentação **SOLUTION.md**.

2. Seu app deverá ser executado em um emulador do Android Studio.

3. Seu código e documentação serão observados por uma equipe de desenvolvedores que avaliará a simplicidade e clareza da solução, arquitetura, estilo de código, testes unitários, nível de automação dos testes, implementação do código, e a organização geral do projeto.

### Dicas

- Use ferramentas e bibliotecas open-source (desde que não façam todo o trabalho para você);
- Documente as decisões e porquês;
- Automatize o máximo possível;
- Em caso de dúvidas, pergunte.