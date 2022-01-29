package com.codekopf.hotstonks.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codekopf.hotstonks.HotStonksApplication;

public class FinvizScrapper implements MenuScraper {

    private final String urlSuffix;
    private final String name;
    private final String date;

    /**
     *
     * @param name
     * @param urlSuffix
     * @param date - date in yyyy-mm-dd string format for which the scrapping is happening
     */
    public FinvizScrapper(String name, String urlSuffix, String date) {
        this.urlSuffix = urlSuffix;
        this.name = name;
        this.date = date;
    }

    @Override
    public void getMenu() throws IOException {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-width=1920");
        options.addArguments("-height=1080");

        FirefoxDriver driver = new FirefoxDriver(options);
        try {

            driver.get("https://finviz.com/quote.ashx?t=" + urlSuffix + "&ty=c&ta=1&p=d&tas=0");
            getDailyGraph(driver);
            driver.get("https://finviz.com/quote.ashx?t=" + urlSuffix + "&ty=c&ta=1&p=w&tas=0");
            getWeeklyGraph(driver);

        } finally {
            driver.quit();
        }
    }

    private void getDailyGraph(FirefoxDriver driver) throws IOException { // TODO replace FirefoxDrive to interface
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ConsentManager__Overlay-np32r2-0"))); // ConsentManager__Overlay-np32r2-0 dibSJp <-- This can change
        driver.executeScript("return arguments[0].remove();", menu);

        List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("interactive-chart")));
        if (webElements.isEmpty()) {
            return;
        }
        WebElement webElement = webElements.get(0);
        Long scrollYPos = (Long) driver.executeScript("arguments[0].scrollIntoView(true); return window.scrollY;", webElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        Point point = webElement.getLocation();

        // Get width and height of the element
        int webElementWidth = webElement.getSize().getWidth();
        int webElementHeight = webElement.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = captureSubimage(fullImg, point, scrollYPos.intValue(), webElementWidth, webElementHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);

        // Copy the element screenshot to disk
        File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + date + "\\" + name + "-d.png");
        FileUtils.copyFile(screenshot, screenshotLocation);
    }

    private void getWeeklyGraph(FirefoxDriver driver) throws IOException { // TODO replace FirefoxDrive to interface
        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ConsentManager__Overlay-np32r2-0"))); // ConsentManager__Overlay-np32r2-0 dibSJp <-- This can change aand
        driver.executeScript("return arguments[0].remove();", menu);

        List<WebElement> webElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("interactive-chart")));
//            driver.executeScript("return arguments[0].remove();", eles);
//            JavascriptExecutor js;
//            if (driver instanceof JavascriptExecutor) {
//                js = (JavascriptExecutor) driver;
//            }
//            js.executeScript("return document.getElementsByClassName('ConsentManager__Overlay-np32r2-0')[0].remove();"); // ConsentManager__Overlay-np32r2-0 dibSJp <-- This can change aand
        if (webElements.isEmpty()) {
            return;
        }
        WebElement webElement = webElements.get(0);
        Long scrollYPos = (Long) driver.executeScript("arguments[0].scrollIntoView(true); return window.scrollY;", webElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        Point point = webElement.getLocation();

        // Get width and height of the element
        int webElementWidth = webElement.getSize().getWidth();
        int webElementHeight = webElement.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = captureSubimage(fullImg, point, scrollYPos.intValue(), webElementWidth, webElementHeight);
        ImageIO.write(eleScreenshot, "png", screenshot);

        // Copy the element screenshot to disk
        File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + date + "\\" + name + "-w.png");
        FileUtils.copyFile(screenshot, screenshotLocation);
    }

    protected BufferedImage captureSubimage(BufferedImage fullImg, Point point, int scrollPostion, int eleWidth, int eleHeight) {
        return fullImg.getSubimage(point.getX(), point.getY() - scrollPostion,
                eleWidth, eleHeight);
    }

}
