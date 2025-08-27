package shift.berketov.reader;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader implements Reader{
    private final List<String> data;

    public ConsoleReader(List<String> dataFromFromReader) {
        this.data = dataFromFromReader;
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
            data.addAll(Arrays.asList(words));
        }
    }

    @Override
    public List<String> getAllReadData() {
        return data;
    }
}
