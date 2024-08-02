package UserInterface;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    public GUI() {
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.toFront();
        this.requestFocus();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
