#language: en
@TesteApis @TesteCompleto
Feature: Testes das APIs /Deposits | /Items | /Inventories

  @CT001
  Scenario: Criacao de um deposito com todos os campos preenchidos - HTTP 200 esperado
    Given que seja definido o payload "postDepositsCompleto", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "endpointDeposits"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "address" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "city" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "zipcode" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"


  @CT018
  Scenario: Criacao de um inventario com todos os campos preenchidos - HTTP 200 esperado
    Given que seja definido o payload "postInventoriesCompleto", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "endpointInventories"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "deposit_id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_count" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"


  @CT038
  Scenario: Criacao de um item com todos os campos preenchidos - HTTP 200 esperado
    Given que seja definido o payload "postItemsCompleto", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "endpointItems"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "height" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "width" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "weight" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"


  @CT007
  Scenario: Consulta de depositos criados  - HTTP 200 esperado
    Given que eu tenha um registro "deposito" criado
    Given que seja definido o endpoint como "endpointDeposits"
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "200"
    And espero que o objeto do body "id[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "address[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "city[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "zipcode[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url[0]" venha com a estrutura do regex "(^.*$)"

  @CT008
  Scenario: Consulta de depositos especifico  - HTTP 200 esperado
    Given que eu tenha um registro "deposito" criado
    Given que seja definido o endpoint como "endpointDeposits" com parametro de id valido
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "address" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "city" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "zipcode" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"

  @CT025
  Scenario: Consulta de inventarios totais  - HTTP 200 esperado
    Given que eu tenha um registro "inventario" criado
    Given que seja definido o endpoint como "endpointInventories"
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_id[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "deposit_id[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_count[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "created_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url[0]" venha com a estrutura do regex "(^.*$)"

  @CT026
  Scenario: Consulta de inventario especifico  - HTTP 200 esperado
    Given que eu tenha um registro "inventario" criado
    Given que seja definido o endpoint como "endpointInventories" com parametro de id valido
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "deposit_id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "item_count" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"


  @CT043
  Scenario: Consulta de items totais  - HTTP 200 esperado
    Given que eu tenha um registro "items" criado
    Given que seja definido o endpoint como "endpointItems"
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id[0]" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "height[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "width[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "weight[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at[0]" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url[0]" venha com a estrutura do regex "(^.*$)"

  @CT044
  Scenario: Consulta de items especifico  - HTTP 200 esperado
    Given que eu tenha um registro "items" criado
    Given que seja definido o endpoint como "endpointItems" com parametro de id valido
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "200"
    And espero que o objeto do body "id" venha com a estrutura do regex "(^\d*$)"
    And espero que o objeto do body "name" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "height" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "width" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "weight" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "created_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "updated_at" venha com a estrutura do regex "(^.*$)"
    And espero que o objeto do body "url" venha com a estrutura do regex "(^.*$)"

  @consultaComIdInvalido
  Scenario Outline: "<CT>" Consulta de registro especifico que nao esteja criado - HTTP 404 esperado
    Given que seja definido o endpoint como "<endpoint>" com parametro de id invalido
    When executar uma requisicao GET pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "404"
    And que venha um erro "Not Found" no response body
    Examples:
      | CT    | endpoint            |
      | CT009 | endpointDeposits    |
      | CT027 | endpointInventories |
      | CT045 | endpointItems       |


  @AlteracaoPatchCompleta
  Scenario Outline: "<CT>" alteracao PATCH com todos os campos  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PATCH pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "200"
    And espero que o objeto do body "<objeto>" venha com a estrutura do regex "<regex>"
    Examples:
      | CT    | registro   | payload                 | endpoint            | objeto | regex   |
      | CT010 | depositos  | postDepositsCompleto    | endpointDeposits    | name   | (^.*$)  |
      | CT046 | items      | postItemsCompleto       | endpointItems       | name   | (^.*$)  |
      | CT028 | inventario | postInventoriesCompleto | endpointInventories | id     | (^\d*$) |

  @AlteracaoPatchParcial
  Scenario Outline: "<CT>" alteracao PATCH com todos apenas um campo  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PATCH pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "200"
    And espero que o objeto do body "<objeto>" venha com a estrutura do regex "<regex>"
    Examples:
      | CT    | registro  | payload             | endpoint         | objeto  | regex  |
      | CT011 | depositos | postDepositsParcial | endpointDeposits | zipcode | (^.*$) |
      #Documentação exige inventario com todos os campos obrigatorios
      #|  | items     | postItemsParcial    | endpointItems    | weight  | (^.*$) |
    #Documentação exige inventario com todos os campos obrigatorios
      #|  | inventario | postInventoriesParcial | endpointInventories | id     | (^\d*$) |

  @AlteracaoPatchVazia
  Scenario Outline: "<CT>" alteracao PATCH com nenhum campo  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PATCH pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "400"
    And que venha um erro "erro" no response body
    Examples:
      | CT    | registro  | payload               | endpoint         | objeto | regex  |
      | CT012 | depositos | postDepositsSemCampos | endpointDeposits | name   | (^.*$) |


  @AlteracaoPUTCompleta
  Scenario Outline: "<CT>" alteracao PUT com todos os campos  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PUT pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "200"
    And espero que o objeto do body "<objeto>" venha com a estrutura do regex "<regex>"
    Examples:
      | CT    | registro   | payload                 | endpoint            | objeto | regex   |
      | CT013 | depositos  | postDepositsCompleto    | endpointDeposits    | name   | (^.*$)  |
      | CT051 | items      | postItemsCompleto       | endpointItems       | name   | (^.*$)  |
      | CT032 | inventario | postInventoriesCompleto | endpointInventories | id     | (^\d*$) |


  @AlteracaoPUTcomFaltaDeCamposObrigatorios
  Scenario Outline: "<CT>" alteracao PUT com a falta de campos obrigatorios  - HTTP 400 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum" e retirando o campo "<campoRetirado>"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PUT pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "400"
    Examples:
      | CT    | registro   | payload                 | endpoint            | campoRetirado |
      | CT052 | items      | postItemsCompleto       | endpointItems       | name          |
      | CT053 | items      | postItemsCompleto       | endpointItems       | height        |
      | CT053 | items      | postItemsCompleto       | endpointItems       | weight        |
      | CT053 | items      | postItemsCompleto       | endpointItems       | width         |
      | CT033 | inventario | postInventoriesCompleto | endpointInventories | item_id       |
      | CT034 | inventario | postInventoriesCompleto | endpointInventories | deposit_id    |
      | CT035 | inventario | postInventoriesCompleto | endpointInventories | item_count    |


  @AlteracaoPATCHcomFaltaDeCamposObrigatorios
  Scenario Outline: "<CT>" alteracao PUT com a falta de campos obrigatorios  - HTTP 400 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum" e retirando o campo "<campoRetirado>"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PATCH pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "400"
    Examples:
      | CT    | registro   | payload                 | endpoint            | campoRetirado |
      | CT047 | items      | postItemsCompleto       | endpointItems       | name          |
      | CT048 | items      | postItemsCompleto       | endpointItems       | height        |
      | CT049 | items      | postItemsCompleto       | endpointItems       | weight        |
      | CT050 | items      | postItemsCompleto       | endpointItems       | width         |
      | CT029 | inventario | postInventoriesCompleto | endpointInventories | item_id       |
      | CT030 | inventario | postInventoriesCompleto | endpointInventories | deposit_id    |
      | CT031 | inventario | postInventoriesCompleto | endpointInventories | item_count    |

  @AlteracaoPUTParcial
  Scenario Outline: "<CT>" alteracao PUT com todos apenas um campo  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PUT pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "200"
    And espero que o objeto do body "<objeto>" venha com a estrutura do regex "<regex>"
    Examples:
      | CT    | registro  | payload             | endpoint         | objeto  | regex  |
      | CT014 | depositos | postDepositsParcial | endpointDeposits | zipcode | (^.*$) |
            #Documentação exige items com todos os campos obrigatorios
      #|       | items     | postItemsParcial    | endpointItems    | weight  | (^.*$) |
    #Documentação exige inventario com todos os campos obrigatorios
      #|  | inventario | postInventoriesParcial | endpointInventories | id     | (^\d*$) |

  @AlteracaoPUTVazia
  Scenario Outline: "<CT>" alteracao PUT com nenhum campo  - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum"
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao PUT pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "400"
    And que venha um erro "erro" no response body
    Examples:
      | CT    | registro  | payload               | endpoint         | objeto | regex  |
      | CT015 | depositos | postDepositsSemCampos | endpointDeposits | name   | (^.*$) |


  @PostComRetiradaDeCampos
  Scenario Outline: "<CT>" Retirada de campos obrigatorios do payload
    Given que seja definido o payload "<payload>", aleatorizando os campos ja colocados "RandomNum" e retirando o campo "<campoASerRetirado>"
    Given que seja definido o endpoint como "<endpoint>"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "<code>"
    And que venha um erro "erro" no response body
    Examples:
      | CT    | campoASerRetirado | payload                 | endpoint            | code |
      | CT002 | name              | postDepositsCompleto    | endpointDeposits    | 400  |
      | CT003 | address           | postDepositsCompleto    | endpointDeposits    | 400  |
      | CT004 | city              | postDepositsCompleto    | endpointDeposits    | 400  |
      | CT005 | state             | postDepositsCompleto    | endpointDeposits    | 400  |
      | CT006 | zipcode           | postDepositsCompleto    | endpointDeposits    | 400  |
      | CT039 | name              | postItemsCompleto       | endpointItems       | 400  |
      | CT040 | height            | postItemsCompleto       | endpointItems       | 400  |
      | CT041 | width             | postItemsCompleto       | endpointItems       | 400  |
      | CT042 | weight            | postItemsCompleto       | endpointItems       | 400  |
      | CT019 | item_id           | postInventoriesCompleto | endpointInventories | 400  |
      | CT020 | deposit_id        | postInventoriesCompleto | endpointInventories | 400  |
      | CT021 | item_count        | postInventoriesCompleto | endpointInventories | 400  |


  @PostComTipagemIncorreta
  Scenario Outline: "<CT>" Criacao de registros com tipagem incorreta de campos
    Given que seja definido o payload "<payload>", modificando o campo "<campo>" para o valor "<valor>"
    Given que seja definido o endpoint como "<endpoint>"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "<code>"
    And que venha um erro "erro" no response body
    Examples:
      | CT    | campo      | payload                 | endpoint            | code | valor |
      | CT022 | item_id    | postInventoriesCompleto | endpointInventories | 400  | teste |
      | CT023 | deposit_id | postInventoriesCompleto | endpointInventories | 400  | teste |
      | CT024 | item_count | postInventoriesCompleto | endpointInventories | 400  | teste |


  @ExclusaoComIdValido
  Scenario Outline: "<CT>" Exclusao de registro especifico que esteja criado - HTTP 200 esperado
    Given que eu tenha um registro "<registro>" criado
    Given que seja definido o endpoint como "<endpoint>" com parametro de id valido
    When executar uma requisicao DELETE pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações, troque o 200 abaixo por 204 para dar bypass na falha de response code
    Then espero receber um response code "200"
    Examples:
      | CT    | registro   | endpoint            |
      | CT017 | deposito   | endpointDeposits    |
      | CT037 | inventario | endpointInventories |
      | CT057 | items      | endpointItems       |


  @ExclusaoComIdInvalido
  Scenario Outline: "<CT>" Exclusao de registro especifico que nao esteja criado - HTTP 404 esperado
    Given que seja definido o endpoint como "<endpoint>" com parametro de id invalido
    When executar uma requisicao DELETE pelo rest-assured com tempo maximo de "5" segundos de resposta
    #caso queira testar as validações por regex, troque o 200 abaixo por 201 para dar bypass na falha de response code
    Then espero receber um response code "404"
    And que venha um erro "Not Found" no response body
    Examples:
      | CT    | endpoint            |
      | CT017 | endpointDeposits    |
      | CT037 | endpointInventories |
      | CT057 | endpointItems       |


  @PostCominventorioIncorreto
  Scenario Outline: "<CT>" Criacao de registros de inventario com dados incorretos de campos
    Given que seja definido o payload "<payload>", modificando o campo "<campo>" para o valor "<valor>"
    Given que seja definido o endpoint como "<endpoint>"
    When executar uma requisicao POST pelo rest-assured com tempo maximo de "5" segundos de resposta
    Then espero receber um response code "<code>"
    And que venha um erro "must exist" no response body
    Examples:
      | CT    | campo      | payload                 | endpoint            | code | valor         |
      | CT058 | item_id    | postInventoriesCompleto | endpointInventories | 422  | 1111111111111 |
      | CT059 | deposit_id | postInventoriesCompleto | endpointInventories | 422  | 1111111111111 |
