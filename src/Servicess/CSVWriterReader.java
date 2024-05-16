package Servicess;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
public class CSVWriterReader {
    private  FileWriter csvWriter;

    private String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

    public CSVWriterReader(String filePath, String auxFile) {
        try {
            moveToAuxiliar(filePath, auxFile);
            csvWriter = new FileWriter(filePath);
            moveFromAuxiliar(auxFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveToAuxiliar(String filePath, String auxFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(auxFile));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void moveFromAuxiliar(String auxFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(auxFile));
            String line;
            while ((line = reader.readLine()) != null) {
                csvWriter.write(line);
                csvWriter.write("\n");
            }
            csvWriter.flush();
        }
        catch(Exception e) {
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
