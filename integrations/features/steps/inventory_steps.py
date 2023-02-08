from behave import given, when, then
from elements.login_el import LoginElements
from elements.home_page_el import HomePageElements
from elements.inventory_page_el import InventoryPageElements
from elements.items_page_el import ItemsPageElements
from elements.deposits_page_el import DepositsPageElements
import random
from faker import Faker
from faker_food import FoodProvider  # https://pypi.org/project/faker-food/

fake = Faker()
fake.add_provider(FoodProvider)


@when("eu tenha um item e um deposito cadastrado")
def register_item_deposito(context):
    # cadastro de novo item
    context.items_page_el = ItemsPageElements(context.browser)
    url = "https://test-bees.herokuapp.com/items/new"
    context.browser.get(url)

    nome_item = f"box of {fake.fruit()} {random.randrange(1, 1000)}"
    altura = random.randrange(1, 50)
    largura = random.randrange(1, 50)
    peso = random.randrange(1, 50)

    context.items_page_el.field_name_item_create_element().send_keys(nome_item)
    campo_altura = context.items_page_el.field_height_create_item_element()
    campo_altura.send_keys(altura)

    campo_largura = context.items_page_el.field_width_create_item_element()
    campo_largura.send_keys(largura)

    campo_peso = context.items_page_el.field_weight_create_item_element()
    campo_peso.send_keys(peso)

    context.items_page_el.button_create_item_element().click()

    # cadastro de novo Deposito
    context.deposits_page_el = DepositsPageElements(context.browser)
    url = "https://test-bees.herokuapp.com/deposits/new"
    context.browser.get(url)

    nome_deposito = f"Deposito Teste  {random.randrange(1000, 10000)}"
    endereco = f"rua {random.randrange(100, 200)}"
    cidade = "Rio de Janeiro"
    estado = "RJ"
    cep = "27220030"

    context.deposits_page_el.field_name_create_deposity_element().send_keys(
        nome_deposito
    )
    context.deposits_page_el.field_address_create_deposity_element().send_keys(
        endereco
    )
    context.deposits_page_el.field_city_create_deposity_element().send_keys(
        cidade
    )
    context.deposits_page_el.field_state_create_deposity_element().send_keys(
        estado
    )
    context.deposits_page_el.field_zipcode_create_deposity_element().send_keys(
        cep
    )

    context.deposits_page_el.button_create_deposit_element().click()


@given("eu estou logado")
def open_site(context):
    context.login_el = LoginElements(context.browser)
    context.inventory_page_el = InventoryPageElements(context.browser)
    context.home_page_el = HomePageElements(context.browser)
    context.browser.get(context.login_el.url)

    context.login_el.field_email_login_element().send_keys(
        "ruanluiz.contato@gmail.com"
    )
    context.login_el.field_password_login_element().send_keys("0250036045")
    context.login_el.button_submit_element().click()


@when("estou na pagina de inventario")
def click_button_inventory(context):
    context.home_page_el = HomePageElements(context.browser)
    context.login_el = LoginElements(context.browser)

    context.home_page_el.button_inventory_element().click()


@then("devo visualizar a lista de inventario")
def check_list_inventory(context):
    context.inventory_page_el = InventoryPageElements(context.browser)

    titulo_lista_inventory = (
        context.inventory_page_el.text_title_list_inventory_page_element().is_displayed()
    )

    assert titulo_lista_inventory is True


@when("clico no botao: {nome_botao}")
def click_button_submit(context, nome_botao):
    context.inventory_page_el = InventoryPageElements(context.browser)

    if nome_botao == "'Show this inventory'":
        botao = context.inventory_page_el.button_show_inventory_element()
        botao.click()

    elif nome_botao == "'New inventory'":
        botao = context.inventory_page_el.button_new_inventory_element()
        context.browser.execute_script("arguments[0].click();", botao)

    elif nome_botao == "'Create inventory'":
        context.inventory_page_el.button_create_inventory_element().click()

    elif nome_botao == "'Edit this inventory'":
        context.inventory_page_el.button_edit_inventory_element().click()

    elif nome_botao == "'Update inventory'":
        context.inventory_page_el.button_update_inventory_element().click()

    elif nome_botao == "'Destroy this inventory'":
        botao = context.inventory_page_el.button_destroy_inventory_element()
        botao.click()


@then("sou redirecionado para uma tela que exibe as informacoes do inventario")
def check_details_inventory(context):
    context.inventory_page_el = InventoryPageElements(context.browser)

    nome_deposits = (
        context.inventory_page_el.show_name_inventory_page_element().is_displayed()
    )

    assert nome_deposits is True


@when("eu preencho o formulario com as informacoes necessarias")
def fill_necessary_infos(context):
    context.inventory_page_el = InventoryPageElements(context.browser)

    item_count = random.randrange(50)

    context.inventory_page_el.list_item_create_inventory_element()
    context.inventory_page_el.list_deposit_create_inventory_element()
    context.inventory_page_el.field_item_count_inventory_create_element().send_keys(
        item_count
    )


@then("exibe uma mensagem: {msg_exibida}")
def verify_msg(context, msg_exibida):
    context.inventory_page_el = InventoryPageElements(context.browser)
    context.home_page_el = HomePageElements(context.browser)

    if msg_exibida == "Sucesso ao criar inventory":
        msg_sucesso = (
            context.inventory_page_el.msg_inventory_created_successfully_element().text
        )
        assert msg_sucesso == "Inventory was successfully created."

    elif msg_exibida == "Sucesso ao editar inventory":
        msg_sucesso = (
            context.inventory_page_el.msg_inventory_updated_successfully_element().text
        )
        assert msg_sucesso == "Inventory was successfully updated."

    elif msg_exibida == "Sucesso ao deletar inventory":

        msg_sucesso = (
            context.inventory_page_el.msg_inventory_destroyed_successfully_element().text
        )
        assert msg_sucesso == "Inventory was successfully destroyed."


@when("edito os campos do formulario de inventario")
def edit_form_fields(context):
    context.inventory_page_el = InventoryPageElements(context.browser)

    item_count = random.randrange(1000)
    context.inventory_page_el.field_item_count_inventory_edit_element_update().send_keys(
        item_count
    )
