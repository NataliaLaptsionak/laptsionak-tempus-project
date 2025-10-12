package by.tempus.api.login;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private Response response;

    public void doRequest(String email, String password) {
        logger.info("Выполнение запроса логина для пользователя: {}", email);
        String URL = "https://tempus.by/bitrix/services/main/ajax.php";
        response = given()
                .formParams(LoginService.getFormParams(email, password))
                .queryParams(getQueryParams())
                .when()
                .post(URL);
        logger.info("Получен ответ от API логина. Статус код: {}", response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getErrorMessage() {
        logger.error("Получено сообщение об ошибке: {}", response.jsonPath().getString("message"));
        return response.path("errors[0].message");
    }

    public static Map<String, String> getFormParams(String email, String password) {
        logger.debug("Формирование formParams для логина.");
        Map<String, String> formParam = new HashMap<>();
        formParam.put("email", email);
        formParam.put("password", password);
        return formParam;
    }

    public static Map<String, String> getQueryParams() {
        logger.debug("Формирование queryParams для логина.");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", "imedia:main.api.Auth.loginByEmail");
        return queryParams;
    }
}
