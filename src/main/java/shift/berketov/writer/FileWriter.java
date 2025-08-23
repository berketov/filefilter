package shift.berketov.writer;

import shift.berketov.settings.Settings;
import shift.berketov.filter.FilterByDataType;
import shift.berketov.statistics.StatisticsData;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileWriter implements Writer {
    private final Settings settings;
    private final List<StatisticsData> allElementsForWrite = new ArrayList<>();

    public FileWriter(FilterByDataType filter, Settings settings) {
        this.settings = settings;
        allElementsForWrite.add(filter.getIntStat());
        allElementsForWrite.add(filter.getFloatStat());
        allElementsForWrite.add(filter.getStringStat());
    }

    @Override
    public void write() {
        for (StatisticsData data : allElementsForWrite) {
            List<String> lines = data.getDataFromStatistics();
            if (lines.isEmpty()) {
                continue;
            } else {
                try (BufferedWriter writer = new BufferedWriter(
                        new java.io.FileWriter((settings.getPathOut() + "/" + data.getName()), StandardCharsets.UTF_8))) {
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
