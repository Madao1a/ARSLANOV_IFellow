package secondLesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Page {
    private final SelenideElement osName = $x("//input[@name='os_username']");
    private final SelenideElement osPass = $x("//input[@name='os_password']");
    private final SelenideElement logButton = $x("//input[@name='login']");

    public void login(String log, String pass){
        osName.shouldBe(Condition.visible);
        osPass.shouldBe(Condition.visible);
        osName.setValue(log);
        osPass.setValue(pass);
        logButton.click();
    }

}
