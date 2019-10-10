package de.jonashackt.springbootvuejs.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("phoneCountryCodeProvider ")
public class PhoneCountryCodeProvider {

    private Map<String, String> phoneCodeCountryMap;

    PhoneCountryCodeProvider() {
        phoneCodeCountryMap = new HashMap<>();
    }

    @PostConstruct
    private void initData() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_country_calling_codes").get();
        Elements countryCodeTable = doc.getElementsByClass("wikitable");
        Elements trs = countryCodeTable.get(1).getElementsByTag("tr");
        trs.remove(0);
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            Element countryNameElement = tds.get(0);
            Element countryPhoneCodeElement = tds.get(1).getElementsByTag("a").get(0);
            String text = countryPhoneCodeElement.text();
            if (!StringUtils.isEmpty(text)) {
                text = text.replaceAll("\\+", "");
                text = text.replaceAll(" ", "");
                phoneCodeCountryMap.put(text, countryNameElement.text());
            }
        }
        System.out.println("Countries and codes loaded!");
    }

    public Map<String, String> getPhoneCodeCountryMap() {
        return phoneCodeCountryMap;
    }
}