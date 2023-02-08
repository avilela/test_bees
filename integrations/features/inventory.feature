Feature: Tela de Inventario
    Como um usuario autenticado do site
    Eu quero poder visualizar a lista de inventarios, um inventario individual e 
        acrescentar, editar ou excluir um inventario
    Para controlar o estoque utilizando a aplicacao

    Scenario: Vizualizar lista de inventario
        Given eu estou logado 
        When estou na pagina de inventario
        Then devo visualizar a lista de inventario

    Scenario: Vizualizar um inventario individual
        When estou na pagina de inventario
        And clico no botao: 'Show this inventory'
        Then sou redirecionado para uma tela que exibe as informacoes do inventario

    Scenario: Criar novo inventario
        When eu tenha um item e um deposito cadastrado
        And estou na pagina de inventario
        And clico no botao: 'New inventory'
        And eu preencho o formulario com as informacoes necessarias
        And clico no botao: 'Create inventory'
        Then sou redirecionado para uma tela que exibe as informacoes do inventario
        And exibe uma mensagem: 'Sucesso ao criar inventario'

    Scenario: Editar um inventario existente
        When estou na pagina de inventario
        And clico no botao: 'Show this inventory'
        And clico no botao: 'Edit this inventory'
        And edito os campos do formulario de inventario
        And clico no botao: 'Update inventory'
        Then sou redirecionado para uma tela que exibe as informacoes do inventario
        And exibe uma mensagem: 'Sucesso ao editar inventario'

    Scenario: Deletar inventorio existente
        When estou na pagina de inventario
        And clico no botao: 'Show this inventory'
        And clico no botao: 'Destroy this inventory'
        Then devo visualizar a lista de inventario
        And exibe uma mensagem: 'Sucesso ao deletar inventario'