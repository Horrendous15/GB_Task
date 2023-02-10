package org.example.ApiTests;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPosts {

    public String getToken() {
        return given()
                .formParam("username", "gngbfgb")
                .formParam("password", "ea3c0db283")
                .when()
                .post("https://test-stand.gb.ru/gateway/login")
                .then()
                .extract().jsonPath()
                .get("token")
                .toString();
    };

    @Test
    void apiGetPosts()
    {
        String token = getToken();
       JsonPath response = given()
                .when()
                .log().all()
                .header("x-auth-token", token)
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
               .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("count"), not(equalTo(0)));
    }

    @Test
    void apiPostsSortDesc()
    {
        String token = getToken();
        JsonPath response = given()
                .when()
                .header("x-auth-token", token)
                .queryParam("sort","createdAt")
                .queryParam("order","DESC")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("count"), not(equalTo(0)));
    }
    @Test
    void apiPostsSortAsc()
    {
        String token = getToken();
        JsonPath response = given()
                .when()
                .header("x-auth-token", token)
                .queryParam("sort","createdAt")
                .queryParam("order","Asc")
                .queryParam("page","1")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("count"), not(equalTo(0)));
    }

    @Test
    void apiPostsPage()
    {
        String token = getToken();
        JsonPath response =  given()
                .when()
                .header("x-auth-token", token)
                .queryParam("page","1")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("prevPage"), equalTo(1));
    }

    @Test
    void apiPostsEmptyPage()
    {
        String token = getToken();
        JsonPath response =  given()
                .when()
                .header("x-auth-token", token)
                .queryParam("page","100")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("prevPage"), equalTo(99));
        assertThat(response.get("nextPage"), equalTo(null));
    }

}
