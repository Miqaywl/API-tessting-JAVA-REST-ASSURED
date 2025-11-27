package core;

import config.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static RequestSpecification requestSpec;

    public static RequestSpecification getRequest() {

        if (requestSpec == null) {

            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigLoader.getInstance().getBaseUrl())
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .setRelaxedHTTPSValidation("TLS")
                    .addFilter(new RequestLoggingFilter())     // logs request
                    .addFilter(new ResponseLoggingFilter())    // logs response
                    .setConfig(RestAssured.config()
                            .httpClient(HttpClientConfig.httpClientConfig()
                                    .setParam("http.connection.timeout", ConfigLoader.getInstance().getTimeout())
                                    .setParam("http.socket.timeout", ConfigLoader.getInstance().getTimeout())))
                    .build();
        }

        return RestAssured.given().spec(requestSpec);
    }
}