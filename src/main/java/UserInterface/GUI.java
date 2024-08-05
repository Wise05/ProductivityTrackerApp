package UserInterface;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    public GUI() throws IOException {
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);
        this.setResizable(false);

        JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(0, 0, 500, 500);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\graphs\\graph1.png");
        label.setIcon(imageIcon);
        label.setBackground(Color.BLACK);
        this.add(label);

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
