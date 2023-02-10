package org.example.ApiTests;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostsNotMy {

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
    void apiPostsOwnerNotMe()
    {
        String token = getToken();
       JsonPath response = given()
                .when()
                .log().all()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
               .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("count"), not(equalTo(0)));
        assertThat(response.get("prevPage"), equalTo(1));
        assertThat(response.get("nextPage"), equalTo(2));
    }

    @Test
    void apiPostsNotMeSort()
    {
        String token = getToken();
        JsonPath response = given()
                .when()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
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
    void apiPostsNotMeSortDesc()
    {
        String token = getToken();
        JsonPath response = given()
                .when()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
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
    void apiPostsNotMeSortAll()
    {
        String token = getToken();
        JsonPath response = given()
                .when()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
                .queryParam("sort","createdAt")
                .queryParam("order","ALL")
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
    void apiPostsNotMePages()
    {
        String token = getToken();
        JsonPath response =  given()
                .when()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
                .queryParam("page","3")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("prevPage"), equalTo(2));
        assertThat(response.get("nextPage"), equalTo(4));
    }

    @Test
    void apiPostsNotMeEmptyPage()
    {
        String token = getToken();
        JsonPath response =  given()
                .when()
                .header("x-auth-token", token)
                .queryParam("owner","notMe")
                .queryParam("page","1000000")
                .get("https://test-stand.gb.ru/gateway/posts")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().body()
                .jsonPath();
        assertThat(response.get("data"), equalTo("[]"));
        assertThat(response.get("prevPage"), equalTo(999999));
        assertThat(response.get("nextPage"), equalTo(null));
    }

}
