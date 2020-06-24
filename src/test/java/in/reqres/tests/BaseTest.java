package in.reqres.tests;

import in.reqres.models.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.*;

public class BaseTest {

    protected final String BASE_URL = "https://reqres.in/api/users/";

    @Step("Извлекаем данные об id пользователей в виде списка")
    protected List<String> getUserIds(String query) {
        return given().log().body().contentType(JSON)
                .when().get(BASE_URL + query)
                .then().log().body().extract().body().jsonPath().getList("data.id");
    }

    @Step("Получаем пользователя по его id - {userId}")
    protected ValidatableResponse getUserById(String userId) {
        return given().log().body().contentType(JSON)
                .when().get(BASE_URL + userId)
                .then().log().body();
    }

    @Step("Создаем пользователя, берем id из ответа на создание: {user} - пользователь для создания")
    protected String createUser(User user) {
        return given().log().body().contentType(JSON).body(user)
                .when().post(BASE_URL)
                .then().log().body().statusCode(SC_CREATED)
                .and().extract().path("id");
    }

    @Step("Редактируем пользователя: {user} - новые данные, {userId} - id пользователя")
    protected ValidatableResponse updateUser(User user, String userId) {
        return given().log().body().contentType(JSON).body(user)
                .when().put(BASE_URL + userId)
                .then().log().body().statusCode(SC_OK);
    }

    @Step("Удаляем пользователя по id - {userId}")
    protected void deleteUser(String userId) {
        given().log().body().contentType(JSON)
                .when().delete(BASE_URL + userId)
                .then().log().body().statusCode(SC_NO_CONTENT);
    }

    protected int getPagesCount() {
        return given().log().body().contentType(JSON)
                .when().get(BASE_URL + "?page=all")
                .then().log().body().extract().path("total_pages");
    }
}
