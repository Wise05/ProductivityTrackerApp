import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    static LocalDateTime lastRecordedTime = LocalDateTime.now();
    static String fileName = "";

    public static void uploadDataBasedOnTime(KeystrokeDataCollector keystrokeDataCollector, MouseDataCollector mouseDataCollector) {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() > lastRecordedTime.getDayOfMonth() ||
                now.getMonthValue() > lastRecordedTime.getMonthValue() ||
                now.getYear() > lastRecordedTime.getYear() ||
                fileName.isEmpty()) {
            fileName = now.getDayOfYear() + " " + now.getYear();
        }

        if (now.getMinute() != lastRecordedTime.getMinute()) {
            lastRecordedTime = now;
            uploadNums(keystrokeDataCollector, mouseDataCollector);
        }
    }

    public static void uploadNums(KeystrokeDataCollector keystrokeDataCollector, MouseDataCollector mouseDataCollector) {
        try {
            File file = new File("C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\data_logs\\" + fileName + ".txt");
            boolean fileCreated = file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true); // Open in append mode
            fileWriter.write(LocalDateTime.now() + " " +
                    keystrokeDataCollector.getEnterCount() + " " +
                    keystrokeDataCollector.getSpaceBarCount() + " " +
                    mouseDataCollector.getNumberClicks()  + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        keystrokeDataCollector.setEnterCount(0);
        keystrokeDataCollector.setSpaceBarCount(0);
        mouseDataCollector.setNumberClicks(0);
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        KeystrokeDataCollector keystrokeDataCollector = new KeystrokeDataCollector();
        MouseDataCollector mouseDataCollector = new MouseDataCollector();

        GlobalScreen.addNativeKeyListener(keystrokeDataCollector);
        GlobalScreen.addNativeMouseListener(mouseDataCollector);

        new Thread(() -> {
            while (true) {
                uploadDataBasedOnTime(keystrokeDataCollector, mouseDataCollector);
            }
        }).start();
    }
}
