package specifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec(String baseUri){
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .build();
    }

    public static ResponseSpecification responseSpecSuccess(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static ResponseSpecification responseSpecError(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
}
