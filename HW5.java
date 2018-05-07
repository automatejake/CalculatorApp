//In this homework, you need to create a calculator by GUI application. 
//It needs to have four functions: plus, minus, multiply, and divide. 
//The number appear on the calculator is 0-9. 
//It should be able to accept floating pointnumber.
//Here is anexample of the GUI interface. You can design different interfaces

/**
boolean first/sec
double firstNum
double secNum
double answer
String operationType

actionlistener numbers
   if boolean true
      when num button pressed, adds to firstNum as String
      displays on screen
   if boolean true
      when num button pressed, adds to secNum as String
      displays on screen
      
actionListener operations
   if/else defines String operationType
   clears screen
   sets boolean to false
       
actionListener equals
   converts first and sec num to double
   does operation to two numbers
   sets first number to answer
   sec num = 0
   displays number on screen
     
actionListener clear
   first and second num become 0
   boolean becomes true
**/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HW5 extends JFrame //implements ActionListener
{
   private JButton[] button = new JButton[16];
   private final int width = 275;
   private final int height = 300;
   private final Color blue = Color.BLUE; 
   
   private boolean first = true;
   private String firstNum = "";
   private String secondNum = "";
   private String operationType;
   private JLabel label = new JLabel("", JLabel.CENTER);
   private JPanel inner = new JPanel();
   private numButtonsAction[] numButton = new numButtonsAction[10];
      
   public static void main(String [] args)
   {
      HW5 hw5 = new HW5();
      hw5.setVisible(true);   
   }
   
   private HW5()
   {
      setTitle("Calculator");
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Container pane = getContentPane();
      pane.setLayout(new BorderLayout());
      
      pane.add(display(""), BorderLayout.NORTH);
      pane.add(buttonsGrid(), BorderLayout.CENTER);
      pane.add(clear(), BorderLayout.SOUTH);
      
      label.setText("0.0");    
   }
   
   
   /**displayed numbers, shows North screen**/
   public JComponent display(String ok)
   {
      label.setText(ok);
      label.setFont(new Font("Roboto", Font.BOLD, 70));
      return label;
   }
   
   /**creates the grid of components to be returned in a JComponent, shows center screen**/
   public JComponent buttonsGrid()
   {
      JPanel inner = new JPanel();
      inner.setLayout(new GridLayout(4,4));
      
      //buttons 7-9
      for(int i = 7; i <= 9; i++)
      {   
         button[i] = new JButton("" + i);
         button[i].setForeground(Color.BLUE);
         numButton[i] = new numButtonsAction(button[i]);
         button[i].addActionListener(numButton[i]);
         inner.add(button[i]);
      }
      
      //multiplication button
      button[10] = new JButton("x");
      button[10].setForeground(Color.BLUE);
      multiplyAction mult = new multiplyAction();
      button[10].addActionListener(mult);
      inner.add(button[10]);
      
      //buttons 4-6
      for(int i = 4; i <= 6; i++)
      {   
         button[i] = new JButton("" + i);
         button[i].setForeground(Color.BLUE);
         numButton[i] = new numButtonsAction(button[i]);
         button[i].addActionListener(numButton[i]);
         inner.add(button[i]);
      }
      
      //division button
      button[11] = new JButton("/");
      button[11].setForeground(Color.BLUE);
      divideAction div = new divideAction();
      button[11].addActionListener(div);
      inner.add(button[11]); 
      
      //buttons 1-3
      for(int i = 1; i <= 3; i++)
      {   
         button[i] = new JButton("" + i);
         button[i].setForeground(Color.BLUE);
         numButton[i] = new numButtonsAction(button[i]);
         button[i].addActionListener(numButton[i]);
         inner.add(button[i]);
      }
      
      //addition button
      button[12] = new JButton("+");
      button[12].setForeground(Color.BLUE);
      addAction add = new addAction();
      button[12].addActionListener(add);
      inner.add(button[12]);
      
      //decinal button
      button[15] = new JButton(".");
      button[15].setForeground(Color.BLUE);
      decimalButtonAction deci = new decimalButtonAction();
      button[15].addActionListener(deci);
      inner.add(button[15]);
      
      //zero button
      button[0] = new JButton("0");
      button[0].setForeground(Color.BLUE);
      numButton[0] = new numButtonsAction(button[0]);
      button[0].addActionListener(numButton[0]);
      inner.add(button[0]);
      
      //equals button
      button[13] = new JButton("=");
      button[13].setForeground(Color.BLUE);
      equalsAction equals = new equalsAction();
      button[13].addActionListener(equals);
      inner.add(button[13]);
      
      //subtraction button
      button[14] = new JButton("-");
      button[14].setForeground(Color.BLUE);
      inner.add(button[14]); 
      subtractAction sub = new subtractAction();
      button[14].addActionListener(sub);
      inner.setBackground(blue);
      return inner;
   }
   
   //clear button, shows South screen
   public JComponent clear()
   {
      clearAction wipe = new clearAction();
      JButton clear = new JButton("CLEAR");
      clear.setForeground(blue);
      inner.setBackground(blue);
      inner.add(clear);
      clear.addActionListener(wipe);
      return inner;
   }
   
   //builds numbers to do operations to
   private class numButtonsAction implements ActionListener //actionlistener is an interface
   {   
      private String num;
   
      public numButtonsAction(JButton a) {
         this.num = a.getText();
      }
     
      public void actionPerformed(ActionEvent e) 
      {   
         
         if(operationType == "Null"){
            firstNum = "";
            secondNum = "";
            label.setText("");
            first = true;
         }
         
         if(first == true)
         {
            firstNum = firstNum + num;
            label.setText(firstNum);
         }
         else if(first == false)
         {
            secondNum = secondNum + num;
            label.setText(secondNum);
         } 
      }
   }
   
   //creates decimal
   private class decimalButtonAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {   
         
         if(first == true)
         {
            firstNum = firstNum + ".";
            label.setText(firstNum);
         }
         else if(first == false)
         {
            secondNum = secondNum + ".";
            label.setText(secondNum);
         }
      } 
   }
   
   //defines operation as addition
   private class addAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         operationType = "ADD";
         label.setText(label.getText() + "+");
         first = false;
      }
   }
   
   //defines operation as subtract
   private class subtractAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         operationType = "SUBTRACT";
         label.setText(label.getText() + "-");
         first = false;
      }
   }
   
   //defines operation as multiply
   private class multiplyAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         operationType = "MULTIPLY";
         label.setText(label.getText() + "x");
         first = false;
      }
   }
   
   //defines operation as divide
   private class divideAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         operationType = "DIVIDE";
         label.setText(label.getText() + "/");
         first = false;
      }
   }
   
   //does operation based on how it has been defined
   private class equalsAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(operationType == "ADD")
         {
            firstNum = ("" + (Double.parseDouble(firstNum) + Double.parseDouble(secondNum)));
            label.setText(firstNum);
            secondNum = "";
            operationType = "Null";
         }
         else if(operationType == "SUBTRACT")
         {
            firstNum = "" + (Double.parseDouble(firstNum) - Double.parseDouble(secondNum));
            label.setText(firstNum);
            secondNum = "";
            operationType = "Null";
         }
         else if(operationType == "MULTIPLY")
         {
            firstNum = "" + (Double.parseDouble(firstNum) * Double.parseDouble(secondNum));
            label.setText(firstNum);
            secondNum = "";
            operationType = "Null";
         }
         else if(operationType == "DIVIDE")
         {
            firstNum = "" + (Double.parseDouble(firstNum) / Double.parseDouble(secondNum));
            if(firstNum.equalsIgnoreCase("Infinity"))
            {
               firstNum = "UNDEFINED";
            }
            label.setText(firstNum);
            secondNum = "";
            operationType = "Null";
         }
         else if(first == true){
            label.setText(firstNum);         
         }
         else
         {
            label.setText("ERROR");
         }
      }
   }
   
   //clears calculator
   private class clearAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         first = true;
         firstNum = "";
         secondNum = "";
         label.setText("");
         operationType = "";
         label.setText("0.0");
      }
   }
   
   
}

