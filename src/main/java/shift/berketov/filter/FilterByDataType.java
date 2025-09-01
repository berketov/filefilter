package shift.berketov.filter;

import shift.berketov.statistics.FloatStatistics;
import shift.berketov.statistics.IntStatistics;
import shift.berketov.statistics.FilteredData;
import shift.berketov.statistics.StringStatistics;

import java.util.ArrayList;
import java.util.List;


public class FilterByDataType implements Filter {
    private final IntStatistics intStat = new IntStatistics();
    private final FloatStatistics floatStat = new FloatStatistics();
    private final StringStatistics stringStat = new StringStatistics();

    @Override
    public void filtration(List<String> allLinesFromFiles) {
        List<String> listForFiltration = new ArrayList<>(allLinesFromFiles);

        for (String s : listForFiltration) {
            if (isInteger(s)) {
                intStat.setDigit(Long.parseLong(s));
            } else if (isFloat(s)) {
                floatStat.setDigit(Double.parseDouble(s));
            } else {
                stringStat.setLine(s);
            }
        }
    }

    private static boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public IntStatistics getIntStat() {
        return intStat;
    }

    public FloatStatistics getFloatStat() {
        return floatStat;
    }

    public StringStatistics getStringStat() {
        return stringStat;
    }
    @Override
    public List<FilteredData> getFilteredData() {
        List<FilteredData> allStatElements = new ArrayList<>();
        allStatElements.add(intStat);
        allStatElements.add(floatStat);
        allStatElements.add(stringStat);
        return allStatElements;
    }
}

