package ru.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.model.User;
import ru.stellarburgers.pages.ForgotPasswordPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginTest extends BaseTest {

    User user = Util.randomUser();

    @Before
    public void createUser() {

        Util.createUser(user);
    }

    @After
    public void deleteUser() {
        Util.deleteUser(user);
    }

    @Test
    @DisplayName("Successful login via the 'Login to account' button")
    public void successfulLoginByLoginButton() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickLoginButton();

        successfulLoginTest();
    }

    @Test
    @DisplayName("Successful login on the 'My Account' button")
    public void successfulLoginByAccountButton() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site", MainPage.class);
        mainPage.clickAccountButton();

        successfulLoginTest();
    }

    @Test
    @DisplayName("Successful login from the registration window")
    public void successfulLoginByRegisterView() {
        RegisterPage registerPage = open("https://stellarburgers.nomoreparties.site/register", RegisterPage.class);
        registerPage.clickLoginLink();

        successfulLoginTest();
    }

    @Test
    @DisplayName("Successful login from the password recovery window")
    public void successfulLoginByForgotPasswordView() {
        ForgotPasswordPage forgotPasswordPage = open("https://stellarburgers.nomoreparties.site/forgot-password", ForgotPasswordPage.class);
        forgotPasswordPage.clickLoginLink();

        successfulLoginTest();
    }

    private void successfulLoginTest() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.assertThatThisIsMainPage();
        mainPage.getLoginButton().shouldBe(visible);
    }
}
