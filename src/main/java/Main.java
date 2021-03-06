import csv.CsvImporter;
import models.Match;
import models.Player;
import models.Team;
import xml.XmlImporter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        File playerFile = new File("./src/main/resources/Player-2018.csv");

        List<Player> players = new ArrayList<>();

        try {
            players = new CsvImporter(playerFile).readPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        players.forEach(player -> {
            System.out.println(player);
        });

        File teamFile = new File("./src/main/resources/teams-2018.xml");

        File matchFile = new File("./src/main/resources/matches-2018.xml");

        List<Team> teams = new XmlImporter(teamFile).readTeams();

        List<Match> matches = new XmlImporter(matchFile).readMatches();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerpersistence");
        EntityManager entityManager = emf.createEntityManager();

        matches.forEach(match -> {
            entityManager.getTransaction().begin();
            entityManager.persist(match);
            entityManager.getTransaction().commit();
        });

        entityManager.close();
    }
}
