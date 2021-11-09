package com.github.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class CheckIssueLambda extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final Integer ISSUE_NUMBER = 68;

    @Test
    public void checkIssueTest() {

        AllureLifecycle lifecycle = Allure.getLifecycle();

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
            lifecycle.addAttachment("Screenshot", "img/png", "png", takeScreenshot());
        });
    }

    private byte[] takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs((OutputType.BYTES));
    }
}