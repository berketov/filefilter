package shift.berketov.statistics;

import shift.berketov.filter.FilterByDataType;

public class FilteredDataStatistics implements Statistics {
    private final boolean isFullStatistics;
    private final IntStatistics intStat;
    private final FloatStatistics floatStat;
    private final StringStatistics stringStat;

    public FilteredDataStatistics(FilterByDataType filter, boolean isFullStatistics) {
        intStat = filter.getIntStat();
        floatStat = filter.getFloatStat();
        stringStat = filter.getStringStat();
        this.isFullStatistics = isFullStatistics;
    }

    @Override
    public void generateStatistics() {
        if (isFullStatistics) {
            generateFullStatistics();
        } else  {
            generateShortStatistics();
        }
    }

    private void generateShortStatistics() {
        System.out.println("Короткая статистика {" +
                "Количество элементов в файле " + intStat.getFullName() + " = " + intStat.getIntCounter() + ", " +
                "Количество элементов в файле " + floatStat.getFullName() + " = " + floatStat.getFloatCounter() + ", " +
                "Количество элементов в файле " + stringStat.getFullName() + " = " + stringStat.getStrCounter() +
                '}');
    }

    private void generateFullStatistics() {
        intStat.printFullStat();
        floatStat.printFullStat();
        stringStat.printFullStat();
    }
}

