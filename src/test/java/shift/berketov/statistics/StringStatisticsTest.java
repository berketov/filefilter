package shift.berketov.statistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringStatisticsTest {
    StringStatistics stringStatistics = new StringStatistics();
    @Test
    public void setPrefix() {
        String fileName = "strings.txt";
        String prefix = "result_";

        stringStatistics.setPrefix("result_");

        String actual = stringStatistics.getFullName();

        String expected =  prefix + fileName;

        Assertions.assertEquals(expected, actual);
    }
}