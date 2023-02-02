#language: en

@TesteWeb @TesteCompleto
Feature: Testes do sistema web de controle de estoque


  @CT001Web
  Scenario: Criacao de usuario com dados corretos
    Given que esteja na tela de cadastro
    When Criar um usuario com dados validos
    Then espero que o usuario seja criado com sucesso


  @CT002Web
  Scenario: Criacao de usuario sem email
    Given que esteja na tela de cadastro
    When Criar um usuario sem informar email
    Then espero que o usuario nao seja criado


  @CT003Web
  Scenario: Criacao de usuario sem senha
    Given que esteja na tela de cadastro
    When Criar um usuario sem informar senha e confirmacao de senha
    Then espero que o usuario nao seja criado

  @CT004Web
  Scenario: Criacao de usuario sem formatacao de email
    Given que esteja na tela de cadastro
    When Criar um usuario sem formatacao de email
    Then espero que o usuario nao seja criado


  @CT005Web
  Scenario: Criacao de usuario com senha menor de 6 caracteres
    Given que esteja na tela de cadastro
    When Criar um usuario com senha menor que seis caracteres
    Then espero que o usuario nao seja criado


  @CT007Web
  Scenario: Login sem informar email
    Given que esteja na tela de login
    When tentar efetuar login sem email
    Then espero que nao seja possivel entrar no sistema


  @CT008Web
  Scenario: Login sem informar senha
    Given que esteja na tela de login
    When tentar efetuar login sem senha
    Then espero que nao seja possivel entrar no sistema


  @CT009Web
  Scenario: Login com email sem formatacao
    Given que esteja na tela de login
    When tentar efetuar login com email sem formatacao
    Then espero que nao seja possivel entrar no sistema


  @CT010Web
  Scenario: Login com senha invalida
    Given que esteja na tela de login
    When tentar efetuar login com senha invalida
    Then espero que nao seja possivel entrar no sistema


  @CT011Web
  Scenario: Criacao de novo deposito com todos os campos preenchidos
    Given que esteja logado no sistema
    When criar um novo deposito com todos os campos
    Then espero que seja criado com sucesso

  @CT012Web
  Scenario: Criacao de novo deposito com nenhum dos campos preenchidos
    Given que esteja logado no sistema
    When criar um novo deposito sem nenhum campo
    Then espero que nao crie o deposito

  @CT013Web
  Scenario: Edicao de deposito ja criado com todos os campos
    Given que esteja logado no sistema
    When editar um deposito com todos os campos
    Then espero que atualize o deposito com sucesso


  @CT014Web
  Scenario: Edicao de deposito ja criado com retirada de todos os campos
    Given que esteja logado no sistema
    When editar um deposito com todos os campos retirados
    Then espero que nao atualize o deposito

  @CT015Web
  Scenario: Exclusao de deposito
    Given que esteja logado no sistema
    When entrar no deposito e excluir ele
    Then espero que seja excluido com sucesso


  @CT016Web
  Scenario: Criacao de novo item com todos os campos preenchidos
    Given que esteja logado no sistema
    When criar um novo item com todos os campos
    Then espero que seja criado com sucesso

  @CT017Web
  Scenario: Criacao de novo item com nenhum dos campos preenchidos
    Given que esteja logado no sistema
    When criar um novo item sem nenhum campo
    Then espero que nao crie o item

  @CT018Web
  Scenario: Edicao de item ja criado com todos os campos
    Given que esteja logado no sistema
    When editar um item com todos os campos
    Then espero que atualize o item com sucesso


  @CT019Web
  Scenario: Edicao de item ja criado com retirada de todos os campos
    Given que esteja logado no sistema
    When editar um item com todos os campos retirados
    Then espero que nao atualize o item

  @CT020Web
  Scenario: Exclusao de item
    Given que esteja logado no sistema
    When entrar no item e excluir ele
    Then espero que seja excluido com sucesso

  @CT021Web
  Scenario: Criacao de novo inventario com todos os campos preenchidos
    Given que esteja logado no sistema
    And Que tenha um novo item e deposito para assimilar
    When criar um novo inventario com todos os campos
    Then espero que seja criado com sucesso

  @CT022Web
  Scenario: Criacao de novo inventario com nenhum dos campos preenchidos
    Given que esteja logado no sistema
    When criar um novo inventario sem nenhum campo
    Then espero que nao crie o inventario

  @CT023Web
  Scenario: Edicao de inventario ja criado com todos os campos
    Given que esteja logado no sistema
    And Que tenha um novo item e deposito para assimilar
    When editar um inventario com todos os campos
    Then espero que atualize o inventario com sucesso


  @CT024Web
  Scenario: Edicao de inventario ja criado com retirada de todos os campos
    Given que esteja logado no sistema
    When editar um inventario com todos os campos retirados
    Then espero que nao atualize o inventario

  @CT025Web
  Scenario: Exclusao de inventario
    Given que esteja logado no sistema
    When entrar no inventario e excluir ele
    Then espero que seja excluido com sucesso