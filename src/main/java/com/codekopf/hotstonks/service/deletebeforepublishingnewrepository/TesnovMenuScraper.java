package com.codekopf.hotstonks.service.deletebeforepublishingnewrepository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codekopf.hotstonks.HotStonksApplication;
import com.codekopf.hotstonks.service.MenuScraper;

/**
 * Created by mstaffa on 08/08/2019
 */
public class TesnovMenuScraper implements MenuScraper {

    @Override
    public void getMenu() throws IOException {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-width=1920");
        options.addArguments("-height=1080");

        // Init WebDriver
        FirefoxDriver driver = new FirefoxDriver(options);
        try {

            driver.get("https://lidovajidelna.cz");

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id = \'DenniMenu\']//div")));
            driver.executeScript("arguments[0].scrollIntoView(true);", ele);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            // Get the location of element on the page
            Point point = ele.getLocation();

            // Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = captureSubimage(fullImg, point, eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + "tesnov.png");
            FileUtils.copyFile(screenshot, screenshotLocation);

        } finally {
            driver.quit();
        }
    }

    protected BufferedImage captureSubimage(BufferedImage fullImg, Point point, int eleWidth, int eleHeight) {
        return fullImg.getSubimage(point.getX(), 0,
                eleWidth, eleHeight);
    }
}
