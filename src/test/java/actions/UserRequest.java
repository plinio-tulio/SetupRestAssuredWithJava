package actions;

import data.UserDataJson;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utility.ApiClient;

public class UserRequest {

    String userPath = "/api/users";

    public Response getUsers(int pageValue) throws Exception {
        String parameter = "?page=" + pageValue;
        return ApiClient.sendRequest(Method.GET, userPath + parameter, null);
    }

    public Response createUsers() throws Exception {
        JSONObject jsonObject = UserDataJson.GetUserDataJson();
        return ApiClient.sendRequest(Method.POST, userPath, jsonObject.toString());
    }
}
