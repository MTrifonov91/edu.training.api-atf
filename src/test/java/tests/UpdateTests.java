package tests;

import config.ApiConfig;
import config.HttpStatusCodes;
import models.requests.Employee;
import models.responses.EmployeeUpdateResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specifications;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class UpdateTests {

    @Test
    public void testUpdate() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL), Specifications.responseSpec(HttpStatusCodes.OK));

        Employee employee = new Employee("morpheus", "zion resident");

        EmployeeUpdateResponse response = given()
                .body(employee)
                .put(ApiConfig.UPDATE_USER)
                .then().log().all()
                .extract().as(EmployeeUpdateResponse.class);

        DateTimeFormatter currentTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter responseTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime parsedResponseTime = LocalDateTime.parse(response.getUpdatedAt(), responseTimeFormatter);

        Assertions.assertEquals(currentTime.format(currentTimeFormatter), parsedResponseTime.format(currentTimeFormatter));
    }
}
