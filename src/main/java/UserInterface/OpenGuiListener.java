package UserInterface;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;

public class OpenGuiListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == 41) { //41 key code is this `
            openWindow();
        }
    }

    public void openWindow() {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new GUI();
                }
            });
    }
}
