package ru.stellarburgers;

import org.junit.Before;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {

    @Before
    public void setBaseURI() {
        baseURI = "https://stellarburgers.nomoreparties.site/";
    }
}
