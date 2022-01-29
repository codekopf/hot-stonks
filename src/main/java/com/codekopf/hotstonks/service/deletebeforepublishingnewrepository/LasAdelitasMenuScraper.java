package com.codekopf.hotstonks.service.deletebeforepublishingnewrepository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.openqa.selenium.Point;

import com.codekopf.hotstonks.HotStonksApplication;
import com.codekopf.hotstonks.service.MenuScraper;

/**
 * Created by mstaffa on 11/12/2018
 */
public class LasAdelitasMenuScraper implements MenuScraper {
    private static final String IMAGE_URL = "http://www.lasadelitas.cz/data/files/DM_{0}.png";

    @Override
    public void getMenu() throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMd_'{0}'_yyyy", Locale.ENGLISH);
        String dateUrlStringWithoutEndDay = sdf.format(cal.getTime());
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        String dateUrlString = MessageFormat.format(dateUrlStringWithoutEndDay, cal.get(Calendar.DATE));

        String url = MessageFormat.format(IMAGE_URL, dateUrlString);
        File screenshotLocation = new File(HotStonksApplication.DOWNLOAD_FILE_PATH + "las-adelitas.png");
        BufferedImage screenshot = ImageIO.read(new URL(url));
        ImageIO.write(screenshot, "png", screenshotLocation);
    }

    protected BufferedImage captureSubimage(BufferedImage fullImg, Point point, int eleWidth, int eleHeight) {
        return fullImg.getSubimage(point.getX(), 0,
                eleWidth, eleHeight);
    }

}
