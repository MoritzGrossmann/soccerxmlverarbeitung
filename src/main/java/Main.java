import csv.CsvImporter;
import database.WrongEntityTypeException;
import database.interfaces.Entity;
import models.Match;
import models.Player;
import models.Team;
import xml.XmlImporter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        });

        EntityManager entityManager = Persistence.createEntityManagerFactory("soccerpersistence").createEntityManager();

        matches.forEach(match -> {
            entityManager.getTransaction().begin();
            entityManager.persist(match);
            entityManager.getTransaction().commit();
        });

        entityManager.close();
    }
}
