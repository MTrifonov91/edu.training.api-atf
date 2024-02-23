package tests;

import config.ApiConfig;
import config.HttpStatusCodes;
import models.requests.Employee;
import models.requests.Registration;
import models.responses.EmployeeCreationResponse;
import models.responses.RegistrationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specifications;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class CreateTests {

    @Test
    public void createUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL), Specifications.responseSpec(HttpStatusCodes.CREATED));

        Employee employee = new Employee("morpheus", "leader");

        EmployeeCreationResponse response = given()
                .body(employee)
                .when()
                .post(ApiConfig.POST_NEW_USER)
                .then().log().all()
                .extract().as(EmployeeCreationResponse.class);

        DateTimeFormatter currentTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter responseTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime parsedResponseTime = LocalDateTime.parse(response.getCreatedAt(), responseTimeFormatter);

        Assertions.assertEquals(currentTime.format(currentTimeFormatter), parsedResponseTime.format(currentTimeFormatter));
    }

    @Test
    public void registerTest() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL),Specifications.responseSpec(HttpStatusCodes.OK));

        Registration registration = new Registration("eve.holt@reqres.in", "pistol");

        RegistrationResponse response = given()
                .body(registration)
                .when()
                .post(ApiConfig.POST_NEW_REGISTRATION)
                .then().log().all()
                .extract().as(RegistrationResponse.class);

        Assertions.assertEquals("QpwL5tke4Pnpja7X4", response.getToken());
    }


}
