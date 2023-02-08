from behave import given, when, then
from elements.login_el import LoginElements
from elements.home_page_el import HomePageElements
from time import sleep
from faker import Faker
import random

fake = Faker()


@given("eu estou na pagina de login")
def open_site(context):
    context.login_el = LoginElements(context.browser)
    context.browser.get(context.login_el.url)


@when("preencho os campos com credenciais {entrada}")
def fill_fields_credentials(context, entrada):
    if entrada == "'validas'":
        context.login_el.field_email_login_element().send_keys(
            "ruanluiz.contato@gmail.com"
        )
        context.login_el.field_password_login_element().send_keys("0250036045")

    elif entrada == "'invalidas'":
        context.login_el.field_email_login_element().send_keys(
            "ruanluiz.contato@gmail.com"
        )
        context.login_el.field_password_login_element().send_keys("1234")


@when("clico no botao {nome_botao}")
def click_button_submit(context, nome_botao):
    if nome_botao == "'Submit'":
        context.login_el.button_submit_element().click()
        sleep(5)

    elif nome_botao == "'Sign up Login'":
        context.login_el.button_sign_up_login_element().click()

    elif nome_botao == "'Sign up Registro'":
        context.login_el.button_sign_up_register_element().click()

    elif nome_botao == "'Send me reset password instructions'":
        context.login_el.button_send_reset_password_element().click()

    elif nome_botao == "'Forgot your password?'":
        context.login_el.button_forgot_your_password_element().click()


@then("exibe uma mensagem indicando {msg_exibida}")
def verify_msg(context, msg_exibida):

    current_url = context.browser.current_url
    context.home_page_el = HomePageElements(context.browser)

    if msg_exibida == "'sucesso'":
        msg_sucesso = (
            context.home_page_el.msg_signed_successfully_element().text
        )

        assert msg_sucesso == "Signed in successfully."
        assert current_url == "https://test-bees.herokuapp.com/"

    elif msg_exibida == "'insucesso'":
        # site não possui msg de erro para login não realizado
        msg_insucesso = (
            context.home_page_el.field_for_undeveloped_feautes().text
        )

        assert current_url == "https://test-bees.herokuapp.com/users/sign_in"

        # erro forçado, pois nao há msg para falha de login
        assert msg_insucesso == "Failed to login"

    elif msg_exibida == "'sucesso no cadastro'":
        msg_sucesso = (
            context.home_page_el.msg_signed_successfully_element().text
        )
        assert msg_sucesso == "Welcome! You have signed up successfully."

    elif msg_exibida == "envio de mensagem para recuperacao de senha":
        msg_sucesso = (
            context.login_el.msg_forgot_password_successfully_element().text
        )

        # erro forçado, pois a página quebra
        assert current_url == "https://test-bees.herokuapp.com/users/password/"

        # erro forçado, pois a página quebra
        assert msg_sucesso == "Email for passowrd change has been sent."

    elif msg_exibida == "email invalido ou nao cadastrado":
        # erro forçado, pois a página quebra
        assert current_url == "https://test-bees.herokuapp.com/users/password/"

        msg_exibida = (
            context.login_el.msg_forgot_password_successfully_element().text
        )

        # erro forçado, pois a página quebra
        assert msg_sucesso == "Invalid or unregistered email."

    elif msg_exibida == "informacoes invalidas":
        # deve se manter na mesma página, pois cadastro n foi realizado
        assert current_url == "https://test-bees.herokuapp.com/users/sign_up"

        msg_exibida = context.login_el.field_for_undeveloped_feautes().text

        # erro forçado, pois não foi desenvolvida uma mensagem de alerta p/ este caso
        assert msg_exibida == "Information entered is invalid."

    elif msg_exibida == "email ja registrado":
        # deve se manter na mesma página, pois cadastro n foi realizado
        assert current_url == "https://test-bees.herokuapp.com/users/sign_up"

        msg_exibida = context.login_el.field_for_undeveloped_feautes().text

        # erro forçado, pois não foi desenvolvida uma mensagem de alerta p/ este caso
        assert (
            msg_exibida
            == "This email already has an account associated with it."
        )


@then("realizo logout")
def logout(context):
    context.home_page_el = HomePageElements(context.browser)
    context.home_page_el.button_logout_element().click()
    sleep(5)


@when("preencho o campo com um e-mail {entrada} associado a uma conta")
def fill_field_email_forgot_password(context, entrada):
    if entrada == "valido":
        context.login_el.field_email_login_element().send_keys(
            "ruanluiz.contato@gmail.com"
        )

    if entrada == "invalido":
        email = f"{fake.first_name()}@{fake.company()}.com"
        email.replace(" ", "")
        context.login_el.field_email_login_element().send_keys(email)


@when("eu preencho o formulario de registro com {entrada}")
def fill_fields(context, entrada):
    sleep(5)
    if entrada == "'dados validos'":
        email = f"ruan{random.randrange(1,10000)}-{random.randrange(1,10000)}@gmail.com"

        senha = fake.password()

        context.login_el.field_email_login_element().send_keys(email)
        context.login_el.field_password_register_element().send_keys(senha)
        context.login_el.field_password_confirmation_register_element().send_keys(
            senha
        )

    elif entrada == "dados invalidos":
        email_invalido = f"{fake.first_name()}.com"
        email_invalido.replace(" ", "")
        context.login_el.field_email_login_element().send_keys(email_invalido)

        senha = fake.password()
        context.login_el.field_password_register_element().send_keys(senha)
        context.login_el.field_password_confirmation_register_element().send_keys(
            senha
        )

    elif entrada == "email cadastrado":
        email_cadastrado = "ruanluiz.contato@gmail.com"
        context.login_el.field_email_login_element().send_keys(
            email_cadastrado
        )

        senha = str(fake.password())
        context.login_el.field_password_register_element().send_keys(senha)
        context.login_el.field_password_confirmation_register_element().send_keys(
            senha
        )
