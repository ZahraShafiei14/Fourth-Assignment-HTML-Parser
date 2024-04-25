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


public class ParserHockeyTeams {
    static List<HockeyTeams> hockeyTeams = new ArrayList<>();

    public List<HockeyTeams> sortByName() {
        List<HockeyTeams> sortedByNames = new ArrayList<>(hockeyTeams);
        sortedByNames.sort(Comparator.comparing(HockeyTeams::getName));
        return sortedByNames;
    }
    public List<HockeyTeams> sortByWins() {
        List<HockeyTeams> sortedByWins = new ArrayList<>(hockeyTeams);
        sortedByWins.sort(Comparator.comparing(HockeyTeams::getWins).reversed());
        return sortedByWins;
    }

    public List<HockeyTeams> sortByLoses() {
        List<HockeyTeams> sortedByLoses = new ArrayList<>(hockeyTeams);
        sortedByLoses.sort(Comparator.comparing(HockeyTeams::getLosses));
        return sortedByLoses;
    }

    public List<HockeyTeams> sortByYear() {
        List<HockeyTeams> sortedByYear = new ArrayList<>(hockeyTeams);
        sortedByYear.sort(Comparator.comparing(HockeyTeams::getYear));
        return sortedByYear;
    }

    public void setUp() throws IOException {
        File input = new File("src/Resources/Hockey Teams.html");
        Document doc = Jsoup.parse(input, "UTF-8");

        Elements teamsElement = doc.select("tr.team");
        for (Element team : teamsElement) {
                String name = team.select(".name").text().trim();
                int year = Integer.parseInt(team.select(".year").text().trim());
                int wins = Integer.parseInt(team.select(".wins").text().trim());
                int losses = Integer.parseInt(team.select(".losses").text().trim());
                int gf = Integer.parseInt(team.select(".gf").text().trim());
                int ga = Integer.parseInt(team.select(".ga").text().trim());
                HockeyTeams hockeyTeam = new HockeyTeams(name, year, wins, losses, gf, ga);
                hockeyTeams.add(hockeyTeam);
        }
    }
    
    public static void main(String[] args) {
        ParserHockeyTeams parser = new ParserHockeyTeams();
        try {
            parser.setUp();
        } catch (IOException exception) {
            System.err.println("Error reading HTML file: " + exception.getMessage());
            return;
        }

        System.out.println("Hockey teams sorted by: \n(1) Name\n(2) Wins\n(3) Losses\n(4) Year");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: hockeyTeams = parser.sortByName(); break;
            case 2: hockeyTeams = parser.sortByWins(); break;
            case 3: hockeyTeams = parser.sortByLoses(); break;
            case 4: hockeyTeams = parser.sortByYear(); break;
            default:
                System.out.println("Invalid choice!");
        }

        for (HockeyTeams team : hockeyTeams) {
            System.out.println(team.toString());
        }
    }
}