package testclasses;

import actions.UserRequest;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class UserTest2 {


    UserRequest homeRequest = new UserRequest();

    @Test
    public void testCase1() throws Exception {
        Response response = homeRequest.getUsers(2);
        response.then().log().all()
                .statusCode(200)
                .assertThat()
                .body("page", Matchers.equalTo(2),
                        "per_page", Matchers.equalTo(6),
                        "total", Matchers.equalTo(12),
                        "data[0].id", Matchers.equalTo(7),
                        "data[0].email", Matchers.equalTo("michael.lawson@reqres.in"));
    }

    @Test
    public void testCase2() throws Exception {
        Response response = homeRequest.createUsers();
        response.then().log().all()
                .statusCode(201)
                .assertThat()
                .body("id", Matchers.notNullValue(),
                        "createdAt", Matchers.notNullValue());
    }
}
