package UserInterface;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.io.IOException;

public class OpenGuiListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == 41) { //41 key code is this ` the button below esc, which I don't use
            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\java\\GraphMaker\\TakeTheDataAndRun.py", "arg1", "arg2");
            try {
                Process p = pb.start();
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    openWindow();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void openWindow() {
            SwingUtilities.invokeLater(() -> {
                try {
                    new GUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}
