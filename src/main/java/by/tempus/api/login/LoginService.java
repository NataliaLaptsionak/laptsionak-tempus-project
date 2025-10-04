package by.tempus.api.login;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class LoginService {

    private Response response;

    public void doRequest(String email, String password) {
        String URL = "https://tempus.by/bitrix/services/main/ajax.php";
        response = given()
                .formParams(LoginService.getFormParams(email, password))
                .queryParams(getQueryParams())
                .when()
                .post(URL);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public String getErrorMessage() {
        return response.path("errors[0].message");
    }

    public static Map<String, String> getFormParams(String email, String password) {
        Map<String, String> formParam = new HashMap<>();
        formParam.put("email", email);
        formParam.put("password", password);
        return formParam;
    }
    public static Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", "imedia:main.api.Auth.loginByEmail");
        return queryParams;
    }
}

