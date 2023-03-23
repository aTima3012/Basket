import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private final List<String> logList = new ArrayList<>();

    public void log(int productNum, int amount) {
        logList.add(productNum + "," + amount);
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(txtFile, true),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END)) {

            if (txtFile.length() == 0) {
                csvWriter.writeNext("productNum,amount".split(","));
            }
            for (String s : logList) {
                csvWriter.writeNext(s.split(","));
            }
        }
    }
}

