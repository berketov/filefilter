package shift.berketov.writer;

import shift.berketov.statistics.FilteredData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileWriter implements Writer {
    private final String pathOut;
    private final List<FilteredData> dataToWrite = new ArrayList<>();

    public FileWriter(List<FilteredData> allStatisticsData, String pathOut) {
        this.pathOut = pathOut;
        dataToWrite.addAll(allStatisticsData);
    }

    @Override
    public void write() {
        for (FilteredData data : dataToWrite) {
            List<String> lines = data.getData();
            if (lines.isEmpty()) {
                continue;
            } else {
                try (BufferedWriter writer = new BufferedWriter(
                        new java.io.FileWriter((pathOut + "/" + data.getFullName()), StandardCharsets.UTF_8))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Файл не удалось записать.");
                    break;
                }
            }
        }
    }
}
