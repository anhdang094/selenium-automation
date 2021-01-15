package vn.betting.automation.core.service.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.betting.automation.core.selenium.Fb88;
import vn.betting.automation.core.service.BettingService;

import javax.transaction.Transactional;
import java.io.File;
import java.net.URL;

/**
 * @author anhdx
 */
@Service
@Transactional
public class BettingServiceImpl implements BettingService {

    private static WebDriver driver = null;

    private static final String CHROMEDRIVER = "chromedriver";

    @Value("${app.enviroment.driver}")
    private String driverPath;

    @Value("${appenv}")
    private String enviroment;

    @Override
    public void bettingSoccer() {
        try {
            System.setProperty("webdriver.chrome.driver", driverPath); // path of chromedriver
            ChromeOptions options = new ChromeOptions();
            if (enviroment.equals("development")) {
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
            }
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.navigate().to("https://www.fb88asia.com/vi-VN/Sportsbook/Sports");
            Thread.sleep(10000);
            Fb88 automation = new Fb88(driver);
            automation.runTest();
        } catch (Exception ex) {
            if (driver != null) {
                driver.quit();
            }
            ex.printStackTrace();
        }
    }
}
