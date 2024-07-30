import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

public class KeystrokeDataCollector implements NativeKeyListener {
    private int enterCount = 0;

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            enterCount++;
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public int getEnterCount() {
        return enterCount;
    }

    public void setEnterCount(int num) {
        enterCount = num;
    }

//    public static void uploadDataBasedOnTime() {
//        LocalDateTime now = LocalDateTime.now();
//
//        if (now.getDayOfMonth() > lastRecordedTime.getDayOfMonth() ||
//                now.getMonthValue() > lastRecordedTime.getMonthValue() ||
//                now.getYear() > lastRecordedTime.getYear() ||
//                fileName.isEmpty()) {
//            fileName = now.getDayOfYear() + " " + now.getYear();
//        }
//
//        if (now.getMinute() != lastRecordedTime.getMinute()) {
//            lastRecordedTime = now;
//            uploadNums();
//            enter = 0;
//        }
//    }
//
//    public static void uploadNums() {
//        try {
//            File file = new File("C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\data_logs\\" + fileName + ".txt");
//            boolean fileCreated = file.createNewFile();
//
//            FileWriter fileWriter = new FileWriter(file, true); // Open in append mode
//            fileWriter.write(LocalDateTime.now() + " " + enter + "\n");
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            GlobalScreen.registerNativeHook();
//        }
//        catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        GlobalScreen.addNativeKeyListener(new KeystrokeDataCollector());
//
//        new Thread(() -> {
//            while (true) {
//                uploadDataBasedOnTime();
//            }
//        }).start();
//    }
}