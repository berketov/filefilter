package shift.berketov.statistics;

import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilter;

public class FilteredDataStatistics implements Statistics {
    private DataFilter filter;
    private Settings settings;

    public FilteredDataStatistics(DataFilter filter, Settings config) {
        this.filter = filter;
        this.settings = config;
        intStat = filter.getIntStat();
        floatStat = filter.getFloatStat();
        stringStat = filter.getStringStat();
    }

    private IntStatistics intStat;
    private FloatStatistics floatStat;
    private StringStatistics stringStat;

    @Override
    public void generateStatistics() {
        if (settings.hasFullStatistics()) {
            generateFullStatistics();
        } else  {
            generateShortStatistics();
        }
    }

    private void generateShortStatistics() {
        System.out.println("Short stats {" +
                "Number of items in file " + intStat.getName() + " = " + intStat.getIntCounter() + ", " +
                "Number of items in file " + floatStat.getName() + " = " + floatStat.getFloatCounter() + ", " +
                "Number of items in file " + stringStat.getName() + " = " + stringStat.getStrCounter() +
                '}');
    }

    private void generateFullStatistics() {
        intStat.printFullStat();
        floatStat.printFullStat();
        stringStat.printFullStat();
    }
}

