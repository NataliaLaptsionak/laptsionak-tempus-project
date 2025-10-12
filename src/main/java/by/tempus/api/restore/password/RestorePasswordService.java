package by.tempus.api.restore.password;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestorePasswordService {
    private static final Logger logger = LogManager.getLogger(RestorePasswordService.class);
    private Response response;

    public void doRequest(String email) {
        logger.info("Выполнение запроса на восстановление пароля для email: {}", email);
        String URL = "https://tempus.by/bitrix/services/main/ajax.php";
        response = given()
                .formParams(getFormParams(email))
                .queryParams(getQueryParams())
                .when()
                .post(URL);
        logger.info("Получен ответ от API восстановления пароля. Статус код: {}", response.getStatusCode());
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getErrorMessage() {
        logger.error("Получено сообщение об ошибке восстановления пароля: {}", response.jsonPath().getString("message"));
        return response.path("errors[0].message");
    }

    private Map<String, String> getFormParams(String email) {
        logger.debug("Формирование formParams для восстановления пароля.");
        Map<String, String> formParams = new HashMap<>();
        formParams.put("email", email);
        formParams.put("action", "imedis.main.api.auth.restore");
        return formParams;
    }

    public static Map<String, String> getQueryParams() {
        logger.debug("Формирование queryParams для восстановления пароля.");
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", "imedia:main.api.Auth.restore");
        return queryParams;
    }
}
