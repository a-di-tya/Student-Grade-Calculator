import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {

    private final JTextField[] markFields;
    private final JButton calculateButton;
    private final JLabel totalMarksLabel;
    private final JLabel averagePercentageLabel;
    private final JLabel gradeLabel;
    private static final int NUM_SUBJECTS = 5;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(NUM_SUBJECTS, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Marks (out of 100)"));

        markFields = new JTextField[NUM_SUBJECTS];
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            inputPanel.add(new JLabel("Subject " + (i + 1) + ":"));
            markFields[i] = new JTextField();
            inputPanel.add(markFields[i]);
        }

        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(this);
        buttonPanel.add(calculateButton);

        JPanel resultsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));

        resultsPanel.add(new JLabel("Total Marks:"));
        totalMarksLabel = new JLabel("...");
        resultsPanel.add(totalMarksLabel);

        resultsPanel.add(new JLabel("Average Percentage:"));
        averagePercentageLabel = new JLabel("...");
        resultsPanel.add(averagePercentageLabel);

        resultsPanel.add(new JLabel("Grade:"));
        gradeLabel = new JLabel("...");
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultsPanel.add(gradeLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double totalMarks = 0;
            try {
                for (int i = 0; i < NUM_SUBJECTS; i++) {
                    String markText = markFields[i].getText();
                    double marks = Double.parseDouble(markText);

                    if (marks < 0 || marks > 100) {
                       JOptionPane.showMessageDialog(this, "Please enter marks between 0 and 100.", "Invalid Mark", JOptionPane.ERROR_MESSAGE);
                       return;
                    }
                    totalMarks += marks;
                }

                double averagePercentage = totalMarks / NUM_SUBJECTS;
                String grade = calculateGrade(averagePercentage);

                totalMarksLabel.setText(String.format("%.2f / %d", totalMarks, NUM_SUBJECTS * 100));
                averagePercentageLabel.setText(String.format("%.2f %%", averagePercentage));
                gradeLabel.setText(grade);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for all marks.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+ (Outstanding)";
        } else if (percentage >= 80) {
            return "A (Excellent)";
        } else if (percentage >= 70) {
            return "B (Good)";
        } else if (percentage >= 60) {
            return "C (Satisfactory)";
        } else if (percentage >= 50) {
            return "D (Pass)";
        } else {
            return "F (Fail)";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator calculator = new StudentGradeCalculator();
            calculator.setVisible(true);
        });
    }
}

