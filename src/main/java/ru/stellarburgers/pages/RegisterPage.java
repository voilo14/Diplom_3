package ru.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class RegisterPage {

    // локатор поля "Имя"
    @FindBy(how = How.XPATH, using = "(//input[contains(@name, 'name')])[1]")
    private SelenideElement nameField;

    // локатор поля "Email"
    @FindBy(how = How.XPATH, using = "(//input[contains(@name, 'name')])[2]")
    private SelenideElement emailField;

    // локатор поля "Пароль"
    @FindBy(how = How.XPATH, using = "//input[contains(@type, 'password')]")
    private SelenideElement passwordField;

    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'button_button__33qZ0')]")
    private SelenideElement registerButton;

    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/login')]")
    private SelenideElement loginLink;

    public void setName(String name) {
        nameField.should(visible, Duration.ofSeconds(5));
        nameField.setValue(name);
    }

    public void setEmail(String email) {
        emailField.should(visible, Duration.ofSeconds(5));
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.should(visible, Duration.ofSeconds(5));
        passwordField.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.should(visible, Duration.ofSeconds(5));
        registerButton.click();
    }

    public void clickLoginLink() {
        loginLink.should(visible, Duration.ofSeconds(5));
        loginLink.click();
    }
}
