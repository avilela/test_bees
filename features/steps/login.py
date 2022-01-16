from behave import given, when, then
import modules.data_test as dt

@given("I am on login email page")
def go_to_login_page(context):
    context.driver.get(dt.email['url'])   
    if context.logged  == True:
        context.hp.logout()
        
@when(u'I used {condition} user credentials')
def do_login(context, condition):
    if condition == "valid":
        context.lp.do_login(dt.login['username'], dt.login['password'])
    elif condition == "invalid":
        context.lp.do_login(dt.login['username'], dt.login['password_wrong'])
    
@then(u'A hightlight should appear')
def hightlight(context):
    context.lp.highlight_pwd_wrong(dt.login['password_highlight'])
    context.lp.clear_web_data(context)

@then(u'I should to get access to the main email page')
def redirect_to_main_page(context):  
    assert context.logged == True
    
@given(u'I should to be logged out')
def account_logout(context):
    if context.logged == True:
        context.hp.logout()

@then(u'I want to logout')
def account_logout(context): 
    if context.logged == True:
        context.hp.logout()





