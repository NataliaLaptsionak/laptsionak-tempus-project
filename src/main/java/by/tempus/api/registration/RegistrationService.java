package by.tempus.api.registration;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegistrationService {
    private static final Logger logger = LogManager.getLogger(RegistrationService.class);
    private Response response;

    public void doRegistrationRequest(String fullName, String email, String phone, String password, String passwordRepeat) {
        logger.info("Выполнение запроса регистрации для пользователя: {} с email: {}", fullName, email);
        String URL = "https://tempus.by/bitrix/services/main/ajax.php";
        response = given()
                .queryParams(getQueryParams())
                .formParams(getFormParams(fullName, email, phone, password, passwordRepeat))
                .when()
                .post(URL);
        logger.info("Получен ответ от API регистрации. Статус код: {}", response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getErrorMessage() {
        logger.error("Получено сообщение об ошибке регистрации: {}", response.jsonPath().getString("message"));
        return response.path("errors[0].message");
    }

    private Map<String, String> getFormParams(String fullName, String email, String phone, String password, String passwordRepeat) {
        logger.debug("Формирование formParams для регистрации.");
        Map<String, String> formParam = new HashMap<>();
        formParam.put("fullName", fullName);
        formParam.put("email", email);
        formParam.put("phone", phone);
        formParam.put("password", password);
        formParam.put("passwordRepeat", passwordRepeat);
        return formParam;
    }

    private Map<String, String> getQueryParams() {
        logger.debug("Формирование queryParams для регистрации.");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", "imedia:main.api.Auth.registration");
        return queryParams;
    }
}
