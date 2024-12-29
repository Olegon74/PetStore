package api;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;


public class Specification {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";

    public static RequestSpecification requestSpec = with()
                .baseUri(BASE_URL)
                .log().all()
                .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static void installSpecification(RequestSpecification request){
        RestAssured.requestSpecification = request;

    }

}




    /*public static ResponseSpecification responseSpecOk200() {
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }

    public static ResponseSpecification responseSpecOk400() {
        return new ResponseSpecBuilder().expectStatusCode(404).build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }*/


