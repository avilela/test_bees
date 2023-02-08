from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


class LoginElements:
    def __init__(self, browser):
        self.url = "https://test-bees.herokuapp.com/"
        self.driver = browser

    def field_email_login_element(self):
        email = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user_email"))
        )
        return email

    def field_password_login_element(self):
        password = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user_password"))
        )
        return password

    def field_email_register_element(self):
        email = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user_email"))
        )
        return email

    def field_password_register_element(self):
        password = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user_password"))
        )
        return password

    def field_password_confirmation_register_element(self):
        confirmation_password = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div/form/div[1]/div[3]/input")
            )
        )
        return confirmation_password

    def field_email_forgot_password_element(self):
        email = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user_email"))
        )
        return email

    # criado para quebrar validações de features não desenvolvidas
    def field_for_undeveloped_feautes(self):
        field_undeveloped = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div[1]/form/div[50]/input")
            )
        )
        return field_undeveloped

    def button_submit_element(self):
        submit = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div[1]/form/div[2]/input")
            )
        )
        return submit

    def button_sign_up_login_element(self):
        sign_up = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div[1]/a[1]")
            )
        )
        return sign_up

    def button_forgot_your_password_element(self):
        forgot_password = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div[1]/a[2]")
            )
        )
        return forgot_password

    def button_sign_up_register_element(self):
        sign_up = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div/form/div[2]/input")
            )
        )
        return sign_up

    def button_send_reset_password_element(self):
        reset_password = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, "/html/body/div/form/div[2]/input")
            )
        )
        return reset_password

    def msg_forgot_password_successfully_element(self):
        msg = WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.XPATH, "/html/body/div/p"))
        )
        return msg
