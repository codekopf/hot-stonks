package com.codekopf.hotstonks.service.deletebeforepublishingnewrepository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
 * Created by mstaffa on 11/12/2018
 */
public class PivolodMenuScraper implements MenuScraper {
    private static final Map<Integer, String> dayOfWeekMap = new HashMap<>();

    static {
        dayOfWeekMap.put(Calendar.MONDAY, "PONDĚLÍ");
        dayOfWeekMap.put(Calendar.TUESDAY, "ÚTERÝ");
        dayOfWeekMap.put(Calendar.WEDNESDAY, "STŘEDA");
        dayOfWeekMap.put(Calendar.THURSDAY, "ČTVRTEK");
        dayOfWeekMap.put(Calendar.FRIDAY, "PÁTEK");
    }

    @Override
    public void getMenu() throws IOException {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-width=1920");
        options.addArguments("-height=1080");

        // Init WebDriver
        FirefoxDriver driver = new FirefoxDriver(options);
        try {
            driver.get("http://www.pivolod.cz/");

            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("site-navigation-fixed")));
            driver.executeScript("return arguments[0].remove();", menu);
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'POLEDNÍ MENU')]/../../../..")));
            driver.executeScript("arguments[0].scrollIntoView(true);", ele);
            sleep(500);

            Calendar cal = Calendar.getInstance();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            String dayString = dayOfWeekMap.get(dayOfWeek);
            WebElement changeDayEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(), '" + dayString + "')]")));
            changeDayEle.click();
            sleep(500);

            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            // Get the location of element on the page
            Point point = ele.getLocation();

            // Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), 0,
                    eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + "pivolod.png");
            FileUtils.copyFile(screenshot, screenshotLocation);
        } finally {
            driver.quit();
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
