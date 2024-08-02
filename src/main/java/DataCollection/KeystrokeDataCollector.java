package DataCollection;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

public class KeystrokeDataCollector implements NativeKeyListener {
    private int enterCount = 0;
    private int spaceBarCount = 0;

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
            enterCount++;
        }
        if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
            spaceBarCount++;
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

    public int getSpaceBarCount() {
        return spaceBarCount;
    }

    public void setSpaceBarCount(int spaceBarCount) {
        this.spaceBarCount = spaceBarCount;
    }

}