# Testes Web e API automatizados usando Python, Requests, Behave e Selenium

### Requisitos técnicos para automação:
- Teste a interface do usuário e a API
- Use o padrão de objeto de página ou padrões relativos de sua preferência.
- Use BDD para qualquer cenário que você queira fazer
- Você pode escolher qualquer linguagem de programação, recomendamos que você use o Python 3.6+, MAS É COM VOCÊ
- Por favor, siga o estilo de código para o seu idioma escolhido (exemplo: PEP's para python).
- Descreva como executar seu código em README.MD
- Gere um relatório dos resultados dos seus testes

### Foi utilizado:
- Python 3.9.4
- Behave 1.2.6
- Faker 12.3.3
- Faker-food 0.1.0
- Requests 2.26.0
- Selenium 3.141.0
- ChromeDriver

### Procedimento para rodar a automação

- Instalar os pacotes necessários através do arquivo requeriments.txt, por meio do comando: 	```
 pip install -r requirements.txt	```
- É necessário baixar o 	``` chromedriver ```, te-lo nas variáveis de ambiente e adicionar o caminho no arquivo ``` environment.py``` no método ```browser_chrome```
- Para rodar toda a automação, utilize o comando ```behave``` estando dentro do diretório ```integrations```

#### Rodando os cenários da API:
| api_deposits.feautre  | api_inventory.feature| api_items.feature |
| ------------- | ------------- | ------------- |
|  ```behave .\features\api_deposits.feature```  |  ```behave .\features\api_inventory.feature``` |  ```behave .\features\api_items.feature```     |

#### Rodando os cenários do Site:
| deposits.feature  | inventory.feature| items.feature | login.feature |
| ------------ | ------------ | ------------ | ------------- |
|  ```behave .\features\deposits.feature     ```  |  ```behave .\features\inventory.feature``` |  ```behave .\features\items.feature```     |  ```behave .\features\login.feature```     |

#### Rodando todos os cenários:
Rodar o comando```behave```.

### Como gerar relatório com o resultado dos testes
- Para este projeto foi considerada utilização do comando ```--junit```. Ao adicioná-lo ao final do comando de rodar o behave, ele irá gerar um relatório .xml contendo o resultado dos testes. 
- Exemplo de comando:  ```behave .\features\deposits.feature --junit  ``` 

### Como gerar relatório com o resultado dos testes
- Já foi gerado uma pasta com os relatórios em: ``` .\integrations\reports```. 