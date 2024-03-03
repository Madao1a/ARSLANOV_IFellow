package secondLesson;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PageTest {
    private final SelenideElement projectButton = $x("//a[@id='browse_link']");
    private final SelenideElement iconTest = $x("//a[@id='admin_main_proj_link_lnk']");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']");
    private final SelenideElement tasksCounter = $x("//div[@class='showing']/child::span");


    public void projectTest(){
        projectButton.click();
        iconTest.click();
    }

    public void searchText(String text){
        searchInput.sendKeys(text);
        searchInput.pressEnter();
    }

    public int countTask(){
        String countTaskText = tasksCounter.getText();
        String[] parts = countTaskText.split(" ");
        return Integer.parseInt(parts[2]);
    }







}
