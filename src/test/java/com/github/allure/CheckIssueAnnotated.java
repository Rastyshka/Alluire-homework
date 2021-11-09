package com.github.allure;

import com.codeborne.selenide.WebDriverRunner;
import com.github.allure.steps.WebSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CheckIssueAnnotated {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final Integer ISSUE_NUMBER = 68;

    private final WebSteps steps = new WebSteps();

    @Test
    @Owner("abdullayevrr")
    @Feature("Поиск")
    @Story("Поиск 'eroshenkoam/allure-example' и проверка наличия Issue ")
    @DisplayName("Тест проверки наличия Issue #68 ")
    @Link(name = "Github", url = "https://github.com")
    public void checkIssue() {

        steps.openMainPage()
                .searchForRepository(REPOSITORY)
                .goToRepository(REPOSITORY)
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE_NUMBER)
                .takeScreenshot();
    }

}
