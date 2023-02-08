from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


class HomePageElements:
    def __init__(self, browser):
        self.url = "https://test-bees.herokuapp.com/"
        self.driver = browser

    def msg_signed_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def button_logout_element(self):
        logout = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (
                    By.XPATH,
                    '//*[@id="navbarSupportedContent"]/ul/li[4]/form/button',
                )
            )
        )
        return logout

    def button_deposits_element(self):
        deposits = self.driver.find_element(
            By.XPATH, '//*[@id="navbarSupportedContent"]/ul/li[1]/a'
        )
        return deposits

    def button_items_element(self):
        items = self.driver.find_element(
            By.XPATH, '//*[@id="navbarSupportedContent"]/ul/li[2]/a'
        )
        return items

    def button_inventory_element(self):
        inventory = self.driver.find_element(
            By.XPATH, '//*[@id="navbarSupportedContent"]/ul/li[3]/a'
        )
        return inventory
