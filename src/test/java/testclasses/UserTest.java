package testclasses;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import requests.UserApi;
import utility.Validator;

public class UserTest {

    @Test(description = "verify user get request")
    public void verifyUserGetMethod() {
        Response response = UserApi.getUser();
        Validator.validateStatusCode(200,response);
        Validator.validateAtributeResponse(response, "page", 2);
        Validator.validateAtributeResponse(response, "total", 12);
        Validator.validateAtributeResponse(response, "data[0].id", 7);
        Validator.validateAtributeResponseNotEmpty(response, "data[0].email");
        Validator.validateContractResponse("getUserResponse",response);
    }

    @Test(description = "verify user post request")
    public void verifyUserPostMethod() {
        Response response = UserApi.postUser();
        Validator.validateStatusCode(201,response);
        Validator.validateAtributeResponseNotEmpty(response, "createdAt");
    }

    @Test(description = "verify user put request")
    public void verifyUserPutMethod() {
        Response response = UserApi.putUser();
        Validator.validateStatusCode(200,response);
        Validator.validateAtributeResponseNotEmpty(response, "updatedAt");
    }

    @Test(description = "verify delete request")
    public void verifyUserDeleteMethod() {
        Response response = UserApi.deleteUser();
        Validator.validateStatusCode(204,response);
    }

}