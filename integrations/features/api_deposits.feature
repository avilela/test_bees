Feature: Validacao da API Deposits

    Teste de API via Python para validar API Deposits utilizando os métodos:
        GET, POST, PATCH, PUT, DELETE

    Scenario: Validar exibir lista de Depositos
        Given eu requira a lista de Depositos via API
        Then o statusCode deve ser 200 e o body nao deve ser vazio

    Scenario: Validar criacao de Depositos
        Given eu tenha um payload valido
        When eu realizo um POST utilizando o endereco da API
        Then o statusCode deve ser 201 e o retorno tera as informacoes do payload

    Scenario: Validar exibir um Deposito via ID
        Given eu requira um Deposito via ID
        Then o statusCode deve ser 200 e o body nao deve ser vazio

    Scenario: Validar atualizar um Deposito com PATCH
        Given eu tenha um payload valido
        When eu realizo uma atualizacao no deposito via API com PATCH
        Then o statusCode deve ser 200 e o deposito deve ter sido atualizado conforme payload

    Scenario: Validar atualizar um Deposito com PUT
        Given eu tenha um payload valido
        When eu realizo uma atualizacao no deposito via API com PUT
        Then o statusCode deve ser 200 e o deposito deve ter sido atualizado conforme payload


    Scenario: Validar exclusao de deposito
        Given eu tenha um payload valido
        When eu realizo um POST utilizando o endereco da API
        Then o statusCode deve ser 201 e o retorno tera as informacoes do payload
        When eu requira a exclusao de um deposito
        Then o statusCode deve ser 204
