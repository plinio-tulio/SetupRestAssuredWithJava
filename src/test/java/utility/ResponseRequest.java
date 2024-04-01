package utility;

import io.restassured.response.Response;

public class ResponseRequest {

    public void printResponse(Response response){
        System.out.println("==============================================================");
        System.out.println("                               RESPONSE                      ");
        System.out.println("==============================================================");
        response.prettyPrint();  //print response body
        System.out.println("Status Line: " + response.statusCode());
        System.out.println("---------------------------------------------------------------");
    }

    public void printTituloRequest(){
        System.out.println("==============================================================");
        System.out.println("                               REQUEST                       ");
        System.out.println("==============================================================");
    }
}
