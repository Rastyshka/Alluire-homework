package com.github.allure.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public WebSteps openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Ищем репозиторий")
    public WebSteps searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
        return this;
    }

    @Step("Переходим в репозиторий")
    public WebSteps goToRepository(String repository) {
        $(linkText(repository)).click();
        return this;
    }

    @Step("Открываем Issues")
    public WebSteps openIssueTab() {
        $(partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с номером {number}")
    public WebSteps shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number)).should(Condition.visible);
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[]  takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs((OutputType.BYTES));
    }
}
