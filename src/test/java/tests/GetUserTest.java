package tests;

import config.ApiConfig;
import config.HttpStatusCodes;
import models.requests.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specifications;

import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class GetUserTest {

    @Test
    public void validateAvatarLinkToContainUserId() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL), Specifications.responseSpec(HttpStatusCodes.OK));

        User user = given()
                .when()
                .get(ApiConfig.GET_USER)
                .then().log().all()
                .extract().body().jsonPath().getObject("data", User.class);

        Assertions.assertTrue(user.getAvatar().contains(String.valueOf(user.getId())));
    }

    @Test
    public void validateAvatarLinksToContainsUsersIds() {
        Specifications.installSpecification(Specifications.requestSpec(ApiConfig.BASE_URL), Specifications.responseSpec(HttpStatusCodes.OK));

        List<User> users = given()
                .when()
                .get(ApiConfig.GET_USERS_LIST)
                .then().log().all()
                .extract().body().jsonPath().getList("data", User.class);

        Assertions.assertTrue(
                IntStream.range(0, users.size())
                        .allMatch(i -> users.get(i).getAvatar().contains(String.valueOf(users.get(i).getId())))
        );
    }
}
