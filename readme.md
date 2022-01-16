# ABINBEV CHALANGE - AUTOMATION TO SEND EMAIL USING GMAIL

#### Requirements
- python3
- Google Chrome
- chromedriver (must to be included in your System's PATH)
- If you prefer you also can use Firefox and geckodriver

#### How to setup

1. If you prefer you can create a python virtual environment(recommended):

		 pip install virtualenv
		 python3 -m venv .venv

   Active virtual environment: 
   
		On Unix or MacOS, using the bash shell: source /path/to/venv/bin/activate
		On Unix or MacOS, using the csh shell: source /path/to/venv/bin/activate.csh
		On Unix or MacOS, using the fish shell: source /path/to/venv/bin/activate.fish
		On Windows using the Command Prompt: path\to\venv\Scripts\activate.bat 
		On Windows using PowerShell: .\path\to\.venv\Scripts\Activate.ps1

2. Install the requirements:
		pip install -r requirements.txt
#### How to execute?
3. On data_test.py file, created to facilitate maintenance, you can change the username, password, email_to, or what you want.
	You also can use below command to select a new email to, by default destination and delivery emails are the same.
	
		behave -D email_to=email@email.com
		
4. To execute all features by default, on root folder of the project just type:
	A) To use Chrome:
		behave

	B) To use firefox:
		 behave -D browser=firefox

5. To execute in headeless (not stable)
		behave -D headeless=true

6. To see the reports, when the tests over, check the folder reports, all feature reports will be there.
