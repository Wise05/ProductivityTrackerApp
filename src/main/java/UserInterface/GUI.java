package UserInterface;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {
    File dirPath = new File("C:\\Users\\zevan\\IdeaProjects\\Productivity Tracker App\\src\\main\\graphs");
    String[] fileNames = dirPath.list();
    String[] graphNames;

    {
        assert fileNames != null;
        graphNames = takeAwayPNG(fileNames);
    }
    ImageIcon[] imageIcons = new ImageIcon[fileNames.length];
    ImageIcon[] scaledIcons = new ImageIcon[fileNames.length];
    JLabel graphLabel = new JLabel();
    JRadioButton[] buttons = new JRadioButton[graphNames.length];
    JButton stopButton = new JButton("Stop Program");

    public GUI() throws IOException {
        GlobalScreen.setEventDispatcher(new SwingDispatchService());

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1000,600);
        this.setLayout(null);
        this.setResizable(false);
        this.setTitle("Keystroke Graphs");

        graphLabel.setVerticalAlignment(JLabel.TOP);
        graphLabel.setBounds(0, 0, 600, 600);

        for (int i = 0; i < fileNames.length; i++) {
            imageIcons[i] = new ImageIcon(dirPath + "\\" + fileNames[i]);
            Image scaledImage = imageIcons[i].getImage().getScaledInstance(550, 550, Image.SCALE_SMOOTH);
            scaledIcons[i] = new ImageIcon(scaledImage);
        }

        graphLabel.setIcon(scaledIcons[0]);
        graphLabel.setBackground(Color.BLACK);

        JLabel buttonsLabel = new JLabel();
        buttonsLabel.setVerticalAlignment(JLabel.TOP);
        buttonsLabel.setBounds(550, 0, 450, 500);
        buttonsLabel.setOpaque(true);
        buttonsLabel.setVisible(true);
        buttonsLabel.setLayout(new BoxLayout(buttonsLabel, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < graphNames.length; i++) {
            buttons[i] = new JRadioButton(graphNames[i]);
            buttons[i].setBackground(Color.WHITE);
            group.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttonsLabel.add(buttons[i]);
        }
        buttonsLabel.setBackground(Color.WHITE);

        stopButton.setBounds(550, 500, 450, 50);
        stopButton.addActionListener(this);

        this.add(graphLabel);
        this.add(buttonsLabel);
        this.add(stopButton);

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
        for (int i = 0; i < fileNames.length; i++) {
            if (e.getSource() == buttons[i]) {
                graphLabel.setIcon(scaledIcons[i]);
            }
        }
        if (e.getSource() == stopButton) {
            System.exit(0);
        }
    }

    private String[] takeAwayPNG(String[] s) {
        String[] output = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            output[i] = s[i].substring(0, s[i].length() - 4);
        }
        return output;
    }
}
