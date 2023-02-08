from behave import given, when, then
import requests


@given("eu requira a lista de Items via API")
def requerir_info(context):
    context.response = requests.get(
        "https://test-bees.herokuapp.com/items.json"
    )


@then("o statusCode deve ser 200 e o body da API Items nao deve ser vazio")
def verificar_retorno(context):
    assert context.response.status_code == 200, context.response.status_code
    assert context.response.content != []


@given("eu tenha um payload de Item valido")
def payload_valido(context):
    context.payload = {
        "name": "Item Teste Api - Ruan",
        "height": "10",
        "width": "10",
        "weight": "20",
    }


@when("eu realizo um POST utilizando o endereco da API de Items")
def realizar_post(context):
    context.response = requests.post(
        "https://test-bees.herokuapp.com/items.json", json=context.payload
    )


@then(
    "o statusCode deve ser 201 e o retorno da API Items tera as informacoes do payload"
)
def verificar_retorno(context):
    item = context.response.json()
    context.item_id = item["id"]

    assert context.response.status_code == 201
    assert item["name"] == context.payload["name"]


@given("eu requira um Item via ID")
def requerir_dado_ID(context):
    context.response = requests.get(
        "https://test-bees.herokuapp.com/items/10.json"
    )


@when("eu realizo uma atualizacao no Item via API com PATCH")
def atualizar_dado_path(context):
    context.response = requests.patch(
        "https://test-bees.herokuapp.com/items/10.json", json=context.payload
    )


@then(
    "o statusCode deve ser 200 e o Item deve ter sido atualizado conforme payload"
)
def verificar_retorno_path(context):
    item = context.response.json()

    assert context.response.status_code == 200
    assert item["name"] == context.payload["name"]


@when("eu realizo uma atualizacao no Item via API com PUT")
def atualizar_dado_path(context):

    context.response = requests.put(
        "https://test-bees.herokuapp.com/items/10.json", json=context.payload
    )


@when("eu requira a exclusao de um Item")
def excluir_item(context):
    id = context.item_id

    context.response = requests.delete(
        f"https://test-bees.herokuapp.com/deposits/{id}.json"
    )


@then("o statusCode da API Items deve ser 204")
def excluir_item(context):
    assert context.response.status_code == 204
