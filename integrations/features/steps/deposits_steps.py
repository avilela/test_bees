from behave import given, when, then
from elements.login_el import LoginElements
from elements.home_page_el import HomePageElements
from elements.deposits_page_el import DepositsPageElements
from faker import Faker
from random import randrange


fake = Faker()


@given("estou logado com um usuario")
def open_site(context):
    context.login_el = LoginElements(context.browser)
    context.deposits_page_el = DepositsPageElements(context.browser)
    context.home_page_el = HomePageElements(context.browser)
    context.browser.get(context.login_el.url)

    context.login_el.field_email_login_element().send_keys(
        "ruanluiz.contato@gmail.com"
    )
    context.login_el.field_password_login_element().send_keys("0250036045")
    context.login_el.button_submit_element().click()


@when("estou na pagina de depositos")
def click_button_deposits(context):
    context.home_page_el = HomePageElements(context.browser)
    context.login_el = LoginElements(context.browser)

    context.home_page_el.button_deposits_element().click()


@then("devo visualizar a lista de depositos")
def check_list_deposits(context):
    context.deposits_page_el = DepositsPageElements(context.browser)

    tabela_deposits = (
        context.deposits_page_el.table_list_deposits_page_element().is_displayed()
    )

    assert tabela_deposits is True


@when("eu clico no botao: {nome_botao}")
def click_button_submit(context, nome_botao):
    context.deposits_page_el = DepositsPageElements(context.browser)

    if nome_botao == "'Show this deposit'":
        context.deposits_page_el.button_show_deposit_element().click()

    elif nome_botao == "'New Deposit'":
        botao = context.deposits_page_el.button_new_deposit_element()
        context.browser.execute_script("arguments[0].click();", botao)

    elif nome_botao == "'Create Deposit'":
        context.deposits_page_el.button_create_deposit_element().click()

    elif nome_botao == "'Edit this deposit'":
        context.deposits_page_el.button_edit_deposit_element().click()

    elif nome_botao == "'Update Deposit'":
        context.deposits_page_el.button_update_deposit_element().click()

    elif nome_botao == "'Destroy this deposit'":
        botao = context.deposits_page_el.button_destroy_deposit_element()
        botao.click()


@then("sou redirecionado para uma tela que exibe as informacoes do deposito")
def check_details_deposits(context):
    context.deposits_page_el = DepositsPageElements(context.browser)

    nome_deposits = (
        context.deposits_page_el.show_name_deposits_page_element().is_displayed()
    )

    assert nome_deposits is True


@when("preencho o formulario com as informacoes necessarias do deposito")
def fill_necessary_infos(context):
    context.deposits_page_el = DepositsPageElements(context.browser)

    nome = f"Deposito Teste {randrange(10)}"
    endereco = f"rua {randrange(100)}"
    cidade = "Rio de Janeiro"
    estado = "RJ"
    cep = "27220030"

    context.deposits_page_el.field_name_create_deposity_element().send_keys(
        nome
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


@then("e exibida uma mensagem: {msg_exibida}")
def verify_msg(context, msg_exibida):
    context.deposits_page_el = DepositsPageElements(context.browser)
    context.home_page_el = HomePageElements(context.browser)

    if msg_exibida == "Sucesso ao criar deposito":
        msg_sucesso = (
            context.deposits_page_el.msg_deposits_created_successfully_element().text
        )

        assert msg_sucesso == "Deposits was successfully created."

    elif msg_exibida == "Sucesso ao editar deposito":
        msg_sucesso = (
            context.deposits_page_el.msg_deposit_updated_successfully_element().text
        )

        assert msg_sucesso == "Deposits was successfully updated."

    elif msg_exibida == "Sucesso ao deletar deposito":
        msg_sucesso = (
            context.deposits_page_el.msg_deposit_destroyed_successfully_element().text
        )

        assert msg_sucesso == "Deposits was successfully destroyed."


@when("edito os campos do formulario do deposito")
def edit_form_fields(context):
    context.deposits_page_el = DepositsPageElements(context.browser)

    item_count = str(randrange(1000))
    context.deposits_page_el.field_name_edit_deposity_element().send_keys(
        item_count
    )
