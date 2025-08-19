package shift.berketov;

import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilter;
import shift.berketov.reader.FileReader;
import shift.berketov.statistics.FilteredDataStatistics;
import shift.berketov.statistics.Statistics;
import shift.berketov.writer.FileWriter;

public class AppController {
    private final Settings settings;
    private FileReader reader;
    private DataFilter filter;

    public AppController(Settings settings) {
        this.settings = settings;
    }

    public void launch() {
        runReader();
        fileFiltration();
        if (settings.hasShortStatistics()) {
            getStatistics();
        }
        runWriter();
    }

    private void runReader() {
        reader = new FileReader(settings);
        reader.read();
    }

    private void fileFiltration() {
        filter = new DataFilter();
        filter.filtration(reader);
    }

    private void getStatistics () {
        Statistics statistics = new FilteredDataStatistics(filter, settings);
        statistics.generateStatistics();
    }

    private void runWriter() {
        FileWriter writer = new FileWriter(filter, settings);
        writer.write();
    }
}
