package org.example.ApiTests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
public class GBApi {

    @Test
    public void invalidPassword()
    {
        given()
                .when()
                .formParam("username", "gngbfgb")
                .formParam("password","ea3c0db")
                .post("https://test-stand.gb.ru/gateway/login")
                                .then()
                .body("code", equalTo(401));
    }

    @Test
    public void invalidUsername()
    {
        given()
                .when()
                .formParam("username", "")
                .formParam("password","ea3c0db283")
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .assertThat()
                .body("code", equalTo(401));
    }

    @Test
    public void validLogin()
    {
        given()
                .when()
                .formParam("username", "gngbfgb")
                .formParam("password","ea3c0db283")
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .statusCode(200);
    }
}
