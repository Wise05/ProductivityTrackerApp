import DataCollection.DataUploader;
import DataCollection.KeystrokeDataCollector;
import DataCollection.MouseDataCollector;
import UserInterface.OpenGuiListener;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
        OpenGuiListener openGuiListener = new OpenGuiListener();

        GlobalScreen.addNativeKeyListener(keystrokeDataCollector);
        GlobalScreen.addNativeMouseListener(mouseDataCollector);
        GlobalScreen.addNativeKeyListener(openGuiListener);

        new Thread(() -> {
            while (true) {
                DataUploader.uploadDataBasedOnTime(keystrokeDataCollector, mouseDataCollector);
            }
        }).start();
    }
}