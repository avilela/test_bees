Feature: Tela de Depositos
    Como um usuario autenticado do site
    Eu quero poder visualizar a lista de depositos, um deposito individual e 
        acrescentar, editar ou excluir um deposito
    Para controlar o estoque utilizando a aplicacao

    Scenario: Vizualizar lista de depositos
        Given estou logado com um usuario
        When estou na pagina de depositos
        Then devo visualizar a lista de depositos

    Scenario: Vizualizar um deposito individual
        When estou na pagina de depositos
        When eu clico no botao: 'Show this deposit'
        Then sou redirecionado para uma tela que exibe as informacoes do deposito

    Scenario: Criar novo deposito
        When estou na pagina de depositos
        When eu clico no botao: 'New Deposit'
        And preencho o formulario com as informacoes necessarias do deposito
        And eu clico no botao: 'Create Deposit'
        Then sou redirecionado para uma tela que exibe as informacoes do deposito
        And e exibida uma mensagem: 'Sucesso ao criar deposito'

    Scenario: Editar um deposito existente
        When estou na pagina de depositos
        When eu clico no botao: 'Show this deposit'
        And eu clico no botao: 'Edit this deposit'
        And edito os campos do formulario do deposito
        And eu clico no botao: 'Update Deposit'
        Then sou redirecionado para uma tela que exibe as informacoes do deposito
        And e exibida uma mensagem: 'Sucesso ao editar deposito'

    Scenario: Deletar deposito existente
        When estou na pagina de depositos
        When eu clico no botao: 'Show this deposit'
        And eu clico no botao: 'Destroy this deposit' 
        Then devo visualizar a lista de depositos
        And e exibida uma mensagem: 'Sucesso ao deletar Deposito'
        # And realizo logout do site