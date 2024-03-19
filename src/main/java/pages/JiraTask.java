package pages;

import java.util.List;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class JiraTask {
    private final SelenideElement statusValue =$x("//span[@id='status-val']/child::span");
    private final SelenideElement fixVersions =$x("//span[@id='fixVersions-field']/child::a");

    public List<String> statusCheck() {
        String statusText = statusValue.shouldBe(Condition.visible).getText();
        String fixVersionsText = fixVersions.shouldBe(Condition.visible).getText();
        return List.of(statusText, fixVersionsText);
    }
}
