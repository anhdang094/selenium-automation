package vn.betting.automation.core.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.SessionId;

import java.util.Set;

@SuppressWarnings("Duplicates")
public class Fb88 extends AbstractEventHandler {

    public Fb88(WebDriver driver) {
        super(driver);
    }

    public void runTest() throws Exception {

        inputOrder("//*[@id=\"loginForm\"]/div[1]/input[2]", "anhdang10110", 1000, "username");

        inputOrder("//*[@id=\"loginForm\"]/div[1]/input[3]", "Demos@12", 1000, "password");

        clickOrder("//*[@id=\"btnLoginSubmit\"]", 5000, "submit dang nhap");

        System.out.println("Done dang nhap");

        // dong popup
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("lightbox_close();");

        Thread.sleep(5000);

        //switch to iframe
        driver.switchTo().frame("iframe");

        Thread.sleep(5000);

        //click only bong da
        clickOrder("//*[@id=\"sp1\"]", 2000, "click bong da");

        SessionId session = ((ChromeDriver) driver).getSessionId();
        driver.manage().getCookies();
        // setup the request
        sendPostRequest(session.toString());

        //chon doi
        clickOrder("//*[@id=\"o3208076471\"]", 3000, "click chon doi");

        //input tien
        inputOrder("//*[@id=\"lt-left\"]/div[3]/div/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div[1]/div/input", "10000", 2000, "input tien");

        //dat cuoc
        clickOrder("//*[@id=\"lt-left\"]/div[3]/div/div/div[3]/div[2]/div/div[2]/div[7]/div[1]/div/div", 2000, "dat cuoc");


        Thread.sleep(10000);
    }

    public void sendPostRequest(String sessionId) {
        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();
        for (String logType : logTypes) {
            LogEntries les = driver.manage().logs().get(logType);
            for (LogEntry le : les) {
                System.out.println(le.getMessage());
            }
        }
    }

}
