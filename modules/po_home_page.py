from page_objects import PageElement, PageObject
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from selenium.common.exceptions import TimeoutException

class HomePage(PageObject):
    """
    Logout elements 
    """
    btn_email_manager = PageElement(css='.gb_A.gb_Ka.gb_f')
    btn_logout = PageElement(id_='gb_71')
    email_logout = PageElement(css='.wLBAL')

    """
    Compose email elements 
    """
    btn_new_email = PageElement(css='.T-I.T-I-KE.L3')
    input_to = PageElement(name='to')
    input_subject = PageElement(name='subjectbox')
    email_body = PageElement(css='.Am.Al.editable.LW-avf.tS-tW')
    btn_send_email = PageElement(css='.T-I.J-J5-Ji.aoO.v7.T-I-atl.L3')
    btn_close_email = PageElement(xpath='//td[@class="Hm"]/img[@class="Ha"]')

    """
    Notification alerts 
    """
    notification_alert = PageElement(css='.bAq')
    notification_if_wrong = PageElement(css='.Kj-JD-Jz')
    btn_notification_ok = PageElement(name="ok")
    
    notification_alert_css = ".bAq"
    notification_if_wrong_css = ".Kj-JD-Jz"


    def is_logged_in(self, wait, username):
        """
        Validate if user is logged in
        """
        try:
            assert WebDriverWait(self.w, wait).until(
                EC.title_contains(username))
        except TimeoutException:
            return False
        return True
    
    def create_new_email(self):
        """
        Click on button to create new email
        """
        self.btn_new_email.click()

    def type_email_destination(self, email_to):
        """
        Insert email destination
        """
        self.input_to.send_keys(email_to)

    def type_email_subject(self, subject):
        """
        Insert email subject
        """
        self.input_subject.send_keys(subject)

    def type_email_body(self, body):
        """
        Insert email body
        """        
        self.email_body.send_keys(body)

    def send_email(self):
        """
        Clicks on "send" button
        """
        self.btn_send_email.click()


    def get_notification(self, condition, msg):
        """
        Get msg of notification
        """       
        wait = WebDriverWait(self.w, 10, poll_frequency=1)
        if condition == "success":
           wait.until(lambda drv: drv.find_element_by_css_selector(
               self.notification_alert_css).text != "Sending...")
           return self.notification_alert.text
        elif condition == "fail" or "empty":
           wait.until(lambda drv: drv.find_element_by_css_selector(
               self.notification_if_wrong_css).text != "")
           notification = self.notification_if_wrong.text
           self.btn_notification_ok.click()
           self.btn_close_email.click()
           return notification

    def logout(self):
        """
        Clicks on the Logout button
        """                                               
        self.btn_email_manager.click()
        self.btn_logout.click()
        assert self.email_logout

