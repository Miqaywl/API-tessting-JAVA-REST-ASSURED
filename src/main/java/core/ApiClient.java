package core;

import config.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequest() {

        if (requestSpec == null) {

            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigLoader.getInstance().getBaseUrl())        // Base URL from config
                    .setContentType(ContentType.JSON)                          // Default body type
                    .setAccept(ContentType.JSON)                               // Default accept header
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .setRelaxedHTTPSValidation()                               // Avoid SSL errors
                    .setConfig(RestAssured.config()
                            .httpClient(HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", ConfigLoader.getInstance().getTimeout())
                                    .setParam("http.socket.timeout", ConfigLoader.getInstance().getTimeout())))
                    .log().all()                                               // Log request details
                    .build();
        }

        return RestAssured.given().spec(requestSpec);
    }
}
