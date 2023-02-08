from behave import given, when, then
import requests


@given("eu requira a lista de Depositos via API")
def requerir_info(context):
    context.response = requests.get(
        "https://test-bees.herokuapp.com/deposits.json"
    )


@then("o statusCode deve ser 200 e o body nao deve ser vazio")
def verificar_retorno(context):
    assert context.response.status_code == 200, context.response.status_code
    assert context.response.content != []


@given("eu tenha um payload valido")
def payload_valido(context):
    context.payload = {
        "name": "Deposito Teste Api - Ruan",
        "address": "Rua A",
        "city": "VR",
        "state": "RJ",
        "zipcode": "27220030",
    }


@when("eu realizo um POST utilizando o endereco da API")
def realizar_post(context):
    context.response = requests.post(
        "https://test-bees.herokuapp.com/deposits.json", json=context.payload
    )


@then("o statusCode deve ser 201 e o retorno tera as informacoes do payload")
def verificar_retorno(context):
    deposito = context.response.json()
    context.deposito_id = deposito["id"]

    assert context.response.status_code == 201
    assert deposito["name"] == context.payload["name"]


@given("eu requira um Deposito via ID")
def requerir_dado_ID(context):
    context.response = requests.get(
        "https://test-bees.herokuapp.com/deposits/68.json"
    )


@when("eu realizo uma atualizacao no deposito via API com PATCH")
def atualizar_dado_path(context):
    context.response = requests.patch(
        "https://test-bees.herokuapp.com/deposits/68.json",
        json=context.payload,
    )


@then(
    "o statusCode deve ser 200 e o deposito deve ter sido atualizado conforme payload"
)
def verificar_retorno_path(context):
    deposito = context.response.json()
    assert context.response.status_code == 200
    assert deposito["name"] == context.payload["name"]


@when("eu realizo uma atualizacao no deposito via API com PUT")
def atualizar_dado_path(context):
    context.response = requests.put(
        "https://test-bees.herokuapp.com/deposits/68.json",
        json=context.payload,
    )


@when("eu requira a exclusao de um deposito")
def excluir_deposito(context):
    id = context.deposito_id

    context.response = requests.delete(
        f"https://test-bees.herokuapp.com/deposits/{id}.json"
    )


@then("o statusCode deve ser 204")
def excluir_deposito(context):
    assert context.response.status_code == 204
