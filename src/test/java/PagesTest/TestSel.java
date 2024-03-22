package PagesTest;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import pages.*;
import webhooks.WebHooks;

public class TestSel extends WebHooks {
    private final Page page = new Page();
    private final PageTest pageTest = new PageTest();
    private final JiraTask jiraTask = new JiraTask();
    private final CreateJiraTask createJiraTask = new CreateJiraTask();

    private int initTaskCount;
    private int afterTaskCount;

    @DisplayName("Проверка авторизации")
    @org.junit.jupiter.api.Test
    public void loginTest() {
        page.login(System.getProperty("login"),System.getProperty("password"));
        Assertions.assertTrue(AuthorizationTask.isProfileItemDisplayed());
    }


    @DisplayName("Проверка проекта тест")
    @org.junit.jupiter.api.Test
    public void projectTest() {
        page.login(System.getProperty("login"),System.getProperty("password"));
        pageTest.projectTest();
        Assertions.assertTrue(AuthorizationTask.isTitleExists("Открытые задачи"));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("проверка полей задачи TestSelenium")
    public void taskTestSelenium(){
        page.login(System.getProperty("login"),System.getProperty("password"));
        AuthorizationTask.waitSignIn();
        pageTest.searchText("TestSelenium");
        List<String> projectStatus = jiraTask.statusCheck();
        Assertions.assertEquals("СДЕЛАТЬ", projectStatus.get(0));
        Assertions.assertEquals("Version 2.0", projectStatus.get(1));
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Создание нового бага")
    public  void CreatJiraTask(){
        page.login(System.getProperty("login"),System.getProperty("password"));
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
        page.login(System.getProperty("login"),System.getProperty("password"));
        pageTest.projectTest();
        createJiraTask.statusChange();
    }
}
