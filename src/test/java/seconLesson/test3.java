package seconLesson;

import com.codeborne.selenide.Selenide;
import io.restassured.internal.common.assertion.Assertion;
import org.codehaus.groovy.runtime.powerassert.AssertionRenderer;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import secondLesson.*;
public class test3 extends WebHooks {
    private final Page page = new Page();
    private final AuthorizationTask authorizationTask = new AuthorizationTask();
    private final PageTest pageTest = new PageTest();
    private final JiraTask jiraTask = new JiraTask();
    private final CreateJiraTask createJiraTask = new CreateJiraTask();

    private final String log = "AT2";
    private final String pass = "Qwerty123";
    private int initTaskCount;
    private int afterTaskCount;


    @Test
    @DisplayName("Проверка авторизации")
    public void loginTest() {
        page.login(log, pass);
        Assertions.assertTrue(AuthorizationTask.isProfileItemDisplayed());
    }

    @Test
    @DisplayName("Проверка проекта тест")
    public void projectTest() {
        page.login(log, pass);
        pageTest.projectTest();
        Assertions.assertTrue(AuthorizationTask.isTitleExists("Открытые задачи"));
    }

    @Test
    @DisplayName("проверка полей задачи TestSelenium")
    public void taskTestSelenium(){
        page.login(log, pass);
        AuthorizationTask.waitSignIn();
        pageTest.searchText("TestSelenium");
        List<String> projectStatus = jiraTask.statusCheck();
        Assertions.assertEquals("СДЕЛАТЬ", projectStatus.get(0));
        Assertions.assertEquals("Version 2.0", projectStatus.get(1));
    }

    @Test
    @DisplayName("Создание нового бага")
    public  void CreatJiraTask(){
        page.login(log, pass);
        pageTest.projectTest();
        initTaskCount = pageTest.countTask();
        System.out.println(initTaskCount);
        createJiraTask.createNewTask("Дефект","Описание дефекта" );
        Selenide.refresh();
        afterTaskCount = pageTest.countTask();
        System.out.println(afterTaskCount);
        Assertions.assertEquals(initTaskCount, afterTaskCount-1);
    }

    @Test
    @DisplayName("Перевод статусов")
    public void CaStatus(){
        page.login(log, pass);
        pageTest.projectTest();
        createJiraTask.statusChange();
    }



}
