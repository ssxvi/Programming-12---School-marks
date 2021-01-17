import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;



public class GradeAnalyzer implements ActionListener {
    JFrame frame;
    JPanel panel;
    JLabel label, label1;
    JComboBox<Integer> cb = new JComboBox<Integer>(gradesList.toArray(new Integer[gradesList.size()]));
    JButton button, button1, button2, button3;
    JTextField field;
    static ArrayList<Integer> gradesList = new ArrayList<Integer>();;
    static int index;
    static int input = 0;

    public GradeAnalyzer() {




        frame = new JFrame("this looks bad");


        panel = new JPanel(new GridBagLayout());
    

        label = new JLabel("School Marks Program!!!");
        label1 = new JLabel("Welcome to program!");   
        field = new JTextField("Enter grades here!");

        button = new JButton("Add New");
        button1 = new JButton("Remove");
        button2 = new JButton("Get Median");
        button3 = new JButton("Get Average");
        frame.add(panel);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH; //makes element fill maximum horizontal space
        label.setHorizontalAlignment(SwingConstants.CENTER); //centers element

        //Below is just the x and y coordinates of the element, as well as the size of the element


        label.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.CENTER);

        c.insets = new Insets(10,
        10,
        10,
        10);
        c.gridwidth = 2;
        c.gridx = 0 ;
        c.gridy = 0;
        panel.add(label, c);
        
        c.gridx = 0 ;
        c.gridy = 1;
        panel.add(label1, c);



        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(cb, c);

        c.gridx = 1;
        panel.add(field, c);

        //Buttons
        c.gridy = 3;

        c.gridx = 1;
        panel.add(button, c);
        button.addActionListener(this);
   
        c.gridx = 0;
        panel.add(button1, c);
        button1.addActionListener(this);



        c.gridy = 4;

        c.gridx = 0;
        panel.add(button2, c);
        button2.addActionListener(this);

        c.gridx = 1;
        panel.add(button3, c);
        button3.addActionListener(this);


        frame.setSize(500,300);
        //frame.pack();
        frame.setVisible(true);


    }

    public static void removeFrom(int index, JComboBox<Integer> cb, ArrayList<Integer> list){
        cb.removeItemAt(index);
        list.remove(index);
    }

     public static void addTo(int num, JComboBox<Integer> cb, ArrayList<Integer> list){
       cb.addItem(num);
       list.add(num);
     }

    public static void getAverage(ArrayList<Integer> grades, JLabel label){
        Integer sum = 0;

        if (grades.size() < 1){
            System.err.println("yikes bud that array fucking sucks");
            label.setText("");
        }

        for (Integer grade : grades){
            sum = sum + grade;
        }

        label.setText("The Average Grade: " + Integer.toString(sum/grades.size())); 
    }

    public static void getMedian(ArrayList<Integer> list, JLabel label){
        
        Collections.sort(list);
        label.setText("The Median Grade: " + Integer.toString(list.get(list.size()/2))); 
    }

    public static int getInput(JTextField field){
        try  {
           return Math.abs(Integer.parseInt(field.getText(), 10));
        } catch (Exception e){
            System.err.println("Yikes bud");
        }
        return -1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        index = cb.getSelectedIndex();
        input = getInput(field);
        System.out.println(input);

        if (e.getSource() == button){
            System.out.println(gradesList); 
            if (input == -1){
                label1.setText("ERROR - Please enter valid value");
            } else {

                addTo(input, cb, gradesList);
       
            }

            field.setText("");

        } else if (e.getSource() == button1){

            if (index == -1){
                label1.setText("No more elements left!!");
            } else {

                removeFrom(index, cb, gradesList);

            }


        } else if (e.getSource() == button3){
            getAverage(gradesList, label1);
        } else if (e.getSource() == button2){
            getMedian(gradesList, label1);
        }
        
        
        //End of if
    

    } //end of actionPerformed
  

    public static void main(String[] args) throws Exception {
        new GradeAnalyzer();
    }
}
