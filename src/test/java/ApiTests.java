import config.ApiConfig;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
import services.UserService;
import specifications.Specifications;
import utils.DateUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specifications.Specifications.responseSpecError;
import static specifications.Specifications.responseSpecSuccess;

@Epic("API Testing")
@Feature("User Management")
public class ApiTests extends ApiConfig {

    UserService userService = new UserService(RestAssured.baseURI);


    @Test
    @Story("Регистрация пользователя")
    @Description("Успешная регистрация пользователя с валидными данными")
    @Step("Проверка успешной регистрации")
    public void successfulRegistrationTest() {
        // Создаем пользователя с валидными данными для успешной регистрации
        User user = User.createUserForRegistration("eve.holt@reqres.in", "pistol");

        // Выполняем запрос на регистрацию
        Response response = userService.registerUser(user);

        // Проверяем, что ответ соответствует ожиданиям (код 200)
        response.then().spec(responseSpecSuccess(200))
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @Test
    @Story("Регистрация пользователя")
    @Description("Ошибка регистрации из-за отсутствия пароля")
    @Step("Проверка ошибки при регистрации без пароля")
    public void unsuccessfulRegistrationTest() {
        // Создаем пользователя без пароля
        User user = User.createUserForRegistration("eve.holt@reqres.in", null);

        // Проверяем, что ответ соответствует ожиданиям (код 400)
        Response response = userService.registerUser(user);
        response.then().spec(Specifications.responseSpecError(400))
                .body("error", equalTo("Missing password"));
    }

    @Test
    @Story("Список пользователей")
    @Description("Проверка email пользователей на соответствие домену @reqres.in")
    public void checkEmailTest() {
        // Выполняем запрос на получение списка пользователей
        Response response = userService.listUsers(1);
        // Проверяем, что все email имеют окончание @reqres.in
        response.then().spec(Specifications.responseSpecSuccess(200))
                .body("data.email", everyItem(endsWith("@reqres.in")));
    }

    @Test
    @Story("Удаление пользователя")
    @Description("Проверка успешного удаления пользователя с ID 2")
    @Step("Удаление пользователя и проверка статус-кода 204")
    public void deleteUserTest() {
        // Выполняем запрос на удаление пользователя с ID 2
        Response response = userService.deleteUser(2);
        // Проверяем, что статус-код 204
        response.then().spec(Specifications.responseSpecSuccess(204));
    }

    @Test
    @Story("Обновление пользователя")
    @Description("Обновление данных пользователя и проверка даты обновления")
    @Step("Обновление информации и проверка даты")
    public void updateUserTest() {
        // Создаем данные для обновления пользователя
        User user = User.createUserForUpdate("morpheus", "zion resident");
        // Получаем текущую дату
        String currentDate = DateUtils.getCurrentDate();

        // Выполняем запрос на обновление пользователя
        Response response = userService.updateUser(2, user);
        // Проверяем, что обновление прошло успешно и дата обновления соответствует текущей
        response.then().spec(Specifications.responseSpecSuccess(200))
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"))
                .body("updatedAt", startsWith(currentDate));

    }
}
