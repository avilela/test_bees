from page_objects import PageElement, PageObject

class LoginPage(PageObject):
    username_input = PageElement(id_="identifierId")
    password_input = PageElement(name='password')

    btn_next_username =  PageElement(id_="identifierNext")
    btn_next_password = PageElement(id_="passwordNext")

    highlight_pwd = PageElement(xpath='//div[@jsname="B34EJ"]/span')
    
    def set_username(self, username: str):
        """
        Types the username in the "username" field
        """
        self.username_input.send_keys(username)

    def type_password(self, password: str):
        """
        Types the password in the "password" field
        """
        self.password_input.send_keys(password)

    def do_login(self, username: str, password: str):
        """
        Types the username
        """
        self.username_input.send_keys(username)
        self.btn_next_username.click()

        """
        Types the password and clicks on Login
        """
        self.password_input.send_keys(password)
        self.btn_next_password.click()
        
    def highlight_pwd_wrong(self, highlight):
        """
        Types the password and clicks on Login
        """
        assert self.highlight_pwd.text == highlight
        
    def clear_web_data(self, context):
        """
        Is mandatory clear all cookies because webdriver changes the page when 
        try to get www.gmail.com more than one time
        """
        context.driver.delete_all_cookies()