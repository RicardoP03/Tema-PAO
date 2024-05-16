package Servicess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CSVWriterReader {
    private BufferedWriter csvWriter;

    private String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public CSVWriterReader(String filePath) {
        try {
            csvWriter = new BufferedWriter(new FileWriter(filePath, true));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void exportToCSV(String data) {
        try {
            csvWriter.write(data);
            csvWriter.write(",");
            csvWriter.write(this.getFormattedDateTime());
            csvWriter.write("\n");
            csvWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
