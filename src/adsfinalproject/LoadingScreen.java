package adsfinalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.Timer;

public class LoadingScreen extends JFrame {

    public LoadingScreen(){
        JFrame frame = new JFrame("Loading");
        frame.setSize(1300,800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);

        ImageIcon gif = new ImageIcon(LoadingScreen.class.getResource("/design/loading.gif"));
        label.setIcon(gif);

        JProgressBar bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setValue(0);
        bar.setStringPainted(true);
        bar.setBackground(Color.white);
       bar.setForeground(new java.awt.Color(255,140,0));

        frame.add(label, BorderLayout.CENTER);
        frame.add(bar, BorderLayout.SOUTH);
        frame.setVisible(true);

        Timer timer = new Timer(60, null);

        timer.addActionListener(e -> {

            int value = bar.getValue();
            bar.setValue(value + 1);

            if(bar.getValue() >= 100){
                timer.stop();
                new startUp().setVisible(true);
                frame.dispose();
            }

        });

        timer.start();
    }
}