package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationTask {
    private static final SelenideElement profileItem = $x("//a[@id='header-details-user-fullname']");
    private static final SelenideElement subNavigationTitle = $x("//h1/span[@id='issues-subnavigation-title']");
    @Step("Первый")
    public static Boolean isProfileItemDisplayed() {
        waitSignIn();
        return profileItem.isDisplayed();
    }

    @Step("Второй")
    public static void waitSignIn() {
        profileItem.shouldBe(Condition.visible);
    }

    @Step("Третий")
    public static Boolean isTitleExists(String title) {
        return subNavigationTitle.getText().equals(title);
    }

}
