package utility;

import com.sun.net.httpserver.Headers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

public class ApiClient {

    public static RequestSpecification request() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = "https://reqres.in";
        return RestAssured.given().
                log().all()
                .accept(ContentType.JSON);
    }

    public static Response sendRequest(Method method, String path, String body) throws Exception {
        Response response;
        RequestSpecification authorization = request();
        switch (method) {
            case GET:
                response =
                        (Objects.isNull(body)) ?
                                authorization
                                        .get(path)
                                        .then()
                                        .extract()
                                        .response()
                                :
                                authorization
                                        .body(body)
                                        .get(path)
                                        .then()
                                        .extract()
                                        .response();
                break;

            case POST:
                response = authorization
                        .body(body)
                        .post(path)
                        .then()
                        .extract()
                        .response();
                break;

            case PUT:
                response = authorization
                        .body(body)
                        .put(path)
                        .then()
                        .extract()
                        .response();
                break;

            case DELETE:
                response = authorization
                        .delete(path)
                        .then()
                        .extract()
                        .response();
                break;

            case PATCH:
                response = authorization
                        .body(body)
                        .patch(path)
                        .then()
                        .extract()
                        .response();
                break;
            default:
                throw new IllegalAccessException("Incorrect provided API");
        }
        if (response.statusCode() == 401) {
            throw new Exception("TOKEN has expired");
        }
        return response;
    }
}
