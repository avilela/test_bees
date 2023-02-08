Feature: Validacao da API Items

    Teste de API via Python para validar API Items utilizando os métodos:
        GET, POST, PATCH, PUT, DELETE

    Scenario: Validar exibir lista de Items
        Given eu requira a lista de Items via API
        Then o statusCode deve ser 200 e o body da API Items nao deve ser vazio

    Scenario: Validar criacao de Items
        Given eu tenha um payload de Item valido
        When eu realizo um POST utilizando o endereco da API de Items
        Then o statusCode deve ser 201 e o retorno da API Items tera as informacoes do payload

    Scenario: Validar exibir um Item via ID
        Given eu requira um Item via ID
        Then o statusCode deve ser 200 e o body da API Items nao deve ser vazio

    Scenario: Validar atualizar um Item com PATCH
        Given eu tenha um payload de Item valido
        When eu realizo uma atualizacao no Item via API com PATCH
        Then o statusCode deve ser 200 e o Item deve ter sido atualizado conforme payload

    Scenario: Validar atualizar um Item com PUT
        Given eu tenha um payload de Item valido
        When eu realizo uma atualizacao no Item via API com PUT
        Then o statusCode deve ser 200 e o Item deve ter sido atualizado conforme payload

    Scenario: Validar exclusao de Item
        Given eu tenha um payload de Item valido
        When eu realizo um POST utilizando o endereco da API de Items
        Then o statusCode deve ser 201 e o retorno da API Items tera as informacoes do payload
        When eu requira a exclusao de um Item
        Then o statusCode da API Items deve ser 204