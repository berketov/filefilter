package shift.berketov.filter;

import shift.berketov.statistics.FilteredData;

import java.util.List;

public interface Filter {
    void filtration(List<String> dataForFiltration);
    List<FilteredData> getFilteredData();
}
