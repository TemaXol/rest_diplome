package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class CheckTest {

    @Test
    @Tag("test")
    void checkCountryList() {

        step("check country List", () ->
                given(requestSpec)
                        .when()
                        .get("/api/v1/countries/list")
                        .then()
                        .spec(responseSpec));

    }

    @Test
    @Tag("test")
    void checkPizzeria() {
        step("check country List", () ->
                given()
                        .when()
                        .get("/api/v1/pizzerias/all/156")
                        .then()
                        .log().all()
                        .statusCode(200)

        );
    }

    @Test
    @Tag("test")
    void checkRevenueCountry() {

        step("check country revenue", () ->
                given(requestSpec)
                        .when()
                        .get("api/v1/pizzerias/unit/643/144")
                        .then()
                        .log().all()
                        .spec(responseSpecRevCountry));

    }
}
