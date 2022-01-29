package com.codekopf.hotstonks.service.utils;

import java.util.StringTokenizer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Strings;

public class AutomaticCssInliner {

    public static Document inlineStyles(Document document) {
        String style = "style";
        Elements els = document.select(style);// to get all the style elements
        for (Element e : els) {
            String styleRules = e.getAllElements().get(0).data().replaceAll("\n", "").trim();
            String delims = "{}";
            StringTokenizer st = new StringTokenizer(styleRules, delims);
            while (st.countTokens() > 1) {
                String selector = st.nextToken(), properties = st.nextToken();
                // Process selectors such as "a:hover"
                if (selector.indexOf(":") > 0) {
                    selector = selector.substring(0, selector.indexOf(':'));
                }
                if (Strings.isNullOrEmpty(selector)) {
                    continue;
                }
                Elements selectedElements = document.select(selector);
                for (Element selElem : selectedElements) {
                    String oldProperties = selElem.attr(style);
                    selElem.attr(
                            style,
                            oldProperties.length() > 0 ? concatenateProperties(oldProperties,
                                    properties) : properties);
                }
            }
            e.remove();
        }
        return document;
    }

    private static final String concatenateProperties(String oldProp, String newProp) {
        oldProp = oldProp.trim();
        if (!newProp.endsWith(";")) {
            newProp += ";";
        }
        return newProp + oldProp; // The existing (old) properties should take precedence.
    }
}
