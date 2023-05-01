package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static org.hamcrest.Matchers.*;

public class Specs {

    public static RequestSpecification requestCountriesSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://globalapi.dodopizza.com/");

    public static RequestSpecification requestSpecFeedback = with()
            .filter(withCustomTemplates())
            .baseUri("https://storyapi.dodois.io/");

    public static RequestSpecification requestSpecWorking = with()
            .filter(withCustomTemplates())
            .baseUri("https://rabotavdodo.ru/");

    public static ResponseSpecification responseCountryListSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("countries.code", hasItems("cn", "ee", "gb", "kg", "kz", "lt",
                    "ru", "ro", "uz", "by", "si", "ng", "de", "vn", "pl", "tj", "ae"))
            .expectBody("countries.currency", hasItems("CNY", "EUR", "GBP", "KGS", "KZT", "EUR", "RUB", "RON", "UZS", "BYN",
                    "EUR", "NGN", "EUR", "VND", "PLN", "TJS", "AED"))
            .build();

    public static ResponseSpecification responseSpecRevCountry = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody("countries.countryId", contains(643))
            .expectBody("countries.countryName", contains("Россия"))
            .expectBody("countries.pizzeria.alias", contains("Московский"))
            .build();

    public static ResponseSpecification responseSpecFeedback = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification responseSpecWorking = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(400)
            .expectBody("title", is("One or more validation errors occurred."))
            .expectBody("errors", hasKey("$.unitUuid"))
            .build();


    public static ResponseSpecification responseSpecWorkingNegative = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(403)
            .build();
}