package com.testselenium.Services;

import com.testselenium.Login;
import com.testselenium.UserInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ScrappingService {
    WebDriver driver;
    WebDriverWait wait;
    public boolean login(Login details) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://academia.srmist.edu.in/");

        // switch frame to find the elements for login
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame("zohoiam");

        // email
        WebElement emailEle = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form[1]/div[1]/div[1]/div/span/input"));
        emailEle.sendKeys(details.getEmail());

        // next button
        WebElement nextBtn = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form[1]/div[1]/button"));
        nextBtn.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form[1]/div[1]/div[2]/div[2]/input"))));

        // password
        WebElement passwordEle = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form[1]/div[1]/div[2]/div[2]/input"));
        passwordEle.sendKeys(details.getPassword());

        // login button
        WebElement signinEle = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form[1]/div[1]/button"));
        signinEle.click();

        driver.switchTo().defaultContent();

        // wait until login completes and banner appears
        WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/div/a/div/span")));
        if (banner != null) return true;
        return false;
    }

    public UserInfo scrapeData() {
        WebElement dot = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/div[1]/div/div/ul/li[7]/a"));
        dot.click();

        WebElement menu = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/div[1]/div/div/ul/li[7]/div/ul/li[2]/a/div/span"));
        menu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[3]/div[1]/div/div/ul/li[7]/div/ul/li[2]/div/ul/li[2]/a")));
        WebElement tt = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/div[1]/div/div/ul/li[7]/div/ul/li[2]/div/ul/li[1]/a/div/span"));
        tt.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/div[3]/div[2]/div[2]/table[2]/tbody/tr/td/div/div[4]/div/table[1]/tbody/tr[1]/td[4]/strong")));
        WebElement name = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/div[2]/div[2]/table[2]/tbody/tr/td/div/div[4]/div/table[1]/tbody/tr[1]/td[4]/strong"));
        String stuName = name.getText();
        WebElement roll = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[3]/div[2]/div[2]/table[2]/tbody/tr/td/div/div[4]/div/table[1]/tbody/tr[1]/td[2]/strong"));
        String stuRoll = roll.getText();

        WebElement profileIcon = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[3]/div/div[1]/a/img"));
        profileIcon.click();

        WebElement email = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[2]/div[2]/a/span"));
        String stuEmail = email.getText();

        UserInfo user = new UserInfo();
        user.setName(stuName);
        user.setEmail(stuEmail);
        user.setRoll(stuRoll);

        return user;
    }
}