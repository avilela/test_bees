Feature: Validacao da API Inventories

    Teste de API via Python para validar API Inventories utilizando os métodos:
        GET, POST, PATCH, PUT, DELETE

    Scenario: Validar exibir lista de Inventories
        Given eu requira a lista de Inventories via API
        Then o statusCode deve ser 200 e o body da API Inventories nao deve ser vazio

    Scenario: Validar criacao de Inventories
        Given eu tenha um payload de Inventory valido
        When eu realizo um POST utilizando o endereco da API de Inventories
        Then o statusCode deve ser 201 e o retorno da API Inventories tera as informacoes do payload

    Scenario: Validar exibir um Inventory via ID
        Given eu tenha um payload de Inventory valido
        When eu realizo um POST utilizando o endereco da API de Inventories
        And eu requira um Inventory via ID
        Then o statusCode deve ser 200 e o body da API Inventories nao deve ser vazio

    Scenario: Validar atualizar um Inventory com PATCH
        Given eu tenha um payload de Inventory valido
        When eu realizo um POST utilizando o endereco da API de Inventories
        And eu realizo uma atualizacao no Inventory via API com PATCH
        Then o statusCode deve ser 200 e o Inventory deve ter sido atualizado conforme payload

    Scenario: Validar atualizar um Inventory com PUT
        Given eu tenha um payload de Inventory valido
        When eu realizo um POST utilizando o endereco da API de Inventories
        And eu realizo uma atualizacao no Inventory via API com PUT
        Then o statusCode deve ser 200 e o Inventory deve ter sido atualizado conforme payload

    Scenario: Validar exclusao de Inventory
        Given eu tenha um payload de Inventory valido
        When eu realizo um POST utilizando o endereco da API de Inventories
        Then o statusCode deve ser 201 e o retorno da API Inventories tera as informacoes do payload
        When eu requira a exclusao de um Inventory
        Then o statusCode da API Inventories deve ser 204
