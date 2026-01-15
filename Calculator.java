import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener{


        JTextField visor;

        double num1 = 0;
        String op = "";
        boolean newDigit = true;

        public static void main(String[] args) {
            new Calculator();
        }
    
        public Calculator(){

            // VISOR

            JFrame frame = new JFrame("Calculator");
            frame.setSize(500, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            visor = new JTextField("0");

            visor.setFont(new Font("Arial", Font.BOLD, 75));
            visor.setHorizontalAlignment(JTextField.RIGHT); // TO THE RIGHT
            visor.setEditable(false);

            frame.add(visor, BorderLayout.NORTH); // TO THE TOP
        
            // KEYBOARD

            JPanel buttonPanel = new JPanel();
            
            buttonPanel.setLayout(new GridLayout(6,4));

            String[] textButton = {
                "%", "CE", "C", "DEL",
                "1/x", "x²", "√x", "÷",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ".", "="
            };

            for(String text : textButton){
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.addActionListener(this);
                buttonPanel.add(button);
            }

            frame.add(buttonPanel, BorderLayout.CENTER);
            frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();

        // PRESSING KEYS

        if (command.equals("C")) {
            visor.setText("0");
            num1 = 0;
            op = "";
        }

        else if (command.equals("CE")) {
            visor.setText("0");
        }

        else if (command.equals("DEL")){

            String deleteText = visor.getText();

            if (deleteText.length() > 1) {
                visor.setText(deleteText.substring(0, deleteText.length() - 1));
            }
            else{
                visor.setText("0");
            }
        }

        else if (command.equals("=")){
            if (!op.isEmpty()) {

                try{
                    double num2 = Double.parseDouble(visor.getText());
                    double result = Operation.calculate(op, num1, num2);
                    visor.setText(String.valueOf(result));
                    newDigit = true;
                }
                catch (Exception ex){
                    visor.setText("Error");
                }
            }
        }

        else if ("0123456789.".contains(command)){
            if (newDigit) {
                visor.setText(command);
                newDigit = false;
            }
            else{
                visor.setText(visor.getText() + command);
            }
        }  

        // TWO NUMBERS

        if (command.equals("+") || command.equals("-") || command.equals("x") || command.equals("÷")) {
            num1 = Double.parseDouble(visor.getText());
            op = command;
            newDigit = true;
        }

        // ONE NUMBER

        else if (command.equals("x²") || command.equals("1/x") || command.equals("√x") || command.equals("+/-") || command.equals("%")) {
            num1 = Double.parseDouble(visor.getText());
            double result = Operation.calculateOne(command, num1);

            visor.setText(String.valueOf(result));
            newDigit = true;
        }
    }
}
