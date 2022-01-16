from behave import given, when, then
import modules.data_test as dt

@given("I am in the main page")
def go_to_main_page(context): 
    if context.logged == False:
        context.lp.clear_web_data(context)
        context.driver.get(dt.email['url'])  
        context.lp.do_login(dt.login['username'], dt.login['password_wrong'])

@when(u"I click on new email button")
def compose_email(context):
    context.hp.create_new_email()

@when(u'I set a "{condition}" destination Email')
def type_email_destination(context, condition): 
    if condition == "valid":
        if context.email_to == "":
            context.hp.type_email_destination(dt.email['to_valid'])
        elif context.email_to != "":
            context.hp.type_email_destination(context.email_to)
    elif condition == "invalid":
        context.hp.type_email_destination(dt.email['to_invalid'])
    elif condition == "empty":
        context.hp.type_email_destination(dt.email['to_empty'])

@when(u"I set a subject for the Email")
def type_subject(context):  
    context.hp.type_email_subject(dt.email['subject'])

@when(u"I type the body of the email")
def type_body(context):  
    context.hp.type_email_body(dt.email['body'])

@when(u"I send the Email")
def click_send_email(context):   
    context.hp.send_email()

@then(u'the notification should be: "{condition}"')
def check_notification(context, condition):    
    notification = context.hp.get_notification(condition, dt.notification['success'])
    if condition == "success":
        assert notification == dt.notification['success']
    elif condition == "fail":
        assert notification == dt.notification['fail']
    elif condition == "empty":
        assert notification == dt.notification['empty']

