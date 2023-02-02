package stepDefinitions;

import api.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import configUtils.PropertiesManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import okhttp3.*;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APIStepDefinition {
    private static String payloadVolatil;
    private static String endpointVolatil;
    public static String itemName;
    public static String depositName;

    public static String getItemName() {
        return itemName;
    }

    public static void setItemName(String itemName) {
        APIStepDefinition.itemName = itemName;
    }

    public static String getDepositName() {
        return depositName;
    }

    public static void setDepositName(String depositName) {
        APIStepDefinition.depositName = depositName;
    }

    public static String getPayloadVolatil() {
        return payloadVolatil;
    }

    public static void setPayloadVolatil(String payloadVolatil) {
        APIStepDefinition.payloadVolatil = payloadVolatil;
    }

    public static String getEndpointVolatil() {
        return endpointVolatil;
    }

    public static void setEndpointVolatil(String endpointVolatil) {
        APIStepDefinition.endpointVolatil = endpointVolatil;
    }


    private static String registroID;

    public static String getRegistroID() {
        return registroID;
    }

    public static void setRegistroID(String registroID) {
        APIStepDefinition.registroID = registroID;
    }


    @When("^executar uma requisicao POST$")
    public void executarUmaRequisicaoPOST() {
        ApiUtils.requestPOST();
    }

    @When("^executar uma requisicao GET$")
    public void executarUmaRequisicaoGET() {
        ApiUtils.requestGET();
    }

    @When("executar uma requisicao PATCH pelo rest-assured com tempo maximo de {string} segundos de resposta")
    public void executarUmaRequisicaoPATCHPeloRestAssuredComTempoMaximoDeSegundosDeResposta(String arg0) {
        ApiUtils.requestRAPatchComTempoLimite(arg0);
    }

    @When("executar uma requisicao PUT pelo rest-assured com tempo maximo de {string} segundos de resposta")
    public void executarUmaRequisicaoPUTPeloRestAssuredComTempoMaximoDeSegundosDeResposta(String arg0) {
        ApiUtils.requestRAPutComTempoLimite(arg0);
    }

    @When("executar uma requisicao DELETE pelo rest-assured com tempo maximo de {string} segundos de resposta")
    public void executarUmaRequisicaoDELETEPeloRestAssuredComTempoMaximoDeSegundosDeResposta(String arg0) {
        ApiUtils.requestRADELETEComTempoLimite(arg0);
    }

    @Then("^espero receber um response code \"([^\"]*)\"$")
    public void esperoReceberUmResponseCode(String code) {
        System.out.println(ApiUtils.getResponseBodyJson());
        ApiUtils.validaResponseCode(code);
    }


    @Given("^que seja definido o payload \"([^\"]*)\"$")
    public void queSejaDefinidoOPayload(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payload = pm.getProps().getProperty(arg0);
        ApiUtils.setBodyJson(payload);
    }


    @Given("que seja definido o payload {string}, aleatorizando os campos {string}, {string}, {string} e {string}")
    public void queSejaDefinidoOPayloadAleatorizandoOsCamposE(String payload, String arg1, String arg2, String arg3, String arg4) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String body = pm.getProps().getProperty(payload);

        JSONObject jsonPayload = new Gson().fromJson(body, JSONObject.class);

        if (!arg1.isEmpty()) {
            jsonPayload.accumulate(arg1, ApiUtils.getRandomPass(10));
        }
        if (!arg2.isEmpty()) {
            jsonPayload.accumulate(arg2, ApiUtils.getRandomPass(10));
        }
        if (!arg3.isEmpty()) {
            jsonPayload.accumulate(arg3, ApiUtils.getRandomPass(10));
        }
        if (!arg4.isEmpty()) {
            jsonPayload.accumulate(arg4, ApiUtils.getRandomPass(10));
        }

        ApiUtils.setBodyJson(jsonPayload.toString());
    }

    @Given("que seja definido o payload {string}, aleatorizando os campos ja colocados {string}")
    public void queSejaDefinidoOPayloadAleatorizandoOsCamposJaColocados(String payload, String arg1) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String body = pm.getProps().getProperty(payload);
        String bodytratado = body.replaceAll(arg1, ApiUtils.getRandomPass(10));
        JsonObject jsonPayload = new Gson().fromJson(bodytratado, JsonObject.class);
        if (payload.contains("postInventoriesCompleto")) {
            String idDeposito = criaDepositoGenerico();
            String idItem = criaItemGenerico();
            jsonPayload.addProperty("item_id", Integer.valueOf(idItem));
            jsonPayload.addProperty("deposit_id", Integer.valueOf(idDeposito));
            ApiUtils.setBodyJson(jsonPayload.toString());
        } else {
            ApiUtils.setBodyJson(bodytratado);
        }

    }


    @Given("que seja definido o payload {string}, aleatorizando os campos ja colocados {string} e retirando o campo {string}")
    public void queSejaDefinidoOPayloadAleatorizandoOsCamposJaColocadosERetirandoOCampo(String payload, String stringAleatoria, String campoRetirado) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String body = pm.getProps().getProperty(payload);
        String bodytratado = body.replace(stringAleatoria, ApiUtils.getRandomPass(10));
        JsonObject jsonPayload = new Gson().fromJson(bodytratado, JsonObject.class);
        if (payload.contains("postInventoriesCompleto")) {
            String idDeposito = criaDepositoGenerico();
            String idItem = criaItemGenerico();
            jsonPayload.addProperty("item_id", Integer.valueOf(idItem));
            jsonPayload.addProperty("deposit_id", Integer.valueOf(idDeposito));
        }
        System.out.println("body antes da remoção: " + jsonPayload.toString());
        jsonPayload.remove(campoRetirado);
        String bodyretirada = jsonPayload.toString();
        System.out.println("Body apos a remoção do campo: " + bodyretirada);

        ApiUtils.setBodyJson(bodyretirada);
    }


    @Then("^espero que o objeto do body \"([^\"]*)\" venha com o valor de \"([^\"]*)\"$")
    public void esperoQueOObjetoDoBodyVenhaComOValorDe(String key, String value) {
        ApiUtils.validaResponseBody(key, value);
    }

    @Then("^espero que o objeto do body \"([^\"]*)\" venha com a estrutura do regex \"([^\"]*)\"$")
    public void esperoQueOObjetoDoBodyVenhaComAEstruturaDoRegex(String key, String valueRegex) {
        ApiUtils.validaResponseBodyComRegex(key, valueRegex);
    }

    @Then("^espero que o body response array, objeto \"([^\"]*)\" venha com o valor \"([^\"]*)\"$")
    public void esperoQueOBodyResponseArrayObjetoVenhaComOValor(String key, String value) throws IOException {
        ApiUtils.validaResponseBodyArray(key, value);
    }

    @Given("^que seja definido o endpoint como \"([^\"]*)\"$")
    public void queSejaDefinidoOEndpointComo(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String endpoint = pm.getProps().getProperty(arg0);
        ApiUtils.setEndpoint(endpoint);
    }

    @Given("^que seja definido o payload \"([^\"]*)\", modificando os valores \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" e \"([^\"]*)\" para \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void queSejaDefinidoOPayloadModificandoOsValoresEPara(String payload, String campo1, String campo2, String campo3, String campo4, String valor1, String valor2, String valor3, String valor4) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payloadJson = pm.getProps().getProperty(payload); // identificando o payload em formato string (hunter)
        JSONObject acl_payload = new JSONObject(payloadJson); // estanciando o objeto json para parametrizar o valor
        acl_payload.put(campo1, valor1); // parametrizando os valores
        acl_payload.put(campo2, valor2); // parametrizando os valores
        acl_payload.put(campo3, valor3); // parametrizando os valores
        acl_payload.put(campo4, valor4); // campo adicionado (caso não exista esse campo no payload, então ele irá adicionar! caso exista, irá mudar valor
        // System.out.println(acl_payload); // imprimir para testar o método adicionado.
//        payloadJson = payloadJson.replaceAll(campo1,valor1);
//        if(!valor2.isEmpty()){
//            payloadJson = payloadJson.replaceAll(campo2,valor2);
//        }
//        if(!valor3.isEmpty()){
//            payloadJson = payloadJson.replaceAll(campo3,valor3);
//        }
//        ApiUtils.setBodyJson(payloadJson);
    }

    @Given("que seja definido o payload {string}, modificando o campo {string} para o valor {string}")
    public void queSejaDefinidoOPayloadModificandoOCampoParaOValor(String payload, String campo1, String valor1) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        String payloadJson = pm.getProps().getProperty(payload); // identificando o payload em formato string (hunter)
        JsonObject payloadalterado = new Gson().fromJson(payloadJson, JsonObject.class); // estanciando o objeto json para parametrizar o valor
        payloadalterado.addProperty(campo1, valor1); // parametrizando os valores
        System.out.println("payload: " + payloadalterado);
        ApiUtils.setBodyJson(payloadalterado.toString());
    }

    @Given("^que seja adicionado os parametros \"([^\"]*)\" no endpoint \"([^\"]*)\"$")
    public void queSejaAdicionadoOsParametrosNoEndpoint(String param, String endpoint) throws Throwable {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/acl.properties");
        String uri = pm.getProps().getProperty(endpoint);
        uri = (uri + param);
        ApiUtils.setEndpoint(uri);
    }

    @When("^executar uma requisicao GET pelo rest-Assured$")
    public void executarUmaRequisicaoGETPeloRestAssured() {
        ApiUtils.requestRAGet();
    }

    @When("^executar uma requisicao POST pelo rest-assured$")
    public void executarUmaRequisicaoPOSTPeloRestAssured() {
        ApiUtils.requestRAPost();
    }

    @Then("^espero que a body response em JSON Array possua o objeto \"([^\"]*)\" e venha com o valor \"([^\"]*)\"$")
    public void esperoQueABodyResponseEmJSONArrayPossuaOObjetoEVenhaComOValor(String key, String value) throws Throwable {
        ApiUtils.validaResponseBodyComJSONArray(key, value);
    }

    @Then("^espero que a body response possua \"([^\"]*)\" \"([^\"]*)\", \"([^\"]*)\" \"([^\"]*)\" e \"([^\"]*)\" \"([^\"]*)\"$")
    public void esperoQueABodyResponsePossuaE(String key1, String value1, String key2, String value2, String key3, String value3) throws Throwable {
        ApiUtils.validaResponseBodyChavesValores(key1, value1, key2, value2, key3, value3);
    }


    @When("executar uma requisicao POST pelo rest-assured com tempo maximo de {string} segundos de resposta")
    public void executarUmaRequisicaoPOSTPeloRestAssuredComTempoMaximoDeSegundosDeResposta(String arg0) {
        ApiUtils.requestRAPostComTempoLimite(arg0);
    }

    @When("executar uma requisicao GET pelo rest-assured com tempo maximo de {string} segundos de resposta")
    public void executarUmaRequisicaoGETPeloRestAssuredComTempoMaximoDeSegundosDeResposta(String arg0) {
        ApiUtils.requestRAGetComTempoLimite(arg0);
    }


    public static String criaDepositoGenerico() {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        PropertiesManager pmE = new PropertiesManager("src/test/resources/properties/api.properties");
        String body = pm.getProps().getProperty("postDepositsCompleto");

        body = body.replace("RandomNum", ApiUtils.getRandomPass(5));


        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given().contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json").body(body).when()
                .post(pmE.getProps().getProperty("endpointDeposits"))
                .then().extract().response();

        setDepositName(response.jsonPath().getString("name"));
        return response.jsonPath().getString("id");

    }

    public static String criaItemGenerico() {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        PropertiesManager pmE = new PropertiesManager("src/test/resources/properties/api.properties");

        String body = pm.getProps().getProperty("postItemsCompleto");

        body = body.replace("RandomNum", ApiUtils.getRandomPass(5));

        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given().contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json").body(body)
                .when().post(pmE.getProps().getProperty("endpointItems"))
                .then().extract().response();

        setItemName(response.jsonPath().getString("name"));
        return response.jsonPath().getString("id");

    }

    @Given("que eu tenha um registro {string} criado")
    public void queEuTenhaUmRegistroCriado(String tipoRegistro) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/payloads.properties");
        PropertiesManager pmE = new PropertiesManager("src/test/resources/properties/api.properties");

        String idItem;
        String idDeposito;

        if (tipoRegistro.contains("deposito")) {
            setPayloadVolatil(pm.getProps().getProperty("postDepositsCompleto"));
            setEndpointVolatil(pmE.getProps().getProperty("endpointDeposits"));
        } else if (tipoRegistro.contains("items")) {
            setPayloadVolatil(pm.getProps().getProperty("postItemsCompleto"));
            setEndpointVolatil(pmE.getProps().getProperty("endpointItems"));
        } else if (tipoRegistro.contains("inventario")) {
            JsonObject jsonInventario = new Gson().fromJson(pm.getProps().getProperty("postInventoriesCompleto"), JsonObject.class);
            idDeposito = criaDepositoGenerico();
            idItem = criaItemGenerico();
            jsonInventario.addProperty("item_id", Integer.valueOf(idItem));
            jsonInventario.addProperty("deposit_id", Integer.valueOf(idDeposito));

            setPayloadVolatil(jsonInventario.toString());
            setEndpointVolatil(pmE.getProps().getProperty("endpointInventories"));
        }

        String bodyJson = getPayloadVolatil().replace("RandomNum", ApiUtils.getRandomPass(5));

        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given().contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json").body(bodyJson).when().post(getEndpointVolatil()).then().extract().response();

        setRegistroID(response.jsonPath().getString("id"));

    }

    @Given("que seja definido o endpoint como {string} com parametro de id valido")
    public void queSejaDefinidoOEndpointComoComParametroDeIdValido(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String endpoint = pm.getProps().getProperty(arg0);
        ApiUtils.setEndpoint(endpoint + "/" + getRegistroID());
        System.out.println("endpoint: " + ApiUtils.getEndpoint());
    }


    @And("que venha um erro {string} no response body")
    public void queVenhaUmErroNoResponseBody(String arg0) {
        assert ApiUtils.getResponseRa() != null;
        String responseBody = ApiUtils.getResponseRa().getBody().prettyPrint();
        System.out.println("Response obtido: " + responseBody);
        Assert.assertTrue("Nenhum erro mostrado no body response! ", responseBody.contains(arg0));
    }

    @Given("que seja definido o endpoint como {string} com parametro de id invalido")
    public void queSejaDefinidoOEndpointComoComParametroDeIdInvalido(String arg0) {
        PropertiesManager pm = new PropertiesManager("src/test/resources/properties/api.properties");
        String endpoint = pm.getProps().getProperty(arg0);
        ApiUtils.setEndpoint(endpoint + "/" + ApiUtils.getRandomNumber(6));
    }


}