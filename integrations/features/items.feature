Feature: Tela de Item
    Como um usuario autenticado do site
    Eu quero poder visualizar a lista de Items, um Item individual e
    acrescentar, editar ou excluir um Item
    Para controlar o estoque utilizando a aplicacao

    Scenario: Vizualizar lista de Item
        Given eu estou logado com um usuario
        When estou na pagina de Item
        Then devo visualizar a lista de Items


    Scenario: Vizualizar um Item individual
        When estou na pagina de Item
        And eu clico no botao 'Show this item'
        Then sou redirecionado para uma tela que exibe as informacoes do Item

    Scenario: Criar novo Item
        When estou na pagina de Item
        And eu clico no botao 'New Item'
        And preencho o formulario com as informacoes necessarias
        And eu clico no botao 'Create Item'
        Then sou redirecionado para uma tela que exibe as informacoes do Item
        And exibe a mensagem indicando 'Sucesso ao criar Item'

    Scenario: Editar um Item existente
        When estou na pagina de Item
        And eu clico no botao 'Show this item'
        And eu clico no botao 'Edit this Item'
        And edito os campos do formulario
        And eu clico no botao 'Update Item'
        Then sou redirecionado para uma tela que exibe as informacoes do Item
        And exibe a mensagem indicando 'Sucesso ao editar Item'

    Scenario: Deletar inventorio existente
        When estou na pagina de Item
        And eu clico no botao 'Show this item'
        And eu clico no botao 'Destroy this Item'
        Then devo visualizar a lista de Items
        And exibe a mensagem indicando 'Sucesso ao deletar Item'
