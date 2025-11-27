package core;

import io.restassured.specification.RequestSpecification;

public class RequestFactory {

    public RequestSpecification getRequest() {
        return ApiClient.getRequest();
    }

    public RequestSpecification getRequestWithBody(Object body) {
        return ApiClient.getRequest()
                .body(body);
    }

    public RequestSpecification getRequestWithParam(String key, String value) {
        return ApiClient.getRequest()
                .queryParam(key, value);
    }

    public RequestSpecification getRequestWithPathParam(String key, String value) {
        return ApiClient.getRequest()
                .pathParam(key, value);
    }

    public RequestSpecification getAuthorizedRequest(String token) {
        return ApiClient.getRequest()
                .header("Authorization", "Bearer " + token);
    }
}