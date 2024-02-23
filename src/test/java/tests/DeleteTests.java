package tests;

import config.ApiConfig;
import config.HttpStatusCodes;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specifications;

import static io.restassured.RestAssured.given;
public class DeleteTests {

    @Test
    public void deleteTest() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL), Specifications.responseSpec(HttpStatusCodes.NO_CONTENT));

        Response response = given()
                .when()
                .delete(ApiConfig.DELETE_USER)
                .then().log().all()
                .extract().response();

        Assertions.assertTrue(response.body().asString().isEmpty());
    }
}
