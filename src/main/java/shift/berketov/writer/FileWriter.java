package shift.berketov.writer;

import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilterImpl;
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

public class FileWriter implements Writer {
    private final Settings settings;
    private final List<StatisticsData> allElementsForWrite = new ArrayList<>();

    public FileWriter(DataFilterImpl filter, Settings settings) {
        this.settings = settings;
        allElementsForWrite.add(filter.getIntStat());
        allElementsForWrite.add(filter.getFloatStat());
        allElementsForWrite.add(filter.getStringStat());
    }

    @Override
    public void write() {
        if (settings.hasFileNamePrefix()) {
            setPrefix(settings, allElementsForWrite);
        }

//        if (settings.hasNewPathForFile()) {
//            setNewPathOut(settings, allElementsForWrite);
//        } //todo заменил на изменение параметра в BufferedWriter

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

    //todo заполнил в конструкторе
//    private void fillElementsListForWrite() {
//        allElementsForWrite.add(intStat);
//        allElementsForWrite.add(floatStat);
//        allElementsForWrite.add(stringStat);
//    }

    private void setPrefix(Settings settings, List<StatisticsData> data) {
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
        } else {
            System.out.println("After the -p option, enter a prefix for the filename");
        }
    }

//    private void setNewPathOut(Settings settings, List<StatisticsData> data) {
//        String newPath;
//        String in = settings.getPathOut();
//        if (!Files.exists(Path.of(in))) {
//            System.out.println("If you want change the default path for saving files,\n" +
//                    "you must enter a new valid path (within quotation marks) after the '-o' parameter");
//            return;
//        } else {
//            for (StatisticsData item : data) {
//                newPath = in + "/" + item.getName();
//                item.setPath(newPath);
//            }
//        }
//    }
}
