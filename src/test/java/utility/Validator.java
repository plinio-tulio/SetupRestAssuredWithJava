package utility;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class Validator {

    public static void validateStatusCode(int statusCode, Response response){
        response.then().assertThat().statusCode(statusCode);
    }

    public static void validateAtributoValue(String valorAtributo, String valorEsperado){
        Assert.assertEquals(valorAtributo, valorEsperado);
    }

    public static void validateAtributeResponse(Response response, String atributo, Object valorAtributo){
        response.then().assertThat().body(atributo,equalTo(valorAtributo));
    }

    public static void validateAtributeResponseNotEmpty(Response response, String atributo){
        response.then().assertThat().body(atributo, Matchers.notNullValue());
    }

    public static void validateContractResponse(String schemaJson, Response response){
        JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+ schemaJson);
        response.then().assertThat().body(validator);
    }

}
