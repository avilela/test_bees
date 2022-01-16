from selenium.webdriver import Chrome, Firefox
from selenium.webdriver.chrome.options import Options
from modules.po_login_page import LoginPage
from modules.po_home_page import HomePage
import modules.data_test as dt
from ipdb import spost_mortem

def before_all(context):
    """
    Test data informations
    """
    context.email_to = context.config.userdata.get("email_to")

    """
    Webdriver configurations
    """
    drive_option = Options()
    headeless = context.config.userdata.getbool('headeless')
    drive_option.headless = headeless

    browser = context.config.userdata.get('browser')
    browsers = {
        'chrome': Chrome,
        'firefox': Firefox
    }

    context.driver = browsers[browser](options=drive_option)
    context.driver.maximize_window()
    context.driver.implicitly_wait(6)

    """
    Page Objects
    """
    context.lp = LoginPage(context.driver)
    context.hp = HomePage(context.driver)

def before_step(context, step):
    context.hp = HomePage(context.driver)
    if step.name == "I should to get access to the main email page":
        context.logged = context.hp.is_logged_in(10, dt.login['username'])
    elif step.name == "I am on login email page"or"I should to be logged out"or"I want to logout":
        context.logged = context.hp.is_logged_in(1, dt.login['username'])

def after_step(context, step):
    if context.config.userdata.getbool("debug") and step.status == "failed":
        spost_mortem(step.exc_traceback)

def after_all(context):
    context.driver.quit()


    

