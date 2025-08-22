package shift.berketov;

import shift.berketov.filter.DataFilter;
import shift.berketov.reader.Reader;
import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilterImpl;
import shift.berketov.reader.FileReader;
import shift.berketov.statistics.FilteredDataStatistics;
import shift.berketov.statistics.Statistics;
import shift.berketov.writer.FileWriter;
import shift.berketov.writer.Writer;

public class AppController {
    private final Settings settings;
    private Reader reader;
    private DataFilter filter;

    public AppController(Settings settings) {
        this.settings = settings;
    }

    public void launch() {
        runReader();
        fileFiltration();
        if (settings.hasShortStatistics()||settings.hasFullStatistics()) {
            getStatistics();
        }
        runWriter();
    }

    private void runReader() {
        reader = new FileReader(settings);
        reader.read();
    }

    private void fileFiltration() {
        filter = new DataFilterImpl(settings);
        filter.filtration(reader);
    }

    private void getStatistics() {
        Statistics statistics = new FilteredDataStatistics((DataFilterImpl) filter, settings);
        statistics.generateStatistics();
    }

    private void runWriter() {
        Writer writer = new FileWriter((DataFilterImpl) filter, settings);
        writer.write();
    }
}
