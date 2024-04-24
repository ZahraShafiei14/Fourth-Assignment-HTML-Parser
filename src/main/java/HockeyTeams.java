public class HockeyTeams {
    private String name;
    private int year;
    private int wins;
    private int losses;
    private int GF;
    private int GA;

    public HockeyTeams(String name, int year, int wins, int losses, int GF, int GA) {
        this.name = name;
        this.year = year;
        this.wins = wins;
        this.losses = losses;
        this.GF = GF;
        this.GA = GA;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    @Override
    public String toString() {
        return "- HockeyTeams:" +
                " Name: " + name +
                ", Year: " + year +
                ", Wins: " + wins +
                ", Losses: " + losses +
                ", Goals For (GF): " + GF +
                ", Goals Against (GA): " + GA ;
    }
}
