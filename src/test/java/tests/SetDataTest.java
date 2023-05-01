package tests;

import helpers.TestData;
import lombok.RequestLombok;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class SetDataTest {

    TestData testData = new TestData();

    @Test
    @Tag("test")
    void askFeedBack() {

        RequestLombok requestLombok = new RequestLombok();
        requestLombok.setEmail(testData.email);
        requestLombok.setFeedback(testData.feedback);
        requestLombok.setName(testData.name);

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
        requestLombok.setBirthday(testData.birthDay);
        requestLombok.setFirstName(testData.firstName);
        requestLombok.setLastName(testData.lastName);
        requestLombok.setVacancyUuid(testData.vacancyId);
        requestLombok.setVacancyName(testData.vacancyName);
        requestLombok.setPhoneNumber(testData.phoneNumber);

        step("To send a resume", () ->
                given(requestSpecWorking)
                        .when()
                        .contentType("application/json")
                        .body(requestLombok)
                        .post("/api/applicants/vacancies/response?apiKey=1b1d705f-0b5a-4c7d-a84d-81a5d2f662a5")
                        .then()
                        .spec(responseSpecWorking));
    }

    @Test
    @Tag("test")
    void mailForWorkNegative() {

        step("Negative try to delete data", () ->
                given()
                        .when()
                        .delete("/api/applicants/vacancies/response?apiKey=1b1d705f-0b5a-4c7d-a84d-81a5d2f662a5")
                        .then()
                        .spec(responseSpecWorkingNegative));
    }
}
