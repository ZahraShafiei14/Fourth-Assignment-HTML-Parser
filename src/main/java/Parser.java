import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        sortedByName.sort(Comparator.comparing(Country::getName));
        return sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        sortedByPopulation.sort(Comparator.comparingInt(Country::getPopulation).reversed());
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        sortedByArea.sort(Comparator.comparingDouble(Country::getArea).reversed());
        return sortedByArea;
    }

    public void setUp() throws IOException {
        File input = new File("src/Resources/country-list.html");
        Document doc = Jsoup.parse(input,"UTF-8");

        Elements countryElements = doc.select("div.country");
        for (Element countryElement : countryElements) {
            String name = countryElement.select(".country-name").text();
            String capital = countryElement.select(".country-capital").text();
            int population = Integer.parseInt(countryElement.select(".country-population").text());
            double area = Double.parseDouble(countryElement.select(".country-area").text());
            Country country = new Country(name, capital, population, area);
            countries.add(country);
        }
    }

    public static void main(String[] args) {
            Parser parser = new Parser();
            try { parser.setUp(); } catch (IOException exception) {}

            System.out.println("Countries sorted by: \n(1) Area\n(2) Name\n(3) Population");
            Scanner scanner = new Scanner(System.in);
            List<Country> countryList = new ArrayList<>(countries);
            switch (scanner.nextInt()) {
                case 1: countryList = parser.sortByArea(); break;
                case 2: countryList = parser.sortByName(); break;
                case 3: countryList = parser.sortByPopulation(); break;
                default: System.out.println("Invalid choice!");
            }
            for (Country country : countryList) {
                System.out.println("-"+country.toString());
            }
    }
}