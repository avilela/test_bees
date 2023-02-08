from behave import fixture, use_fixture
from selenium.webdriver import Chrome


def browser_chrome():
    browser = Chrome(
        executable_path=r"C:\\Users\\kinro\\AppData\\Local\\Programs\\Python\\Python39\\chromedriver.exe"
    )
    return browser


def before_feature(context, feature):

    if feature.name not in [
        "Validacao da API Deposits",
        "Validacao da API Inventories",
        "Validacao da API Items",
    ]:
        context.browser = browser_chrome()
        context.browser.maximize_window()
        context.browser.implicitly_wait(15)


def after_feature(context, feature):
    if feature.name not in [
        "Validacao da API Deposits",
        "Validacao da API Inventories",
        "Validacao da API Items",
    ]:
        context.browser.quit()
