package pagesTests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import org.junit.jupiter.api.Test;
import util.TestProperties;
import webhooks.WebHooks;
import pages.Page;
import pages.AuthorizationTask;
import pages.PageTest;
import pages.JiraTask;
import pages.CreateJiraTask;


public class TestSel extends WebHooks {
    private final Page page = new Page();
    private final PageTest pageTest = new PageTest();
    private final JiraTask jiraTask = new JiraTask();
    private final CreateJiraTask createJiraTask = new CreateJiraTask();

    private int initTaskCount;
    private int afterTaskCount;


    @DisplayName("Проверка авторизации")
    @Test
    public void loginTest() {
        page.login(TestProperties.getPoperty("login"),TestProperties.getPoperty("password"));
        Assertions.assertTrue(AuthorizationTask.isProfileItemDisplayed());
    }


    @DisplayName("Проверка проекта тест")
    @Test
    public void projectTest() {
        page.login(TestProperties.getPoperty("login"),TestProperties.getPoperty("password"));
        pageTest.projectTest();
        Assertions.assertTrue(AuthorizationTask.isTitleExists("Открытые задачи"));
    }


    @DisplayName("проверка полей задачи TestSelenium")
    @Test
    public void taskTestSelenium(){
        page.login(TestProperties.getPoperty("login"),TestProperties.getPoperty("password"));
        AuthorizationTask.waitSignIn();
        pageTest.searchText("TestSelenium");
        List<String> projectStatus = jiraTask.statusCheck();
        Assertions.assertEquals("готово", projectStatus.get(0));
        Assertions.assertEquals("Version 2.0", projectStatus.get(1));
    }


    @DisplayName("Создание нового бага")
    @Test
    public  void CreatJiraTask(){
        page.login(TestProperties.getPoperty("login"),TestProperties.getPoperty("password"));
        pageTest.projectTest();
        initTaskCount = pageTest.countTask();
        createJiraTask.createNewTask("Дефект2","Описание дефекта" );
        Selenide.refresh();
        afterTaskCount = pageTest.countTask();
        Assertions.assertEquals(initTaskCount, afterTaskCount-1);
    }


    @DisplayName("Перевод статусов")
    @Test
    public void CaStatus(){
        page.login(TestProperties.getPoperty("login"),TestProperties.getPoperty("password"));
        pageTest.projectTest();
        createJiraTask.statusChange();
    }
}
