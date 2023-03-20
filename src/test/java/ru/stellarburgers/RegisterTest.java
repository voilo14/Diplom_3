package ru.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.stellarburgers.model.User;
import ru.stellarburgers.pages.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

public class RegisterTest extends BaseTest {

    private RegisterPage registerPage;

    @Before
    public void openPage() {

        registerPage = open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
    }

    @Test
    @DisplayName("Successful registration")
    public void successfulRegistration() {
        User user = Util.randomUser();

        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();

        $(By.xpath("//h2[text()]")).shouldBe(visible);
        assertNotNull(Util.getToken(user));

        Util.deleteUser(user);
    }

    @Test
    @DisplayName("Failed registration with a short password")
    public void shortPassword() {
        User user = Util.randomUser("12345");

        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();

        $(By.xpath("//p[@class=\"input__error text_type_main-default\"]")).shouldBe(visible);
        assertNull(Util.getToken(user));
    }
}
