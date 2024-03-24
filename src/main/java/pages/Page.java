package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class Page {
    private final SelenideElement osName = $x("//input[@name='os_username']");
    private final SelenideElement osPass = $x("//input[@name='os_password']");
    private final SelenideElement logButton = $x("//input[@name='login']");

    @Step("Шестой")
    public void login(String login, String password){
        osName.sendKeys(login);
        osPass.sendKeys(password);
        logButton.click();
    }
}
