package com.orgname.framework.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiDriverFactory {

    public Response getMethod(String path, String queryParam){
        return RestAssured
                .given()
                .when()
                .get(path+queryParam)
                .then()
                .extract().response();
    }
    public String printOutput(Response response){
        return response.prettyPrint();
    }

    public Response postMethod(String path, String requestBody) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(path)
                .then()
                .extract().response();
    }
}