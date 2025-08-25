package shift.berketov.filter;

import shift.berketov.reader.Reader;
import shift.berketov.settings.Settings;
import shift.berketov.statistics.FloatStatistics;
import shift.berketov.statistics.IntStatistics;
import shift.berketov.statistics.StringStatistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilterByDataType implements Filter {
    Settings settings;
    private final IntStatistics intStat = new IntStatistics();
    private final FloatStatistics floatStat = new FloatStatistics();
    private final StringStatistics stringStat = new StringStatistics();

    public FilterByDataType(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void filtration(Reader reader) {
        List<String> listForFiltration = new ArrayList<>(reader.getAllLinesFromFiles());
        if (settings.isAppendMode()) {
            updateContent(listForFiltration);
        }

        for (String s : listForFiltration) {
            if (isInteger(s)) {
                intStat.setDigit(Long.parseLong(s));
            } else if (isFloat(s)) {
                floatStat.setDigit(Double.parseDouble(s));
            } else {
                stringStat.setLine(s);
            }
        }
        if (settings.hasFileNamePrefix()) {
            setPrefix();
        }
    }

    private void updateContent(List<String> listForFiltration) {
        System.out.println("Введите данные через запятую и нажмите 'enter':");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        if (in.isEmpty()) {
            System.out.println("Вы ничего не ввели. \n");
            return;
        } else {
            String[] words = in.split(",|, ");
            listForFiltration.addAll(Arrays.asList(words));
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

    private void setPrefix() {
        intStat.setName(settings.getFileNamePrefix() + intStat.getName());
        floatStat.setName(settings.getFileNamePrefix() + floatStat.getName());
        stringStat.setName(settings.getFileNamePrefix() + stringStat.getName());
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

