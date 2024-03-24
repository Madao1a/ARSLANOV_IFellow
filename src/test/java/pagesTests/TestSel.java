package pagesTests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import java.util.List;

import org.junit.jupiter.api.Test;
import webhooks.WebHooks;
import pages.Page;
import pages.AuthorizationTask;
import pages.PageTest;
import pages.JiraTask;
import pages.CreateJiraTask;

public class TestSel extends WebHooks {
    private final Page page = new Page();
    private final AuthorizationTask authorizationTask = new AuthorizationTask();
    private final PageTest pageTest = new PageTest();
    private final JiraTask jiraTask = new JiraTask();
    private final CreateJiraTask createJiraTask = new CreateJiraTask();


    private int initTaskCount;
    private int afterTaskCount;

    @DisplayName("Проверка авторизации")
    @Test
    public void loginTest() {
        page.login(System.getProperty("login"),System.getProperty("password"));
        Assertions.assertTrue(authorizationTask.isProfileItemDisplayed());
    }


    @DisplayName("Проверка проекта тест")
    @Test
    public void projectTest() {
        page.login(System.getProperty("login"),System.getProperty("password"));
        pageTest.projectTest();
        Assertions.assertTrue(authorizationTask.isTitleExists("Открытые задачи"));
    }


    @DisplayName("проверка полей задачи TestSelenium")
    @Test
    public void taskTestSelenium(){
        page.login(System.getProperty("login"),System.getProperty("password"));
        authorizationTask.waitSignIn();
        pageTest.searchText("TestSelenium");
        List<String> projectStatus = jiraTask.statusCheck();
        Assertions.assertEquals("СДЕЛАТЬ", projectStatus.get(0));
        Assertions.assertEquals("Version 2.0", projectStatus.get(1));
    }


    @DisplayName("Создание нового бага")
    @Test
    public  void CreatJiraTask(){
        page.login(System.getProperty("login"),System.getProperty("password"));
        pageTest.projectTest();
        initTaskCount = pageTest.countTask();
        createJiraTask.createNewTask("Дефект","Описание дефекта" );
        Selenide.refresh();
        afterTaskCount = pageTest.countTask();
        Assertions.assertEquals(initTaskCount, afterTaskCount-1);
    }


    @DisplayName("Перевод статусов")
    @Test
    public void CaStatus(){
        page.login(System.getProperty("login"),System.getProperty("password"));
        pageTest.projectTest();
        createJiraTask.statusChange();
    }
}
