package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreateJiraTask {

    private final SelenideElement createTask = $x("//a[@id='create_link']");
    private final SelenideElement inputTaskTheme = $x("//input[@id='summary']");
    private final SelenideElement inputTaskDescription = $x("//textarea[@name='description']");
    private final SelenideElement createButton = $x("//input[@value='Создать']");
    private final SelenideElement checkNewTask = $x("//a[@class='issue-created-key issue-link']");
    private final SelenideElement triggerC = $x("//a[@class='order-options aui-button aui-style-default jira-aui-dropdown2-trigger']");
    private final SelenideElement labelCheck = $x("//label[@class='item-label checkbox']");
    private final SelenideElement lastCreatedTask = $x("//ol[@class='issue-list']/li[1]/a");
    private final SelenideElement inWorkButton = $x("//a[@id='action_id_21']");
    private final SelenideElement actionInWork =$x("//a[@id='action_id_21']");
    private final SelenideElement businessProcess =$x("//a[@id='opsbar-transitions_more']");
    private final SelenideElement accomplishedWork =$x("//span[text()='Выполнено']/parent::a[@role='menuitem']");

    @Step("создание нового бага")
    public void createNewTask(String taskTheme, String description) {
        createTask.click();
        inputTaskTheme.shouldBe(visible);
        inputTaskTheme.setValue(taskTheme);
        inputTaskDescription.setValue(description);
        createButton.click();
        checkNewTask.shouldBe(visible);
    }

    @Step("смена статусов")
    public void statusChange(){
        triggerC.click();
        labelCheck.click();
        lastCreatedTask.shouldBe(visible).click();
        inWorkButton.shouldBe(visible).click();
        actionInWork.click();
        businessProcess.click();
        accomplishedWork.click();

    }
}
