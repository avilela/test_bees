package stepDefinitions;

import api.ApiUtils;
import configUtils.PropertiesManager;
import hooks.Hook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import pom.LoginPO;
import pom.PrincipalPO;
import web.ActionUtils;

import static configUtils.ConfigFramework.getBrowser;
import static stepDefinitions.APIStepDefinition.*;

public class WebStepDefinition {

    PropertiesManager pm = new PropertiesManager("src/test/resources/properties/web.properties");
    String url = pm.getProps().getProperty("urlSistema");
    String user = pm.getProps().getProperty("usuariopadrao");
    String senha = pm.getProps().getProperty("senhaPadrao");

    @Given("que esteja na tela de cadastro")
    public void queEstejaNaTelaDeCadastro() {
        Hook.iniciarWeb();
        getBrowser().get(url);
        ActionUtils.click(getBrowser(), LoginPO.linkCadastro, 20);
        Assert.assertTrue("Não foi direcionado para a tela de cadastro!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.passConfirmInput, 15));
    }


    @When("Criar um usuario com dados validos")
    public void criarUmUsuarioComDadosValidos() {
        ActionUtils.fillInput(getBrowser(), LoginPO.emailInput, "teste" + ApiUtils.getRandomPass(5) + "@email.com", 15);
        ActionUtils.fillInput(getBrowser(), LoginPO.passInput, "teste123", 15);
        ActionUtils.fillInput(getBrowser(), LoginPO.passConfirmInput, "teste123", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);

    }

    @Then("espero que o usuario seja criado com sucesso")
    public void esperoQueOUsuarioSejaCriadoComSucesso() {
        Assert.assertTrue("Usuario não foi criado com sucesso!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Welcome to your storage"), 20));
    }

    @When("Criar um usuario sem informar email")
    public void criarUmUsuarioSemInformarEmail() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, "teste123", 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passConfirmInput, "teste123", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);
    }

    @Then("espero que um erro seja mostrado")
    public void esperoQueUmErroSejaMostrado() {
        Assert.assertTrue("Não foi mostrado erro ao não preencher o campo!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Erro"), 20));
    }

    @When("Criar um usuario sem informar senha e confirmacao de senha")
    public void criarUmUsuarioSemInformarSenhaEConfirmacaoDeSenha() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, "teste" + ApiUtils.getRandomPass(5) + "@email.com", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);
        Assert.assertFalse("Usuario foi criado com os erros de preenchimento!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Welcome to your storage"), 10));
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, "teste123", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);
    }

    @When("Criar um usuario sem formatacao de email")
    public void criarUmUsuarioSemFormatacaoDeEmail() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, "teste" + ApiUtils.getRandomPass(5), 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, "teste123", 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passConfirmInput, "teste123", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);
    }

    @Then("espero que o usuario nao seja criado")
    public void esperoQueOUsuarioNaoSejaCriado() {
        Assert.assertFalse("Usuario foi criado com os erros de preenchimento!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Welcome to your storage"), 10));

    }

    @When("Criar um usuario com senha menor que seis caracteres")
    public void criarUmUsuarioComSenhaMenorQueSeisCaracteres() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, "teste" + ApiUtils.getRandomPass(5) + "@email.com", 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, "teste", 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passConfirmInput, "teste", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnSignUp, 15);
    }

    @Given("que esteja na tela de login")
    public void queEstejaNaTelaDeLogin() {
        Hook.iniciarWeb();
        getBrowser().get(url);
    }

    @When("tentar efetuar login sem email")
    public void tentarEfetuarLoginSemEmail() {
        //ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, user, 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, senha, 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnLogin, 15);
    }

    @Then("espero que nao seja possivel entrar no sistema")
    public void esperoQueNaoSejaPossivelEntrarNoSistema() {
        Assert.assertFalse("Usuario conseguiu logar mesmo sem o campo obrigatorio!", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Welcome to your storage"), 10));

    }

    @When("tentar efetuar login sem senha")
    public void tentarEfetuarLoginSemSenha() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, user, 15);
        //ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, senha, 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnLogin, 15);
    }


    @When("tentar efetuar login com email sem formatacao")
    public void tentarEfetuarLoginComEmailSemFormatao() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, "teste", 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, senha, 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnLogin, 15);
    }

    @When("tentar efetuar login com senha invalida")
    public void tentarEfetuarLoginComSenhaInvalida() {
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, user, 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, "invalido", 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnLogin, 15);
    }

    @Given("que esteja logado no sistema")
    public void queEstejaLogadoNoSistema() {
        Hook.iniciarWeb();
        getBrowser().get(url);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.emailInput, user, 15);
        ActionUtils.fillInputjs(getBrowser(), LoginPO.passInput, senha, 15);
        ActionUtils.clickjs(getBrowser(), LoginPO.btnLogin, 15);
        Assert.assertTrue("Não foi possivel efetuar o login", ActionUtils.isElementoPresente(getBrowser(), LoginPO.textosNaTela("Welcome to your storage"), 10));

    }

    @When("criar um novo deposito com todos os campos")
    public void criarUmNovoDepositoComTodosOsCampos() {

        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaDepositos, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divDeposits, 15));

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewDeposit, 20);
        Assert.assertTrue("Não foi aberta a tela de novo deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewDeposit, 20));
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputName, "testeUI" + ApiUtils.getRandomNumber(2), 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputAddress, "rua te testeUI", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputCity, "Hortolandia", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputState, "São paulo", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputZipCode, "13388321", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateDeposit, 20);

    }

    @Then("espero que seja criado com sucesso")
    public void esperoQueSejaCriadoComSucesso() {
        Assert.assertTrue(ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully created."), 20));

    }

    @When("criar um novo deposito sem nenhum campo")
    public void criarUmNovoDepositoSemNenhumCampo() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaDepositos, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divDeposits, 15));

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewDeposit, 20);
        Assert.assertTrue("Não foi aberta a tela de novo deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewDeposit, 20));

        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateDeposit, 20);
    }

    @Then("espero que nao crie o deposito")
    public void esperoQueNaoCrieODeposito() {
        Assert.assertFalse("O sistema permitiu a criação do deposito sem nenhum campo preenchido!!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully created."), 20));
    }

    @When("editar um deposito com todos os campos")
    public void editarUmDepositoComTodosOsCampos() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaDepositos, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divDeposits, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowDeposit, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditDeposit, 20);

        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputName, "testeUI editar" + ApiUtils.getRandomNumber(2), 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputAddress, "rua te testeUI", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputCity, "Hortolandia", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputState, "São paulo", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputZipCode, "13388321", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateDeposit, 20);


    }

    @Then("espero que atualize o deposito com sucesso")
    public void esperoQueAtualizeODepositoComSucesso() {
        Assert.assertTrue(ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));
    }

    @When("editar um deposito com todos os campos retirados")
    public void editarUmDepositoComTodosOsCamposRetirados() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaDepositos, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divDeposits, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowDeposit, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditDeposit, 20);

        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputName, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputAddress, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputCity, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputState, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputZipCode, "", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateDeposit, 20);
    }

    @Then("espero que nao atualize o deposito")
    public void esperoQueNaoAtualizeODeposito() {
        Assert.assertFalse("O sistema permitiu atualizar o deposito com todos os campos retirados", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));
    }

    @When("entrar no deposito e excluir ele")
    public void entrarNoDepositoEExcluirEle() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaDepositos, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de deposito!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divDeposits, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowDeposit, 20);

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnDestroyDeposit, 20);

    }

    @Then("espero que seja excluido com sucesso")
    public void esperoQueSejaExcluidoComSucesso() {
        Assert.assertTrue("O sistema não excluiu o registro", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully destroyed"), 20));

    }

    @When("criar um novo item com todos os campos")
    public void criarUmNovoitemComTodosOsCampos() {

        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaItems, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divItems, 15));

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewItem, 20);
        Assert.assertTrue("Não foi aberta a tela de novo item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewItem, 20));
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemName, "teste item " + ApiUtils.getRandomNumber(2), 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputHeight, "22", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWidth, "2", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWeigh, "1", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateItem, 20);

    }


    @When("criar um novo item sem nenhum campo")
    public void criarUmNovoitemSemNenhumCampo() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaItems, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divItems, 15));

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewItem, 20);
        Assert.assertTrue("Não foi aberta a tela de novo item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewItem, 20));

        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateItem, 20);
    }

    @Then("espero que nao crie o item")
    public void esperoQueNaoCrieOitem() {
        Assert.assertFalse("O sistema permitiu a criação do item sem nenhum campo preenchido!!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully created."), 20));
    }

    @When("editar um item com todos os campos")
    public void editarUmitemComTodosOsCampos() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaItems, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divItems, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowItem, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditItem, 20);

        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemName, "teste item " + ApiUtils.getRandomNumber(2), 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputHeight, "22", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWidth, "2", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWeigh, "1", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateItem, 20);


    }

    @Then("espero que atualize o item com sucesso")
    public void esperoQueAtualizeOitemComSucesso() {
        Assert.assertTrue(ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));
    }

    @When("editar um item com todos os campos retirados")
    public void editarUmitemComTodosOsCamposRetirados() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaItems, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divItems, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowItem, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditItem, 20);

        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemName, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputHeight, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWidth, "", 15);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputWeigh, "", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateItem, 20);
    }

    @Then("espero que nao atualize o item")
    public void esperoQueNaoAtualizeOitem() {
        Assert.assertFalse("O sistema permitiu atualizar o item com todos os campos retirados", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));
    }

    @When("entrar no item e excluir ele")
    public void entrarNoitemEExcluirEle() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaItems, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divItems, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowItem, 20);

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnDestroyItem, 20);

    }


    @When("criar um novo inventario com todos os campos")
    public void criarUmNovoInventarioComTodosOsCampos() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaInventarios, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divInventory, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewInventory, 20);
        Assert.assertTrue("Não foi aberta a tela de novo inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewInventory, 20));
        By selDeposit = By.id("inventory_deposit_id");
        Select selectDeposit = new Select(getBrowser().findElement(selDeposit));
        By selItem = By.id("inventory_item_id");
        Select selectItem = new Select(getBrowser().findElement(selItem));
        selectDeposit.selectByVisibleText(getDepositName());
        selectItem.selectByVisibleText(getItemName());
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemCount, "12", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateInventory, 20);

    }

    @And("Que tenha um novo item e deposito para assimilar")
    public void queTenhaUmNovoItemEDepositoParaAssimilar() {
        criaDepositoGenerico();
        criaItemGenerico();
    }

    @When("criar um novo inventario sem nenhum campo")
    public void criarUmNovoInventarioSemNenhumCampo() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaInventarios, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divInventory, 15));

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnNewInventory, 20);
        Assert.assertTrue("Não foi aberta a tela de novo inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.formNewInventory, 20));
        ActionUtils.click(getBrowser(), PrincipalPO.btnCreateInventory, 20);

    }

    @Then("espero que nao crie o inventario")
    public void esperoQueNaoCrieOInventario() {
        Assert.assertFalse("O sistema permitiu a criação sem nenhum campo preenchido", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully created."), 20));
    }

    @When("editar um inventario com todos os campos")
    public void editarUmInventarioComTodosOsCampos() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaInventarios, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divInventory, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowinventory, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditInventory, 20);
        Assert.assertTrue("não foi possivel entrar na tela de edição", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.btnUpdateInventory, 20));

        By selDeposit = By.id("inventory_deposit_id");
        Select selectDeposit = new Select(getBrowser().findElement(selDeposit));
        By selItem = By.id("inventory_item_id");
        Select selectItem = new Select(getBrowser().findElement(selItem));
        selectDeposit.selectByVisibleText(getDepositName());
        selectItem.selectByVisibleText(getItemName());
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemCount, "12", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateInventory, 20);

    }

    @Then("espero que atualize o inventario com sucesso")
    public void esperoQueAtualizeOInventarioComSucesso() {
        Assert.assertTrue("O inventario nao atualizou", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));

    }

    @When("editar um inventario com todos os campos retirados")
    public void editarUmInventarioComTodosOsCamposRetirados() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaInventarios, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de inventario!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divInventory, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowinventory, 20);
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkEditInventory, 20);
        Assert.assertTrue("não foi possivel entrar na tela de edição", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.btnUpdateInventory, 20));

        By selDeposit = By.id("inventory_deposit_id");
        Select selectDeposit = new Select(getBrowser().findElement(selDeposit));
        By selItem = By.id("inventory_item_id");
        Select selectItem = new Select(getBrowser().findElement(selItem));
        selectDeposit.selectByIndex(1);
        selectItem.selectByIndex(1);
        ActionUtils.fillInputjs(getBrowser(), PrincipalPO.inputItemCount, "", 15);
        ActionUtils.click(getBrowser(), PrincipalPO.btnUpdateInventory, 20);
    }

    @Then("espero que nao atualize o inventario")
    public void esperoQueNaoAtualizeOInventario() {
        Assert.assertFalse("O inventario atualizou com a retirada dos campos", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.textosNaTela("was successfully updated"), 20));
    }


    @When("entrar no inventario e excluir ele")
    public void entrarNoInventarioEExcluirEle() {
        ActionUtils.clickjs(getBrowser(), PrincipalPO.abaInventarios, 20);
        Assert.assertTrue("Não foi possivel entrar na tela de item!", ActionUtils.isElementoPresente(getBrowser(), PrincipalPO.divInventory, 15));
        ActionUtils.clickjs(getBrowser(), PrincipalPO.linkShowinventory, 20);

        ActionUtils.clickjs(getBrowser(), PrincipalPO.btnDestroyInventory, 20);
    }
}
