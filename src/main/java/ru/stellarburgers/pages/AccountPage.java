package ru.stellarburgers.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class AccountPage {

    // локатор ссылки "Конструктор"
    @FindBy(how = How.XPATH, using = "//a[contains(@class, 'AppHeader_header__link__3D_hX')]")
    private SelenideElement constructorLink;

    // локатор логотипа
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement logo;

    // локатор ссылки "Выход"
    @FindBy(how = How.XPATH, using = "//button[contains(@class, 'Account')]")
    private SelenideElement logoutLink;

    // локатор контейнера
    @FindBy(how = How.CLASS_NAME, using = "Account_account__vgk_w")
    private SelenideElement container;

    public void clickConstructorLink() {
        constructorLink.should(visible, Duration.ofSeconds(5));
        constructorLink.click();
    }

    public void assertThatThisIsAccountPage() {
        container.shouldBe(visible);
    }

    public void clickLogo() {
        logo.should(visible, Duration.ofSeconds(5));
        logo.click();
    }

    public void clickLogoutLink() {
        logoutLink.should(visible, Duration.ofSeconds(5));
        logoutLink.click();
    }
}
