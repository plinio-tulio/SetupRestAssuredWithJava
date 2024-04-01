package testclasses;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import requests.UserApi;
import utility.Validator;

public class UserTest3 {

    String baseUrl = "https://reqres.in";

    @Test(description = "verify get user request")
    public void verifyGetUserMethod() {
        Response response = UserApi.getUser();
        Validator.validateStatusCode(200,response);
        Validator.validateAtributeResponse(response, "page", 2);
    }

    @Test(description = "verify get request")
    public void verifyGetMethod() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("pageNumber", "2")
                .log().all()
                .when()
                .get(baseUrl + "/api/users?page={pageNumber}");
        response.then().log().all()
                .assertThat()
                .statusCode(200)
                .body("page", Matchers.equalTo(2));
    }

    @Test(description = "verify post request")
    public void verifyPostMethod() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Ganesh");
        jsonObject.put("company", "TCS");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .log().all()
                .when()
                .post(baseUrl + "/api/users");
        response.then().log().all()
                .assertThat()
                .statusCode(201)
                .body("createdAt", Matchers.notNullValue());
    }

    @Test(description = "verify put request")
    public void verifyPutMethod() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Abdul Kalam");
        jsonObject.put("job", "Scientist");
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("pageNumber", "2")
                .body(jsonObject.toString())
                .log().all()
                .when()
                .put(baseUrl + "/api/users/{pageNumber}");
        response.then().log().all()
                .assertThat()
                .statusCode(200)
                .body("updatedAt", Matchers.notNullValue());
    }

    @Test(description = "verify delete request")
    public void verifyDeleteMethod() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .pathParam("pageNumber", "2")
                .log().all()
                .when()
                .delete(baseUrl + "/api/users/{pageNumber}");
        response.then().log().all()
                .assertThat()
                .statusCode(204);
    }
}
