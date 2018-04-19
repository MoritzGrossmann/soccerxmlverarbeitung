package csv;

import models.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvImporter {

    private static CSVFormat FORMAT = CSVFormat.newFormat(';');

    private File file;

    public CsvImporter(File file) {
        this.file = file;
    }

    public List<Player> readPlayers() throws IOException {

        List<Player> players = new ArrayList<>();

        Reader reader = new FileReader(this.file);

        Iterable<CSVRecord> records = CSVFormat
                .newFormat(';')
                .withHeader("TeamId", "TrikotNr", "Name", "Geburtstag","Minuten", "Tore", "Gelbe", "GelbRot", "Rote")
                .withSkipHeaderRecord()
                .parse(reader);

        for (CSVRecord record : records) {
                Player player = new Player();
                player.setTeamId(Integer.parseInt(record.get("TeamId")));
                try {
                    player.setTrikotNr(Integer.parseInt(record.get("TrikotNr")));
                } catch (NumberFormatException e) {
                    player.setTrikotNr(0);
                }
                player.setName(record.get("Name"));

                try {
                    player.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(record.get("Geburtstag")));
                } catch (ParseException e) {
                    player.setBirthDate(new Date(0L));
                }

                player.setMinutes(Integer.parseInt(record.get("Minuten")));
                player.setGoals(Integer.parseInt(record.get("Tore")));
                player.setYellowCards(Integer.parseInt(record.get("Gelbe")));
                player.setYellowRedCards(Integer.parseInt(record.get("GelbRot")));
                player.setRedCards(Integer.parseInt(record.get("Rote")));
                players.add(player);
        }

        return players;
    }
}
