Feature: Tela de Login
    Como um usuário do site
    Eu quero poder fazer uma conta, login ou recuperar minha senha
    Para acessar recursos restritos do site

    Scenario Outline: Validacao de Recuperacao de Acesso
        Given eu estou na pagina de login
        When clico no botao 'Forgot your password?'
        And preencho o campo com um e-mail <entrada> associado a uma conta
        And clico no botao 'Send me reset password instructions'
        Then exibe uma mensagem indicando <msg_exibida>

    Examples:
        | entrada | msg_exibida |
        | valido  | envio de mensagem para recuperacao de senha |
        | invalido  | email invalido ou nao cadastrado |

    Scenario: Registro com Sucesso
        Given eu estou na pagina de login
        When clico no botao 'Sign up Login'
        And eu preencho o formulario de registro com 'dados validos'  
        And clico no botao 'Sign up Registro'
        Then exibe uma mensagem indicando 'sucesso no cadastro'
        And realizo logout

    Scenario Outline: Validando Registro sem Sucesso
        Given eu estou na pagina de login
        When clico no botao 'Sign up Login'
        And eu preencho o formulario de registro com <entrada>
        And clico no botao 'Sign up Registro'
        Then exibe uma mensagem indicando <msg_exibida>

    Examples:
        | entrada | msg_exibida |
        | dados invalidos  | informacoes invalidas |
        | email cadastrado  | email ja registrado  |    

    Scenario: Validacao do Login com credenciais invalidas
        Given eu estou na pagina de login
        When preencho os campos com credenciais 'invalidas'
        And clico no botao 'Submit'
        Then exibe uma mensagem indicando 'insucesso'

    Scenario: Validacao do Login com credenciais validas
        Given eu estou na pagina de login
        When preencho os campos com credenciais 'validas'
        And clico no botao 'Submit'
        Then exibe uma mensagem indicando 'sucesso'