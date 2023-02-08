from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select


class InventoryPageElements:
    def __init__(self, browser):
        self.url = "https://test-bees.herokuapp.com/"
        self.driver = browser

    def button_show_inventory_element(self):
        show_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div/table/tbody/tr[1]/td[4]/a"
        )
        return show_inventory

    def button_new_inventory_element(self):
        new_inventory = self.driver.find_element(By.XPATH, "/html/body/div/a")
        return new_inventory

    def button_edit_inventory_element(self):
        edit_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/a[1]"
        )
        return edit_inventory

    def button_create_inventory_element(self):
        create_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return create_inventory

    def button_destroy_inventory_element(self):
        destroy_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/form/button"
        )
        return destroy_inventory

    def button_update_inventory_element(self):
        update_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return update_inventory

    def msg_inventory_created_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_inventory_destroyed_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_inventory_updated_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def field_item_count_inventory_edit_element_update(self):
        item_count_inventory_edit = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[3]/input"
        )
        return item_count_inventory_edit

    def list_item_create_inventory_element(self):
        select = Select(self.driver.find_element(By.ID, "inventory_item_id"))

        lista = []
        lista = select.options
        tamanho_lista = len(lista)

        return select.select_by_index(tamanho_lista - 1)

    def list_deposit_create_inventory_element(self):
        select = Select(
            self.driver.find_element(By.ID, "inventory_deposit_id")
        )

        lista = []
        lista = select.options
        tamanho_lista = len(lista)

        return select.select_by_index(tamanho_lista - 1)

    def field_item_count_inventory_create_element(self):
        item_count_inventory_create = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[3]/input"
        )
        return item_count_inventory_create

    def text_title_list_inventory_page_element(self):
        text_title = self.driver.find_element(By.XPATH, "/html/body/div/h1")
        return text_title

    def table_list_inventory_page_element(self):
        table_list_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div/table"
        )
        return table_list_inventory

    def show_name_inventory_page_element(self):
        name_inventory = self.driver.find_element(
            By.XPATH, "/html/body/div/div[1]/p[1]/strong"
        )
        return name_inventory
