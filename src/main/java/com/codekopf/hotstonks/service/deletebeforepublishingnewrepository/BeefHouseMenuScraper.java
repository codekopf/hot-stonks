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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codekopf.hotstonks.HotStonksApplication;
import com.codekopf.hotstonks.service.MenuScraper;

/**
 * Created by mstaffa on 11/12/2018
 */
public class BeefHouseMenuScraper implements MenuScraper {
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
            driver.get("http://beefhouse.cz/bistro/");

            WebDriverWait wait = new WebDriverWait(driver, 10);

            Calendar cal = Calendar.getInstance();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.THURSDAY || dayOfWeek == Calendar.FRIDAY) {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("carousel-item")));
                Actions move = new Actions(driver);
                Action action = move.dragAndDropBy(element, -50, 0).build();
                action.perform();
                action.perform();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

            String dayString = dayOfWeekMap.get(dayOfWeek);
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + dayString + "')]/../..")));

            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            // Get the location of element on the page
            Point point = ele.getLocation();

            // Get width and height of the element
            int eleWidth = ele.getSize().getWidth();
            int eleHeight = ele.getSize().getHeight();

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(),
                    eleWidth, eleHeight);
            ImageIO.write(eleScreenshot, "png", screenshot);

            // Copy the element screenshot to disk
            File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + "beefhouse-bistro.png");
            FileUtils.copyFile(screenshot, screenshotLocation);
        } finally {
            driver.quit();
        }
    }
}
