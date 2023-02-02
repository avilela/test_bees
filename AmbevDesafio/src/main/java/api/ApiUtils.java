package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import okhttp3.*;
import org.codehaus.groovy.util.ListHashMap;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static Response response;
    public static String bodyJson;
    public static String url;
    public static String recurso;
    public static String endpoint;
    public static String authorization;

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        ApiUtils.response = response;
    }


    public static String getBodyJson() {
        return bodyJson;
    }

    public static void setBodyJson(String bodyJson) {
        ApiUtils.bodyJson = bodyJson;
    }

    public static String getAuthorization() {
        return authorization;
    }

    public static void setAuthorization(String authorization) {
        ApiUtils.authorization = authorization;
    }


    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ApiUtils.url = url;
    }

    public static String getRecurso() {
        return recurso;
    }

    public static void setRecurso(String recurso) {
        ApiUtils.recurso = recurso;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        ApiUtils.endpoint = endpoint;
    }

    //metodo para geração de senha aleatoria, para ser usado na tag externalID do body
    public static String getRandomPass(int len) {
        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++)
            senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static io.restassured.response.Response responseRa;

    @Nullable
    public static io.restassured.response.Response getResponseRa() {
        return responseRa;
    }

    public static void setResponseRa(io.restassured.response.Response responseRa) {
        ApiUtils.responseRa = responseRa;
        ApiUtils.setResponseBodyJson(responseRa.getBody().asString());
    }


    public static String responseBodyJson;

    public static String getResponseBodyJson() {
        return responseBodyJson;
    }

    public static void setResponseBodyJson(String responseBodyJson) {
        ApiUtils.responseBodyJson = responseBodyJson;
    }


    public static ListHashMap<String, String> getTicketFieldValue() {
        return ticketFieldValue;
    }

    public static void putTicketFieldValue(String field, String value) {
        ticketFieldValue.put(field, value);
    }

    private static ListHashMap<String, String> ticketFieldValue = new ListHashMap<>();

    public static Response getApplicationResponse() {
        return applicationResponse;
    }

    public static void setApplicationResponse(Response applicationResponse) {
        ApiUtils.applicationResponse = applicationResponse;
    }

    private static Response applicationResponse;


    public static String getRandomLetras(int len) {
        char[] chart = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++)
            senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static String getRandomNumber(int len) {
        char[] chart = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++)
            senha[x] = chart[rdm.nextInt(chartLenght)];
        return new String(senha);
    }

    public static void criarBody(List<String> bodyKey, List<String> bodyValue) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (int i = 0; i < bodyKey.size(); i++) {
                jsonObject.put(bodyKey.get(i), bodyValue.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setBodyJson(jsonObject.toString());
    }

    /**
     * Realiza uma requisição do tipo OKHTTP usando os valores pre definidos nos steps de given
     * <p>
     * Metodo POST
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestPOST() {
        ApiUtils.bypassTLSCertificateValidation();
        OkHttpClient client = ApiUtils.getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, getBodyJson());
        Request request = new Request.Builder()
                .url(getEndpoint())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", getAuthorization())
                .build();
        try {
            setResponse(client.newCall(request).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza uma requisição do tipo OKHTTP usando os valores pre definidos nos steps de given
     * <p>
     * Metodo GET
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestGET() {
        ApiUtils.bypassTLSCertificateValidation();
        OkHttpClient client = ApiUtils.getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url(getEndpoint())
                .get()
                .addHeader("Authorization", getAuthorization())
                .addHeader("Accept", "application/json")
                .build();
        try {
            setResponse(client.newCall(request).execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * <p>
     * Metodo GET
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestRAGet() {
        RestAssured.useRelaxedHTTPSValidation();
        ApiUtils.bypassTLSCertificateValidation();
        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON).header("Authorization", getAuthorization()).header("Accept", "application/json")
                .when()
                .get(getEndpoint())
                .then()
                .extract().response();
        setResponseRa(response);
    }

    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * <p>
     * Metodo POST
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestRAPost() {
        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .body(getBodyJson())
                .when()
                .post(getEndpoint())
                .then()
                .extract().response();
        setResponseRa(response);
    }


    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * <p>
     * Metodo POST
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestRAPostComTempoLimite(String tempo) {
        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .body(getBodyJson())
                .when()
                .post(getEndpoint())
                .then().time(Matchers.lessThan(Long.parseLong(tempo)), TimeUnit.SECONDS)
                .extract().response();
        setResponseRa(response);
    }


    public static void requestRAPatchComTempoLimite(String tempo) {
        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .body(getBodyJson())
                .when()
                .patch(getEndpoint())
                .then().time(Matchers.lessThan(Long.parseLong(tempo)), TimeUnit.SECONDS)
                .extract().response();
        setResponseRa(response);
    }


    public static void requestRAPutComTempoLimite(String tempo) {
        RestAssured.useRelaxedHTTPSValidation();

        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .body(getBodyJson())
                .when()
                .put(getEndpoint())
                .then().time(Matchers.lessThan(Long.parseLong(tempo)), TimeUnit.SECONDS)
                .extract().response();
        setResponseRa(response);
    }
    /**
     * Realiza uma requisição do tipo rest-assured usando os valores pre definidos nos steps de given
     * <p>
     * Metodo POST
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void requestRAGetComTempoLimite(String tempo) {
        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given()
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .when()
                .get(getEndpoint())
                .then().time(Matchers.lessThan(Long.parseLong(tempo)), TimeUnit.SECONDS)
                .extract().response();
        setResponseRa(response);
    }
    public static void requestRADELETEComTempoLimite(String tempo) {
        RestAssured.useRelaxedHTTPSValidation();
        io.restassured.response.Response response = given()
                //.header("Authorization", getAuthorization())
                .header("Accept", "application/json")
                .when()
                .delete(getEndpoint())
                .then().time(Matchers.lessThan(Long.parseLong(tempo)), TimeUnit.SECONDS)
                .extract().response();
        setResponseRa(response);
    }

    /**
     * Realiza a validação de campos especificos do body response, atraves do mapeamento json e os compara com valores unicos
     * <p>
     * Só realiza a validação de responses do tipo OKHTTP3*
     *
     * @param key   - objeto a ser procurado
     * @param value - valor a ser usado na validacao
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void validaResponseBody(String key, String value) {
        Assert.assertNotNull(getResponse().body());
        JSONObject responseJson = new JSONObject(getResponse().body());
        String valueKey = responseJson.getString(key);
        Assert.assertEquals(value, valueKey);
    }

    public static void validaResponseBodyComJSONArray(String key, String value) {
        boolean checkValues = false;
        Assert.assertNotNull(getResponse().body());
        JSONArray jsonArray = new JSONArray(getResponseBodyJson());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = new JSONObject(jsonArray.get(i).toString());
            if (jsonObject.get(key).toString().equals(value)) checkValues = true;
        }
        Assert.assertTrue(checkValues);
    }

    public static void validaResponseBodyChavesValores(String key1, String value1, String key2, String value2, String key3, String value3) {
        boolean checkValue1 = false;
        boolean checkValue2 = false;
        boolean checkValue3 = false;
        Assert.assertNotNull(getResponse().body());
        JSONArray jsonArray = new JSONArray(getResponseBodyJson());
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            String responseElement = jsonArray.get(i).toString();
            if (!(checkValue1 && checkValue2 && checkValue3)) {
                checkValue1 = responseElement.contains("\"" + key1 + "\":\"" + value1 + "\"") || responseElement.contains("\"" + key1 + "\":" + value1 + "");
                checkValue2 = responseElement.contains("\"" + key2 + "\":\"" + value2 + "\"") || responseElement.contains("\"" + key2 + "\":" + value2 + "") && checkValue1;
                checkValue3 = responseElement.contains("\"" + key3 + "\":\"" + value3 + "\"") || responseElement.contains("\"" + key3 + "\":" + value3 + "") && checkValue3;
            }
        }
        Assert.assertTrue("1º valor diferente do esperado!", checkValue1);
        Assert.assertTrue("2º valor diferente do esperado!", checkValue2);
        Assert.assertTrue("3º valor diferente do esperado!", checkValue3);
    }

    public static void validaResponseBodyArray(String key, String valueRegex) {
        Assert.assertNotNull(getResponse().body());
        JSONArray responseJson = new JSONArray(getResponse().body());
        for (int i = 0; i < responseJson.length(); i++) {
            JSONObject arrayOBJ = responseJson.getJSONObject(i);
            String valueKey = arrayOBJ.getString(key);
            Assert.assertThat(valueKey, Matchers.<String>equalTo(String.valueOf(Pattern.compile("ˆ" + valueRegex + "$"))));
        }
    }

    /**
     * Realiza a validação de campos especificos do body response, atraves do mapeamento json e os compara com Regex
     * <p>
     * Só realiza a validação de responses do tipo Rest-Assured*
     *
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void validaResponseCode(String code) {
        String statuscode = null;
        if (getResponse() != null) {
            statuscode = String.valueOf(getResponse().code());
            System.out.println("code Rest assured: " + statuscode);
        }
        String statuscodeRa = null;
        if (getResponseRa() != null) {
            statuscodeRa = String.valueOf(getResponseRa().statusCode());
            System.out.println("code Rest assured: " + statuscodeRa);
        }
        if (statuscodeRa == null) {
            Assert.assertEquals("O Response code foi diferente do esperado!", code, statuscode);
            if (!code.equals(statuscode)) {
                System.out.println("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + statuscode + ";");
                System.out.println("body-> " + getResponse().body().toString());
                // ExtentReports.appendToReport("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + statuscode + ";");
                // ExtentReports.appendToReport("body-> " + getResponse().body().toString());
            }
        } else {
            Assert.assertEquals("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + statuscodeRa + ";", code, statuscodeRa);
            if (!code.equals(statuscodeRa)) {
                System.out.println("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + statuscodeRa + ";");
                System.out.println("body-> " + getResponseRa().getBody().prettyPrint());
                // ExtentReports.appendToReport("O Response code foi diferente do esperado!, esperado: " + code + "; recebido da API: " + statuscodeRa + ";");
                // ExtentReports.appendToReport("body-> " + getResponseRa().getBody().prettyPrint());
            }
        }
    }

    public static void validaResponseHeaderList(List<String> headerKey, List<String> headerValue) {
        for (int i = 0; i < headerKey.size(); i++) {
            String valorHeader = getResponse().header(headerKey.get(i));
            Assert.assertEquals(headerValue.get(i), valorHeader);
        }
    }

    public static void validaResponseHeader(String headerKey, String headerValue) {
        String valorHeader = getResponse().header(headerKey);
        Assert.assertEquals(headerValue, valorHeader);
    }

    /**
     * Realiza a validação de campos especificos do body response, atraves do mapeamento json e os compara com Regex
     * <p>
     * Só realiza a validação de responses do tipo Rest-Assured*
     *
     * @param key   - objeto a ser procurado - exemplo: data.email, ou em caso de arrays: data.leads[0].email
     * @param value - Regex a ser usado na validacao, sempre usando '^$', exemplo: ^.*$, ^\d*$
     * @return void
     * @author ricardo.vaz.d.junior
     */
    public static void validaResponseBodyComRegex(String key, String value) {
        String data = getResponseRa().jsonPath().getString(key);
//        System.out.println(data);
        Assert.assertThat("O registro veio diferente do padrão documentado", data, RegexMatcher.matchesRegex(value));

    }

    public static void bypassTLSCertificateValidation() {
        TrustManager[] trustAllCerts = trustManager();
        SSLContext sc = sslContext(trustAllCerts);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier validHosts = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(validHosts);
    }


    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    static {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        OkHttpClient.Builder okBuilder = new OkHttpClient().newBuilder();
        TrustManager[] trustManager = trustManager();
        SSLContext sslContext = sslContext(trustManager);
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        okBuilder.sslSocketFactory(socketFactory, (X509TrustManager) trustManager[0]);
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        httpClient = okBuilder.build();
    }

    public static OkHttpClient getOhttpClient() {
        return httpClient;
    }


    private static TrustManager[] trustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]
                {new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]
                                {};
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }};
        return trustAllCerts;
    }

    private static final OkHttpClient httpClient;

    private static SSLContext sslContext(TrustManager[] trustAllCerts) {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return sc;
    }


}