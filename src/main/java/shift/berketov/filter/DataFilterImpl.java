package shift.berketov.filter;

import shift.berketov.reader.Reader;
import shift.berketov.statistics.FloatStatistics;
import shift.berketov.statistics.IntStatistics;
import shift.berketov.statistics.StringStatistics;

public class DataFilterImpl implements DataFilter {
    private final IntStatistics intStat = new IntStatistics();
    private final FloatStatistics floatStat = new FloatStatistics();
    private final StringStatistics stringStat = new StringStatistics();
    @Override
    public void filtration(Reader reader) {
        for (String s : reader.getAllLinesFromFiles()) {
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


}

