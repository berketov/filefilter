package shift.berketov;

import shift.berketov.filter.Filter;
import shift.berketov.reader.ConsoleReader;
import shift.berketov.reader.Reader;
import shift.berketov.settings.ContentUpdater;
import shift.berketov.settings.Settings;
import shift.berketov.filter.FilterByDataType;
import shift.berketov.reader.FileReader;
import shift.berketov.statistics.FilteredDataStatistics;
import shift.berketov.statistics.Statistics;
import shift.berketov.statistics.StatisticsData;
import shift.berketov.writer.FileWriter;
import shift.berketov.writer.Writer;

import java.util.List;

public class AppController {
    private final Settings settings;
    private Reader reader;
    private Filter filter;

    public AppController(Settings settings) {
        this.settings = settings;
    }

    public void launch() {
        List<String> filesContent = readFiles();

        if (settings.isAppendMode()) {
            List<String> linesToAdd = readConsole(filesContent);
            ContentUpdater updater = new ContentUpdater();
            filesContent = updater.append(filesContent, linesToAdd);
        }

        fileFiltration(filesContent);
        addPrefix();

        if (settings.hasShortStatistics() || settings.hasFullStatistics()) {
            getStatistics();
        }

        runWriter();
    }

    private List<String> readFiles() {
        reader = new FileReader(settings.getPaths());
        reader.read();
        return reader.getAllReadData();
    }

    private List<String> readConsole(List<String> content) {
        reader = new ConsoleReader(content);
        reader.read();
        return reader.getAllReadData();
    }

    private void fileFiltration(List<String> filesContent) {
        filter = new FilterByDataType();
        filter.filtration(filesContent);
    }

    private void addPrefix() {
        for (StatisticsData data : filter.getAllStatisticsData()) {
            data.setPrefix(settings.getFileNamePrefix());
        }
    }

    private void getStatistics() {
        Statistics statistics = new FilteredDataStatistics((FilterByDataType) filter, settings.hasFullStatistics());
        statistics.generateStatistics();
    }

    private void runWriter() {
        Writer writer = new FileWriter(filter.getAllStatisticsData(), settings.getPathOut());
        writer.write();
    }
}
