package csv;

import models.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvImporter {

    private static CSVFormat FORMAT = CSVFormat.newFormat(';');

    private File file;

    public CsvImporter(File file) {
        this.file = file;
    }

    public List<Player> readPlayers() throws IOException {

        List<Player> players = new ArrayList<>();

        CSVParser csvParser = CSVParser.parse(this.file, StandardCharsets.UTF_8, FORMAT.withHeader());

        csvParser.getRecords().forEach(record -> {
            System.out.println(record);
        });

        return players;
    }
}
