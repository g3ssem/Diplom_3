package api;

import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class UserClient {
    public final static String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private final static String ENDPOINT_REG = "https://stellarburgers.nomoreparties.site/api/auth/register";
    private final static String ENDPOINT_LOGIN = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private final static String ENDPOINT_EDIT = " https://stellarburgers.nomoreparties.site/api/auth/user";


    public Response userCreate (User user){
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(ENDPOINT_REG);

    }
    public Response userLogin (String json) {
        return given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post(ENDPOINT_LOGIN);
    }
    public Response userEdit (User user, String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .body(user)
                .when()
                .patch(ENDPOINT_EDIT);
    }
    public Response userDelete (String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(ENDPOINT_EDIT);

    }
}
