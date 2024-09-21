package services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import specifications.Specifications;

import static io.restassured.RestAssured.given;

public class UserService {

    private final String baseUri;

    public UserService(String baseUri){
        this.baseUri = baseUri;
    }

    @Step("Регистрация пользователя с email: {user.email}")
    public Response registerUser(User user){
        return given()
                .spec(Specifications.requestSpec(baseUri))
                .body(user)
                .when()
                .post("/api/register");


    }

    @Step("Получение списка пользователей страницы: {page}")
    public Response listUsers(int page){
        return given()
                .spec(Specifications.requestSpec(baseUri))
                .when()
                .get("/api/users?page=" + page);
    }

    @Step("Удаление пользователя с ID: {userId}")
    public Response deleteUser(int userId){
        return given()
                .spec(Specifications.requestSpec(baseUri))
                .when()
                .delete("/api/users/" + userId);
    }

    @Step("Обновление пользователя с ID: {userId}")
    public Response updateUser(int userId, User user){
        return given()
                .spec(Specifications.requestSpec(baseUri))
                .body(user)
                .when()
                .patch("/api/users/" + userId);
    }
}
