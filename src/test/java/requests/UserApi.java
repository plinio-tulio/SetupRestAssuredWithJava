package requests;

import data.UserDataJson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Environment;
import utility.ResponseRequest;

public class UserApi {

    static String baseUrl = Environment.getValueEnvironment("baseurl");
    static String baseUri = "/api/users";

    public static RequestSpecification request() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                        .baseUri(baseUrl+baseUri)
                        //.config(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()))
                        //.filter(new RequestLoggingFilter())
                        //.filter(new ResponseLoggingFilter())
                        //.header("Authorization","Bearer"+token)
                        .log().all()  //print request
                        .accept(ContentType.JSON);
    }

    public static Response getUser(){
        new ResponseRequest().printTituloRequest();
        Response response = request().get("?page=2");
        new ResponseRequest().printResponse(response);
        return (Response) response.then().extract();
    }

    public static Response getUserOption2(){
        Response response = request()
                .pathParam("pageNumber", "2")
                .get(baseUrl + "/api/users?page={pageNumber}");
        return (Response) response.then().extract();
    }

    public static Response postUser(){
        new ResponseRequest().printTituloRequest();
        Response response = request().body(UserDataJson.GetUserDataJson()).post(baseUrl + "/api/users");
        return (Response) response.then().extract();
    }

    public static Response putUser(){
        new ResponseRequest().printTituloRequest();
        Response response = request().pathParam("pageNumber", "2").put(baseUrl + "/api/users/{pageNumber}");
        return (Response) response.then().extract();
    }

    public static Response deleteUser(){
        new ResponseRequest().printTituloRequest();
        Response response = request().pathParam("pageNumber", "2").delete(baseUrl + "/api/users/{pageNumber}");
        return (Response) response.then().extract();
    }

}