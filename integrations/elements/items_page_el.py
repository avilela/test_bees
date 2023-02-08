from selenium.webdriver.common.by import By


class ItemsPageElements:
    def __init__(self, browser):
        self.url = "https://test-bees.herokuapp.com/"
        self.driver = browser

    def button_show_item_element(self):
        show_item = self.driver.find_element(
            By.XPATH, "/html/body/div/div/table/tbody/tr[1]/td[5]/a"
        )
        return show_item

    def button_new_item_element(self):
        new_item = self.driver.find_element(By.XPATH, "/html/body/div/a")
        return new_item

    def button_edit_item_element(self):
        edit_item = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/a[1]"
        )
        return edit_item

    def button_create_item_element(self):
        create_item = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return create_item

    def button_destroy_item_element(self):
        destroy_item = self.driver.find_element(
            By.XPATH, "/html/body/div/div[2]/form/button"
        )
        return destroy_item

    def button_update_item_element(self):
        update_item = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[2]/input"
        )
        return update_item

    def msg_item_created_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_item_destroyed_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def msg_item_updated_successfully_element(self):
        msg = self.driver.find_element(By.XPATH, "/html/body/div/p")
        return msg

    def field_name_item_edit_element(self):
        name_item_edit = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[1]/input"
        )
        return name_item_edit

    def field_name_item_create_element(self):
        name_item_create = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[1]/input"
        )
        return name_item_create

    def field_height_create_item_element(self):
        height_create_item = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[2]/input"
        )
        return height_create_item

    def field_width_create_item_element(self):
        width_create_item = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[3]/input"
        )
        return width_create_item

    def field_weight_create_item_element(self):
        weight_create_item = self.driver.find_element(
            By.XPATH, "/html/body/div/form/div[1]/div[4]/input"
        )
        return weight_create_item

    def text_title_list_items_page_element(self):
        text_title = self.driver.find_element(By.XPATH, "/html/body/div/h1")
        return text_title

    def table_list_items_page_element(self):
        table_list_items = self.driver.find_element(
            By.XPATH, "/html/body/div/div/table"
        )
        return table_list_items

    def show_name_items_page_element(self):
        name_items = self.driver.find_element(
            By.XPATH, "/html/body/div/div[1]/p[1]/strong"
        )
        return name_items
