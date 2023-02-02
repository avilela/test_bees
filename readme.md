# Desafio AbinBev

## Projeto desenvolvido em JAVA para testar os requisitos solicitados no desafio de automação da Ambev.

### Casos de teste

- Os casos de teste cobertos na automação podem ser acessados no diretorio: .../AmbevDesafio/CasosDeTeste 

### Features e tags

- '@TesteCompleto' - executa todos os testes do projeto
- [x] APIs '@TesteApis'
- [x] Funcional WEB '@TesteWeb'

### Preparação de maquina para execução:

#### Como pré condição para execução do projeto, serão necessarias as instalações dos softwares abaixo, com configuração de variaveis de sistema:

- Maven 3.8.4, link de apoio: https://dicasdejava.com.br/como-instalar-o-maven-no-windows/
- java JDK 11, link de apoio: https://techexpert.tips/pt-br/windows-pt-br/instalar-java-jdk-no-windows/
- Atualmente o script esta executando no Google Chrome 109, porem no diretorio ../AmbevDesafio/drivers estão as versões 103 e 110, caso precise trocar de chromeDriver para adequar a sua maquina, troque o arquivo "chromedriver.exe"


### Instruções de execução:

- Abrir a aplicação pela IDE preferida, entrar na classe ...test/java/runners/RunTest.java, trocar a tag para o teste desejado;
- Abrir o CMD na raiz do projeto (/AmbevDesafio) onde o arquivo pom.xml é visivel, e executar a linha de comando a seguir, trocando a tag conforme o teste desejado: mvn test -Dtest=RunTest -Dcucumber.options="--tags @TesteCompleto"
- Os relatorios estão sendo gerados no diretorio: .../WorkspaceDesafio/AmbevDesafio/target/cucumber-reports
