package com.codekopf.hotstonks.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockTitle {

    private CrawlingSource crawlingSource;
    private String ticker;
    private String name;

    public static StockTitle of(final CrawlingSource crawlingSource, final String ticker, final String name) {
        return new StockTitle(crawlingSource, ticker, name);
    }
}
