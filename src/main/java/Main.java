import csv.CsvImporter;
import database.WrongEntityTypeException;
import models.Match;
import models.Player;
import models.Team;
import xml.XmlImporter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File playerFile = new File("./res/Player-2018.csv");

        try {
            List<Player> players = new CsvImporter(playerFile).readPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File teamFile = new File("./res/teams-2018.xml");

        File matchFile = new File("./res/matches-2018.xml");

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
