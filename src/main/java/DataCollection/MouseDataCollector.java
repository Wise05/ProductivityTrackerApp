package DataCollection;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

public class MouseDataCollector implements NativeMouseInputListener {
    private int numberClicks;

    public void nativeMouseReleased(NativeMouseEvent e) {
        numberClicks++;
    }

    public int getNumberClicks() {
        return numberClicks;
    }

    public void setNumberClicks(int num) {
        numberClicks = num;
    }
}
