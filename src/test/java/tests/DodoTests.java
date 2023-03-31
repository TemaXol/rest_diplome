package tests;

import helpers.Variables;
import lombok.RequestLombok;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class DodoTests {

    Variables variables = new Variables();

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
    void checkRevenueCountry() {

        step("check country revenue", () ->
                given(requestSpec)
                        .when()
                        .get("api/v1/pizzerias/unit/643/144")
                        .then()
                        .log().all()
                        .spec(responseSpecRevCountry));

    }

    @Test
    @Tag("test")
    void askFeedBack() {

        RequestLombok requestLombok = new RequestLombok();
        requestLombok.setEmail(variables.email);
        requestLombok.setFeedback(variables.feedback);
        requestLombok.setName(variables.name);

        step("To send feedback", () ->
                given(requestSpecFeedback)
                        .when()
                        .contentType("application/json")
                        .body(requestLombok)
                        .post("/api/v2/application/feedback")
                        .then()
                        .spec(responseSpecFeedback));
    }

    @Test
    @Tag("test")
    void mailForWork() {

        RequestLombok requestLombok = new RequestLombok();
        requestLombok.setBirthday(variables.birthDay);
        requestLombok.setFirstName(variables.firstName);
        requestLombok.setLastName(variables.lastName);
        requestLombok.setVacancyUuid(variables.vacancyId);
        requestLombok.setVacancyName(variables.vacancyName);
        requestLombok.setPhoneNumber(variables.phoneNumber);

        step("To send a resume", () ->
                given(requestSpecWorking)
                        .when()
                        .contentType("application/json")
                        .body(requestLombok)
                        .post("/api/applicants/vacancies/response?apiKey=1b1d705f-0b5a-4c7d-a84d-81a5d2f662a5")
                        .then()
                        .spec(responseSpecWorking));
    }
}

