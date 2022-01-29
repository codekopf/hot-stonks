package com.codekopf.hotstonks.service.deletebeforepublishingnewrepository;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.codekopf.hotstonks.HotStonksApplication;
import com.codekopf.hotstonks.service.MenuScraper;

/**
 * Created by mstaffa on 16/07/2019
 */
public class ImageMenuScraper implements MenuScraper {
    private final String name;
    private final String imageUrl;

    public ImageMenuScraper(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    @Override
    public void getMenu() throws IOException {
        FileUtils.copyFile(new File(imageUrl), new File(HotStonksApplication.DOWNLOAD_FILE_PATH + name + ".png"));
    }
}
