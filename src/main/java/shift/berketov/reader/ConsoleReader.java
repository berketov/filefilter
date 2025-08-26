package shift.berketov.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader implements Reader{
    List<String> extraDataFromScanner = new ArrayList<>();

    public ConsoleReader(List<String> dataFromFromReader) {
        this.extraDataFromScanner = dataFromFromReader;
    }

    @Override
    public void read() {
        System.out.println("Введите данные через запятую и нажмите 'enter':");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        if (in.isEmpty()) {
            System.out.println("Вы ничего не ввели. \n");
            return;
        } else {
            String[] words = in.split(",|, ");
            extraDataFromScanner.addAll(Arrays.asList(words));
        }
    }

    @Override
    public List<String> getAllReadData() {
        return extraDataFromScanner;
    }
}
