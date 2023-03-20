package ru.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.model.User;
import ru.stellarburgers.pages.AccountPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoggedUserTest extends BaseTest {

    User user = Util.randomUser();

    @Before
    public void createUser() {

        Util.createUser(user);
    }

    @Before
    public void loginUser() {
        LoginPage loginPage = open("https://stellarburgers.nomoreparties.site/login", LoginPage.class);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();

        MainPage mainPage = page(MainPage.class);
        mainPage.assertThatThisIsMainPage();
    }

    @After
    public void deleteUser() {
        Util.deleteUser(user);
    }

    @Test
    @DisplayName("Switching to a profile from the constructor")
    public void accountTransitionFromConstructor() {
        MainPage mainPage = open("https://stellarburgers.nomoreparties.site/login", MainPage.class);
        mainPage.clickAccountButton();

        AccountPage accountPage = page(AccountPage.class);
        accountPage.assertThatThisIsAccountPage();
    }

    @Test
    @DisplayName("Switching to the constructor from the profile with a link")
    public void constructorTransitionFromAccountByConstructorLink() {
        AccountPage accountPage = open("https://stellarburgers.nomoreparties.site/account", AccountPage.class);
        accountPage.clickConstructorLink();

        MainPage mainPage = page(MainPage.class);
        mainPage.assertThatThisIsMainPage();
    }

    @Test
    @DisplayName("Switching to the designer from the logo profile")
    public void constructorTransitionFromAccountByLogo() {
        AccountPage accountPage = open("https://stellarburgers.nomoreparties.site/account", AccountPage.class);
        accountPage.clickLogo();

        MainPage mainPage = page(MainPage.class);
        mainPage.assertThatThisIsMainPage();
    }

    @Test
    @DisplayName("Exit")
    public void logout() {
        AccountPage accountPage = open("https://stellarburgers.nomoreparties.site/account", AccountPage.class);
        accountPage.clickLogoutLink();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.assertThatThisIsLoginPage();
    }

}
