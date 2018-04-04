import database.WrongEntityTypeException;
import models.Match;
import models.Team;
import xml.XmlImporter;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File teamFile = new File("C:\\Users\\morit\\IdeaProjects\\soccerxmlverarbeitung\\src\\main\\resources\\teams-2018.xml");

        File matchFile = new File("C:\\Users\\morit\\IdeaProjects\\soccerxmlverarbeitung\\src\\main\\resources\\matches-2018.xml");

        List<Team> teams = new XmlImporter(teamFile).readTeams();

        List<Match> matches = new XmlImporter(matchFile).readMatches();

        teams.forEach(team-> {
            try {
                team.store();
            } catch (WrongEntityTypeException e) {
                e.printStackTrace();
            }
        });

        matches.forEach(match -> {
            try {
                match.store();
            } catch (WrongEntityTypeException e) {
                e.printStackTrace();
            }
        });
    }
}
