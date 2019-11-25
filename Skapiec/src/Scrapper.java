import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrapper {

    ArrayList<String> urls;
    ArrayList<Double> ratings;
    ArrayList<Integer> ratingsCount;

    public Scrapper(String searchString) throws IOException {
        String url = parseURL(searchString);

        urls = new ArrayList<String>();
        ratings = new ArrayList<>();
        ratingsCount = new ArrayList<>();
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
//        if (urls.size() < 20) {
            for(String url1 : urls) {
                Document docDeeper = Jsoup.connect(url1).get();
                Elements datas = docDeeper.select("object.section-opinions");
                for (Element data : datas) {
                    String dataString = data.toString();

                    Pattern ratingsPattern = Pattern.compile("(\\d+(?:\\.\\d+))");
                    Matcher matchRatings = ratingsPattern.matcher(dataString);
                    while (matchRatings.find()) {
                        double d = Double.parseDouble(matchRatings.group(1));
                        ratings.add(d);
                    }
                    Pattern ratingsCountPattern = Pattern.compile("(?<![\\.\\d])[0-9]+(?![\\.\\d])");
                    Matcher matchCount = ratingsCountPattern.matcher(dataString);
                    while (matchCount.find()) {
                        int count = Integer.parseInt(matchCount.group(0));
                        ratingsCount.add(count);
                    }
                }
            }
        System.out.println(ratings);
//        }
    }

    private String parseURL(String searchString) {
        String searchable = searchString.replaceAll(" ", "+");
        return "https://www.skapiec.pl/szukaj/w_calym_serwisie/" + searchable + "/price";
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public Double getRatings() {
        return ratings.get(0);

    }

    public Integer getRatingsCount() {
        return ratingsCount.get(0);
    }
}