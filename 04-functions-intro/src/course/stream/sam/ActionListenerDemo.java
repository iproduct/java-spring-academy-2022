package course.stream.sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerDemo {
    public static void main(String[] args) {
        // Swing stuff
        JFrame frame = new JFrame("ActionListener Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton testButton = new JButton("Test Button");
        frame.add(testButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        // ActionListener SAM interface impl
//        testButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                System.out.println("Click detected using anonymous class");
//            }
//        });
        testButton.addActionListener(actionEvent -> System.out.println("Click detected using anonymous class"));
    }
}
