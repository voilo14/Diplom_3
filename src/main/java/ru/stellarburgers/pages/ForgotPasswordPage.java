package ru.stellarburgers.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class ForgotPasswordPage {

    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/login')]")
    private SelenideElement loginLink;

    public void clickLoginLink() {
        loginLink.should(visible, Duration.ofSeconds(5));
        loginLink.click();
    }
}
