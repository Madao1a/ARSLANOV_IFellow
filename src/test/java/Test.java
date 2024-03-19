import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import pages.*;
import webhooks.WebHooks;

public class Test extends WebHooks {
    private final Page page = new Page();
    private final AuthorizationTask authorizationTask = new AuthorizationTask();
    private final PageTest pageTest = new PageTest();
    private final JiraTask jiraTask = new JiraTask();
    private final CreateJiraTask createJiraTask = new CreateJiraTask();

    private final String log = "AT2";
    private final String pass = "Qwerty123";
    private int initTaskCount;
    private int afterTaskCount;

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка авторизации")
    public void loginTest() {
        page.login(log, pass);
        Assertions.assertTrue(AuthorizationTask.isProfileItemDisplayed());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка проекта тест")
    public void projectTest() {
        page.login(log, pass);
        pageTest.projectTest();
        Assertions.assertTrue(AuthorizationTask.isTitleExists("Открытые задачи"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("проверка полей задачи TestSelenium")
    public void taskTestSelenium(){
        page.login(log, pass);
        AuthorizationTask.waitSignIn();
        pageTest.searchText("TestSelenium");
        List<String> projectStatus = jiraTask.statusCheck();
        Assertions.assertEquals("СДЕЛАТЬ", projectStatus.get(0));
        Assertions.assertEquals("Version 2.0", projectStatus.get(1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Создание нового бага")
    public  void CreatJiraTask(){
        page.login(log, pass);
        pageTest.projectTest();
        initTaskCount = pageTest.countTask();
        createJiraTask.createNewTask("Дефект","Описание дефекта" );
        Selenide.refresh();
        afterTaskCount = pageTest.countTask();
        Assertions.assertEquals(initTaskCount, afterTaskCount-1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Перевод статусов")
    public void CaStatus(){
        page.login(log, pass);
        pageTest.projectTest();
        createJiraTask.statusChange();
    }
}
