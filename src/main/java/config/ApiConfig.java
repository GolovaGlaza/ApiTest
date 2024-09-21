package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class ApiConfig {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.filters(new AllureRestAssured());

    }
}
