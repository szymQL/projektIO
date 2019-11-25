import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class testScrapper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.skapiec.pl/szukaj/w_calym_serwisie/samsung+galaxy+s8+64gb";
        ArrayList<String> urls = new ArrayList<String>();
        Document doc = Jsoup.connect(url).get();

        Elements links = doc.select("a");
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            if (absHref.startsWith("https://www.skapiec.pl/site/cat")) {
                if (!urls.contains(absHref)) {
                    urls.add(absHref);
                }
            }
        }
        Document docDeeper = Jsoup.connect(urls.get(0)).get();
        Element data = docDeeper.select("object.section-opinions").first();

        System.out.println(data.toString());
    }
}
