package pages;

import java.util.List;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraTask {
    private final SelenideElement statusValue =$x("//span[@id='status-val']/child::span");
    private final SelenideElement fixVersions =$x("//span[@id='fixVersions-field']/child::a");

//    public List<String> statusCheck() {
//        String statusText = statusValue.shouldBe(Condition.visible).getText();
//        String fixVersionsText = fixVersions.shouldBe(Condition.visible).getText();
//        return List.of(statusText, fixVersionsText);
//    }
    @Step("шестой")
    public List<String> statusCheck(){
        String statusText = statusValue.getText().toLowerCase();
        String version = fixVersions.getText();
        return List.of(statusText, version);
    }

}
