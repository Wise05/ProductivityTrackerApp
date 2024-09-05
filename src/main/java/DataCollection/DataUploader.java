package DataCollection;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class DataUploader {
    private static LocalDateTime lastRecordedTime = LocalDateTime.now();
    private static String fileName = "";

    public static void uploadDataBasedOnTime(KeystrokeDataCollector keystrokeDataCollector, MouseDataCollector mouseDataCollector) throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();

        if (now.getDayOfMonth() > lastRecordedTime.getDayOfMonth() ||
                now.getMonthValue() > lastRecordedTime.getMonthValue() ||
                now.getYear() > lastRecordedTime.getYear() ||
                fileName.isEmpty()) {
            fileName = now.getYear() + " " + now.getMonthValue() + " " + now.getDayOfMonth();
        }

        if (now.getMinute() != lastRecordedTime.getMinute()) {
            lastRecordedTime = now;
            uploadNums(keystrokeDataCollector, mouseDataCollector);
            Thread.sleep(1000);
        }
    }

    public static void uploadNums(KeystrokeDataCollector keystrokeDataCollector, MouseDataCollector mouseDataCollector) {
        try {
            File file = new File("C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\data_logs\\" + fileName + ".txt");
            boolean fileCreated = file.createNewFile();

            java.io.FileWriter fileWriter = new java.io.FileWriter(file, true);
            fileWriter.write(LocalDateTime.now().getHour() + "," +
                    LocalDateTime.now().getMinute() + "," +
                    keystrokeDataCollector.getEnterCount() + "," +
                    keystrokeDataCollector.getSpaceBarCount() + "," +
                    mouseDataCollector.getNumberClicks()  + ",\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        keystrokeDataCollector.setEnterCount(0);
        keystrokeDataCollector.setSpaceBarCount(0);
        mouseDataCollector.setNumberClicks(0);
    }
}
