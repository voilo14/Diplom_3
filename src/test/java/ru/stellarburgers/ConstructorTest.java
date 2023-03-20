package ru.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.pages.MainPage;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void openPage() {

        mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
    }

    @Test
    @DisplayName("Selecting sections")
    public void sectionsSelect() {
        String selectedClass = "tab_tab_type_current__2BEPc";

        mainPage.getBunsLink().shouldHave(cssClass(selectedClass));
        mainPage.getSousesLink().shouldNotHave(cssClass(selectedClass));
        mainPage.getFillingsLink().shouldNotHave(cssClass(selectedClass));

        mainPage.clickSousesLink();
        mainPage.getBunsLink().shouldNotHave(cssClass(selectedClass));
        mainPage.getSousesLink().shouldHave(cssClass(selectedClass));
        mainPage.getFillingsLink().shouldNotHave(cssClass(selectedClass));

        mainPage.clickFillingsLink();
        mainPage.getBunsLink().shouldNotHave(cssClass(selectedClass));
        mainPage.getSousesLink().shouldNotHave(cssClass(selectedClass));
        mainPage.getFillingsLink().shouldHave(cssClass(selectedClass));

        mainPage.clickBunsLink();
        mainPage.getBunsLink().shouldHave(cssClass(selectedClass));
        mainPage.getSousesLink().shouldNotHave(cssClass(selectedClass));
        mainPage.getFillingsLink().shouldNotHave(cssClass(selectedClass));
    }
}
