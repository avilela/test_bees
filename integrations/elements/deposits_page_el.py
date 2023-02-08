from selenium.webdriver.common.by import By
from time import sleep


class DepositsPageElements:
    def __init__(self, browser):
        self.url = "https://test-bees.herokuapp.com/"
        self.driver = browser

    def button_show_deposit_element(self):
        show_deposit = self.driver.find_element(
            By.XPATH, "/html/body/div[1]/div/table/tbody/tr[1]/td[7]/a"
        )
        return show_deposit

    def button_new_deposit_element(self):
        new_deposit = self.driver.find_element(By.XPATH, "/html/body/div/a")
        return new_deposit

    def button_edit_deposit_element(self):
        edit_deposit = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/a[1]"
        )
        return edit_deposit

    def button_create_deposit_element(self):
        create_deposit = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return create_deposit

    def button_destroy_deposit_element(self):
        destroy_deposit = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/form/button"
        )
        return destroy_deposit

    def button_update_deposit_element(self):
        update_deposit = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return update_deposit

    def msg_deposits_created_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_deposit_destroyed_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_deposit_updated_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def field_name_edit_deposity_element(self):
        name_edit_deposity = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[1]/input"
        )
        return name_edit_deposity

    def field_name_create_deposity_element(self):
        name_create_deposity = self.driver.find_element(By.ID, "deposit_name")
        return name_create_deposity

    def field_address_create_deposity_element(self):
        address_create_deposity = self.driver.find_element(
            By.ID, "deposit_address"
        )
        return address_create_deposity

    def field_city_create_deposity_element(self):
        city_create_deposity = self.driver.find_element(By.ID, "deposit_city")
        return city_create_deposity

    def field_state_create_deposity_element(self):
        state_create_deposity = self.driver.find_element(
            By.ID, "deposit_state"
        )
        return state_create_deposity

    def field_zipcode_create_deposity_element(self):
        zipcode_create_deposity = self.driver.find_element(
            By.ID, "deposit_zipcode"
        )
        return zipcode_create_deposity

    def text_title_list_deposits_page_element(self):

        text_title_list_deposits = self.driver.find_element(
            By.XPATH, "/html/body/div/h1"
        )
        return text_title_list_deposits

    def table_list_deposits_page_element(self):
        table_list_deposits = self.driver.find_element(
            By.XPATH, "/html/body/div/div/table"
        )
        return table_list_deposits

    def show_name_deposits_page_element(self):
        name_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div[1]/p[1]/strong"
        )
        return name_inventory

    def container_page(self):
        container = self.driver.find_element(By.XPATH, "/html/body/div")
        return container
