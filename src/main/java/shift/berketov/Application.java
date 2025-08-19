package shift.berketov;

import shift.berketov.settings.Settings;

public class Application {
    public static void main(String[] args) {
        Settings settings = new Settings(args);
        AppController controller = new AppController(settings);
        controller.launch();
    }
}