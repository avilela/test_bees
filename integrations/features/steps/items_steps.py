from behave import given, when, then
from elements.login_el import LoginElements
from elements.home_page_el import HomePageElements
from elements.items_page_el import ItemsPageElements
from faker import Faker
from faker_food import FoodProvider  # https://pypi.org/project/faker-food/
import random

fake = Faker()
fake.add_provider(FoodProvider)


@given("eu estou logado com um usuario")
def open_site(context):
    context.login_el = LoginElements(context.browser)
    context.items_page_el = ItemsPageElements(context.browser)
    context.home_page_el = HomePageElements(context.browser)
    context.browser.get(context.login_el.url)

    context.login_el.field_email_login_element().send_keys(
        "ruanluiz.contato@gmail.com"
    )
    context.login_el.field_password_login_element().send_keys("0250036045")
    context.login_el.button_submit_element().click()


@when("estou na pagina de Item")
def click_button_items(context):
    context.home_page_el = HomePageElements(context.browser)

    context.home_page_el.button_items_element().click()


@then("devo visualizar a lista de Items")
def check_list_items(context):
    context.items_page_el = ItemsPageElements(context.browser)

    titulo_lista_items = (
        context.items_page_el.text_title_list_items_page_element().is_displayed()
    )

    tabela_items = (
        context.items_page_el.table_list_items_page_element().is_displayed()
    )

    assert titulo_lista_items is True
    assert tabela_items is True


@when("eu clico no botao {nome_botao}")
def click_button_submit(context, nome_botao):
    context.items_page_el = ItemsPageElements(context.browser)

    if nome_botao == "'Show this item'":

        context.items_page_el.button_show_item_element().click()

    elif nome_botao == "'New Item'":
        botao = context.items_page_el.button_new_item_element()
        context.browser.execute_script("arguments[0].click();", botao)

    elif nome_botao == "'Create Item'":
        context.items_page_el.button_create_item_element().click()

    elif nome_botao == "'Edit this Item'":
        context.items_page_el.button_edit_item_element().click()

    elif nome_botao == "'Update Item'":
        context.items_page_el.button_update_item_element().click()

    elif nome_botao == "'Destroy this Item'":
        botao = context.items_page_el.button_destroy_item_element()
        botao.click()


@then("sou redirecionado para uma tela que exibe as informacoes do Item")
def check_details_item(context):
    context.items_page_el = ItemsPageElements(context.browser)

    nome_items = (
        context.items_page_el.show_name_items_page_element().is_displayed()
    )

    assert nome_items is True


@when("preencho o formulario com as informacoes necessarias")
def fill_necessary_infos(context):
    context.items_page_el = ItemsPageElements(context.browser)

    nome = f"box of {fake.fruit()}"
    altura = random.randrange(1, 50)
    largura = random.randrange(1, 50)
    peso = random.randrange(1, 50)

    context.items_page_el.field_name_item_create_element().send_keys(nome)
    campo_altura = context.items_page_el.field_height_create_item_element()
    campo_altura.send_keys(altura)

    campo_largura = context.items_page_el.field_width_create_item_element()
    campo_largura.send_keys(largura)

    campo_peso = context.items_page_el.field_weight_create_item_element()
    campo_peso.send_keys(peso)


@then("exibe a mensagem indicando {msg_exibida}")
def verify_msg(context, msg_exibida):
    context.items_page_el = ItemsPageElements(context.browser)

    if msg_exibida == "Sucesso ao criar Item":
        msg_sucesso = (
            context.items_page_el.msg_item_created_successfully_element().text
        )

        assert msg_sucesso == "Item was successfully created."

    elif msg_exibida == "Sucesso ao editar Item":
        msg_sucesso = (
            context.items_page_el.msg_item_updated_successfully_element().text
        )

        assert msg_sucesso == "Item was successfully updated."

    elif msg_exibida == "Sucesso ao deletar Item":

        msg_sucesso = (
            context.items_page_el.msg_item_destroyed_successfully_element().text
        )

        assert msg_sucesso == "Item was successfully destroyed."


@when("edito os campos do formulario")
def edit_form_fields(context):
    context.items_page_el = ItemsPageElements(context.browser)

    nome = f"{fake.fruit()} bag"
    context.items_page_el.field_name_item_edit_element().send_keys(nome)
