package UserInterface;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenGuiListener implements NativeKeyListener {
    JFrame loadingPane;
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == 41) { //41 key code is this ` the button below esc, which I don't use
            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\java\\GraphMaker\\TakeTheDataAndRun.py", "arg1", "arg2");
            try {
                Process p = pb.start();
                openLoadingPane();
                int exitCode = p.waitFor();
                if (exitCode == 0) {
                    loadingPane.dispose();
                    openWindow();
                }
            } catch (IOException | InterruptedException ex) {
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

    private void openLoadingPane() {
        loadingPane = new JFrame("Loading");
        loadingPane.setSize(new Dimension(300, 160));
        loadingPane.setBackground(Color.WHITE);

        JLabel loadingText = new JLabel("Page is loading. This will only take a minute.");
        loadingText.setPreferredSize(new Dimension(300, 150));
        loadingPane.add(loadingText);
        loadingText.setVisible(true);

        loadingPane.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        loadingPane.setLocationRelativeTo(null);
        loadingPane.setVisible(true);
    }
}
