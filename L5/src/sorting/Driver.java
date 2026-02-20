package sorting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Flow;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Driver {

    private JFrame display;

    private SortSolver solver;

    private JButton infoButton;

    private JButton runButton;
    private JPanel controls;
    private JTextField netIDinput;

    private JPanel questionPanel;

    public Driver() {
        display = new JFrame("Sort Solver");
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLayout(new BorderLayout());

        controls = createControls();
        display.add(controls, BorderLayout.NORTH);

        display.setResizable(false);
        display.pack();
        display.setVisible(true);

        refresh();
    }

    private JPanel createControls() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.lightGray);

        JPanel controls = new JPanel();
        controls.setBackground(Color.lightGray);
        JLabel netIDLabel = new JLabel("Enter NetID:");
        netIDinput = new JTextField();
        netIDinput.setPreferredSize(new Dimension(75, 19));
        runButton = new JButton("Run Sorting Algorithms");

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (netIDinput.getText() != null) {
                    netIDinput.setEditable(false);
                    solver = new SortSolver(netIDinput.getText());
                    
                    StdOut.setFile("questions.out");
                    StdOut.println(netIDinput.getText());
                    String[] qs = solver.getQuestions();
                    for(String s : qs){
                        StdOut.println(s);
                    }
                    JOptionPane.showMessageDialog(null, "Your questions have been printed to questions.out for reference", "questions.out generated", JOptionPane.INFORMATION_MESSAGE);
                    
                    createQuestionPanel(qs);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "NetID input is empty!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        controls.add(netIDLabel);
        controls.add(netIDinput);
        controls.add(runButton);
 
        controlPanel.add(controls);
        return controlPanel;
    } 
 
    private void createQuestionPanel(String[] questions) {
        if (questionPanel != null) {
            return;
        }
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.lightGray);
        ArrayList<JTextField> answerInputs = new ArrayList<>();
        for (String s : questions) {
            JPanel question = new JPanel();
            question.setLayout(new FlowLayout(FlowLayout.RIGHT));
            question.setBackground(Color.lightGray);
            question.add(new JLabel(s));
            JTextField input = new JTextField();
            input.setPreferredSize(new Dimension(50, 20));
            input.setMaximumSize(new Dimension(50, 20));
            answerInputs.add(input);
            question.add(input);
            questionPanel.add(question);
        }

        this.runButton.setText("Rerun Sorting Algorithms"); 

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton printAnswers = new JButton("Print answers.out");
        printAnswers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StdOut.setFile("answers.out");
                String netID = netIDinput.getText();
                StdOut.println(netID); 
                StdOut.println("Spring 2026 Sort Simulation Answers");
                for (JTextField text : answerInputs) {
                    StdOut.println(text.getText());
                }
                JOptionPane.showMessageDialog(null, "Answers printed to answers.out", "Output file generated", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        JButton restartButton = new JButton("Quit and Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setText("Run Sorting Algorithms");
                netIDinput.setEditable(true); 

                display.remove(questionPanel);
                questionPanel = null;
                display.pack();
            }
        });

        buttonPanel.add(printAnswers);
        buttonPanel.add(restartButton);

        questionPanel.add(buttonPanel);

        display.add(questionPanel, BorderLayout.SOUTH);
        display.pack();
    }

    private void refresh() {
        display.revalidate();
        display.repaint();
    }

    private void clearActionListeners(JButton button) {
        if (button.getActionListeners().length > 0) {
            for (int i = button.getActionListeners().length - 1; i >= 0; i--) {
                button.removeActionListener(button.getActionListeners()[i]);
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Driver();
            }
        });
    }
}