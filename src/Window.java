import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    public static JTextField txtAnswer;
    public static JTextArea sheet;
    private static boolean sheetActive = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Riemann Sum Calculator");
        frame.setSize(355, 165);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblEquation = new JLabel("Equation: y = x ^ 2");
        lblEquation.setBounds(10, 10, 110, 20);
        frame.add(lblEquation);

        JLabel lblMinInterval = new JLabel("Lower limit");
        lblMinInterval.setBounds(10, 40, 80, 20);
        frame.add(lblMinInterval);

        JTextField txtMinInterval = new JTextField();
        txtMinInterval.setBounds(100, 40, 50, 20);
        frame.add(txtMinInterval);

        JLabel lblMaxInterval = new JLabel("Upper limit");
        lblMaxInterval.setBounds(10, 70, 80, 20);
        frame.add(lblMaxInterval);

        JTextField txtMaxInterval = new JTextField();
        txtMaxInterval.setBounds(100, 70, 50, 20);
        frame.add(txtMaxInterval);

        JLabel lblSubIntervals = new JLabel("Subintervals");
        lblSubIntervals.setBounds(10, 100, 80, 20);
        frame.add(lblSubIntervals);

        JTextField txtSubinterval = new JTextField();
        txtSubinterval.setBounds(100, 100, 50, 20);
        frame.add(txtSubinterval);

        JLabel lblSide = new JLabel("Side");
        lblSide.setBounds(210, 40, 70, 20);
        frame.add(lblSide);

        String[] sides = {" ", "left", "right", "middle"};
        JComboBox cmbSide = new JComboBox(sides);
        cmbSide.setBounds(260, 40, 70, 20);
        frame.add(cmbSide);

        JLabel lblAnswer = new JLabel("Area");
        lblAnswer.setBounds(210, 70, 80, 20);
        frame.add(lblAnswer);

        txtAnswer = new JTextField();
        txtAnswer.setBounds(210, 100, 120, 20);
        txtAnswer.setEditable(false);
        frame.add(txtAnswer);

        JButton btnCalc = new JButton("Calculate");
        btnCalc.setBounds(210, 10, 120, 20);
        frame.add(btnCalc);

        sheet = new JTextArea();
        sheet.setEditable(false);
        sheet.setLineWrap(true);
        frame.add(sheet);

        JScrollPane scrollPane = new JScrollPane(sheet);
        scrollPane.setBounds(0, 150, 350, 375);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);

        frame.setVisible(true);

        btnCalc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double minInt = 0;
                double maxInt = 0;
                double subint;

                if (txtMinInterval.getText() == null)
                    return;
                else if (txtMaxInterval.getText() == null)
                    return;
                else if (txtSubinterval.getText() == null)
                    return;
                else if (cmbSide.getSelectedIndex() == 0) {
                    txtAnswer.setText("No side selected");
                    return;
                }

                String min = txtMinInterval.getText();
                if (StringUtils.isNumeric(min)
                        || StringUtils.isNumeric(min.substring(1))) {
                    minInt = Double.parseDouble(min);
                } else {
                    txtAnswer.setText("Invalid lower limit");
                    return;
                }

                String max = txtMaxInterval.getText();
                if (StringUtils.isNumeric(max)
                        || StringUtils.isNumeric(max.substring(1))) {
                    maxInt = Double.parseDouble(max);
                } else {
                    txtAnswer.setText("Invalid upper limit");
                    return;
                }

                String sub = txtSubinterval.getText();
                if (StringUtils.isNumeric(sub)) {
                    subint = Double.parseDouble(sub);
                } else {
                    txtAnswer.setText("Invalid subinterval");
                    return;
                }

                Calculate calc = new Calculate(minInt, maxInt, subint);
                calc.resetSpreadsheet();

                int side = cmbSide.getSelectedIndex();
                switch (side) {
                    case 1: calc.calcLeft();
                            break;
                    case 2: calc.calcRight();
                            break;
                    case 3: calc.calcMiddle();
                            break;
                }

                calc.setSpreadsheet();
            }
        });

        JButton btnMore = new JButton("...");
        btnMore.setBounds(170, 100, 20, 20);
        frame.add(btnMore);
        btnMore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sheetActive) {
                    frame.setSize(355, 550);
                    sheetActive = true;
                } else {
                    frame.setSize(355, 165);
                    sheetActive = false;
                }
            }
        });
    }
}
