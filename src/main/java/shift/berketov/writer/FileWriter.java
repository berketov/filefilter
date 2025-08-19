package shift.berketov.writer;

import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilter;
import cft.bsd.statistics.*;
import shift.berketov.statistics.FloatStatistics;
import shift.berketov.statistics.IntStatistics;
import shift.berketov.statistics.StatisticsData;
import shift.berketov.statistics.StringStatistics;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileWriter implements Writer{
    private DataFilter filter;
    private Settings settings;

    public FileWriter(DataFilter filter, Settings config) {
        this.filter = filter;
        this.settings = config;
        intStat = filter.getIntStat();
        floatStat = filter.getFloatStat();
        stringStat = filter.getStringStat();
    }

    private IntStatistics intStat;
    private FloatStatistics floatStat;
    private StringStatistics stringStat;
    private List<StatisticsData> allElementsForWrite = new ArrayList<>();

    @Override
    public void write() {
        fillElementsListForWrite();

        if (settings.hasFileNamePrefix()) {
            setPrefix(settings, allElementsForWrite);
        }

        if (settings.hasNewPathForFile()) {
            setNewPathOut(settings, allElementsForWrite);
        }

        for (StatisticsData data : allElementsForWrite) {
            List<String> lines = data.getDataFromStatistics();
            if (lines.isEmpty()) {
                continue;
            } else {
                try (BufferedWriter writer = new BufferedWriter(
                        new java.io.FileWriter(data.getPath(), StandardCharsets.UTF_8))) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Failed to write file");
                    break;
                }
            }
        }
    }

    private void fillElementsListForWrite() {
        allElementsForWrite.add(intStat);
        allElementsForWrite.add(floatStat);
        allElementsForWrite.add(stringStat);
    }

    private void setPrefix (Settings settings, List<StatisticsData> data) {
        String nameWithPrefix;
        String pfx = settings.getFileNamePrefix();
        for (String currentFile : settings.getPaths()) {
            if (currentFile.equals(pfx)) {
                System.out.println("The prefix is the same as the filename to filter. " +
                                    "You may have forgotten the prefix.");
            }
        }
        if (!"".equals(pfx)) {
            for (StatisticsData item : data) {
                nameWithPrefix = pfx + item.getName();
                item.setName(nameWithPrefix);
                item.setPath(nameWithPrefix);
            }
        } else  {
            System.out.println("After the -p option, enter a prefix for the filename");
        }
    }

    private void setNewPathOut(Settings settings, List<StatisticsData> data) {
        String newPath;
        String in = settings.getPathOut();
        if (!Files.exists(Path.of(in))) {
            System.out.println("If you want change the default path for saving files,\n" +
                                "you must enter a new valid path (within quotation marks) after the '-o' parameter");
            return;
        }else {
            for (StatisticsData element : data) {
                newPath = in + "/" + element.getName();
                element.setPath(newPath);
            }
        }
    }
}
