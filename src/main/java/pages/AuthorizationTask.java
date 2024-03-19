package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationTask {
    private static final SelenideElement profileItem = $x("//a[@id='header-details-user-fullname']");
    private static final SelenideElement subNavigationTitle = $x("//h1/span[@id='issues-subnavigation-title']");

    public static Boolean isProfileItemDisplayed() {
        waitSignIn();
        return profileItem.isDisplayed();
    }
    public static void waitSignIn() {
        profileItem.shouldBe(Condition.visible);
    }
    public static Boolean isTitleExists(String title) {
        return subNavigationTitle.getText().equals(title);
    }

}
