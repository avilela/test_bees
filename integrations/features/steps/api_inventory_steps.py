from behave import given, when, then
import requests


@given("eu requira a lista de Inventories via API")
def requerir_info(context):
    context.response = requests.get(
        "https://test-bees.herokuapp.com/inventories.json"
    )


@then(
    "o statusCode deve ser 200 e o body da API Inventories nao deve ser vazio"
)
def verificar_retorno(context):
    assert context.response.status_code == 200, context.response.status_code
    assert context.response.content != []


@given("eu tenha um payload de Inventory valido")
def payload_valido(context):
    context.items_payload = {
        "name": "Item Teste Api - Ruan",
        "height": "10",
        "width": "10",
        "weight": "20",
    }

    context.deposits_payload = {
        "name": "Deposito Teste Api - Ruan",
        "address": "Rua A",
        "city": "VR",
        "state": "RJ",
        "zipcode": "27220030",
    }

    context.response_deposit = requests.post(
        "https://test-bees.herokuapp.com/deposits.json",
        json=context.deposits_payload,
    )

    context.response_items = requests.post(
        "https://test-bees.herokuapp.com/items.json",
        json=context.items_payload,
    )

    item = context.response_items.json()
    context.item_id = item["id"]

    deposito = context.response_deposit.json()
    context.deposit_id = deposito["id"]

    context.payload = {
        "item_id": context.item_id,
        "deposit_id": context.deposit_id,
        "item_count": 10,
    }


@when("eu realizo um POST utilizando o endereco da API de Inventories")
def realizar_post(context):
    context.response = requests.post(
        "https://test-bees.herokuapp.com/inventories.json",
        json=context.payload,
    )


@then(
    "o statusCode deve ser 201 e o retorno da API Inventories tera as informacoes do payload"
)
def verificar_retorno(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]
    context.inventario_item_id = inventario["item_id"]
    context.inventario_deposit_id = inventario["deposit_id"]

    deposito = context.response_deposit.json()
    context.deposit_id = deposito["id"]

    item = context.response_items.json()
    context.item_id = item["id"]

    assert context.response.status_code == 201
    assert context.inventario_item_id == context.item_id
    assert context.inventario_deposit_id == context.deposit_id


@when("eu requira um Inventory via ID")
def requerir_dado_ID(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]
    context.response = requests.get(
        f"https://test-bees.herokuapp.com/inventories/{context.inventario_id}.json"
    )


@when("eu realizo uma atualizacao no Inventory via API com PATCH")
def atualizar_dado_path(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]

    context.response = requests.patch(
        f"https://test-bees.herokuapp.com/inventories/{context.inventario_id}.json",
        json=context.payload,
    )


@then(
    "o statusCode deve ser 200 e o Inventory deve ter sido atualizado conforme payload"
)
def verificar_retorno_path(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]
    context.inventario_item_id = inventario["item_id"]
    context.inventario_deposit_id = inventario["deposit_id"]

    deposito = context.response_deposit.json()
    context.deposit_id = deposito["id"]

    item = context.response_items.json()
    context.item_id = item["id"]

    assert context.response.status_code == 200
    assert context.inventario_item_id == context.item_id
    assert context.inventario_deposit_id == context.deposit_id


@when("eu realizo uma atualizacao no Inventory via API com PUT")
def atualizar_dado_path(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]

    context.response = requests.put(
        f"https://test-bees.herokuapp.com/inventories/{context.inventario_id}.json",
        json=context.payload,
    )


@when("eu requira a exclusao de um Inventory")
def excluir_item(context):
    inventario = context.response.json()
    context.inventario_id = inventario["id"]

    context.response = requests.delete(
        f"https://test-bees.herokuapp.com/inventories/{context.inventario_id}.json"
    )


@then("o statusCode da API Inventories deve ser 204")
def excluir_item(context):
    assert context.response.status_code == 204
