package shift.berketov.statistics;

import shift.berketov.settings.Settings;
import shift.berketov.filter.DataFilterImpl;

public class FilteredDataStatistics implements Statistics {
    private final Settings settings;
    private final IntStatistics intStat;
    private final FloatStatistics floatStat;
    private final StringStatistics stringStat;

    public FilteredDataStatistics(DataFilterImpl filter, Settings settings) {
        this.settings = settings;
        intStat = filter.getIntStat();
        floatStat = filter.getFloatStat();
        stringStat = filter.getStringStat();
    }

    @Override
    public void generateStatistics() {
        if (settings.hasFullStatistics()) {
            generateFullStatistics();
        } else  {
            generateShortStatistics();
        }
    }

    private void generateShortStatistics() {
        System.out.println("Короткая статистика {" +
                "Количество элементов в файле " + intStat.getName() + " = " + intStat.getIntCounter() + ", " +
                "Количество элементов в файле " + floatStat.getName() + " = " + floatStat.getFloatCounter() + ", " +
                "Количество элементов в файле " + stringStat.getName() + " = " + stringStat.getStrCounter() +
                '}');
    }

    private void generateFullStatistics() {
        intStat.printFullStat();
        floatStat.printFullStat();
        stringStat.printFullStat();
    }
}

