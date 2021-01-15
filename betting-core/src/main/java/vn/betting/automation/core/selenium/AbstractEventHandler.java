package vn.betting.automation.core.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractEventHandler {
    public WebDriver driver;
    public String error = "";

    public String getError() {
        return error;
    }

    public AbstractEventHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrder(String btnXPath, int time, String location) throws InterruptedException {
        try {
            driver.findElement(By.xpath("." + btnXPath)).click();
            Thread.sleep(time);
        } catch (Exception ex){
            error = location;
            ex.printStackTrace();
            throw ex;
        }
    }

    public void submitOrder(String btnXPath, int time, String location) throws InterruptedException {
        try {
            driver.findElement(By.xpath("." + btnXPath)).click();
            Thread.sleep(time);
        } catch (Exception ex){
            error = location;
            ex.printStackTrace();
            throw ex;
        }
    }

    public void clickOrder(int time, String location) throws InterruptedException {
        try {
            driver.getWindowHandles().size();
            Thread.sleep(time);
        } catch (Exception ex){
            error = location;
            ex.printStackTrace();
            throw ex;
        }
    }

    public void inputOrder(String inputXPath, String key, int time, String location) throws InterruptedException {
        try {
            driver.findElement(By.xpath("." + inputXPath)).sendKeys(key);
            Thread.sleep(time);
        } catch (Exception ex){
            error = location;
            ex.printStackTrace();
            throw ex;
        }

    }

    public void clear(String inputXPath, int time, String location) throws InterruptedException {
        try {
            driver.findElement(By.xpath("." + inputXPath)).clear();
            Thread.sleep(time);
        } catch (Exception ex){
            error = location;
            ex.printStackTrace();
            throw ex;
        }
    }

    public String getText(String btnXPath, int time, String location) throws InterruptedException {
        try {
             Thread.sleep(time);
             return driver.findElement(By.xpath("." + btnXPath)).getText();
        } catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }
    public String getText(String btnXPath) throws InterruptedException {
        try {
            return driver.findElement(By.xpath("." + btnXPath)).getText();

        } catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }
    public void setError(String error) {
        this.error = error;
    }
}
