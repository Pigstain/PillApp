package PillApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;


public class SetupScreen extends JFrame{
    private JFrame Setup;
    private JLabel nameLabel;
    private JTextArea medsLabel;
    private JButton submitButton;
    private ArrayList<String> Pnames;

    public SetupScreen(){
        Setup = new JFrame();
        Setup.setSize(300, 200);
        
        Pnames = new ArrayList<>();
        JPanel panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = 0;
        constr.gridy = 0;
        constr.gridheight = 1;
        constr.gridwidth = 1;
        constr.anchor = GridBagConstraints.CENTER;
        
        nameLabel = new JLabel("Pill Name");
        nameLabel.setPreferredSize(new Dimension(100, 50));
        panel1.add(nameLabel,constr);
        JTextField nameField = new JTextField();
        submitButton = new JButton("SUBMIT");
        Action action = new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               Pnames.add(nameField.getText());
               nameField.setText("");
               String temp ="";
              
                Iterator<String> it = Pnames.iterator();
                while(it.hasNext()){
                    String name = it.next();
                    
                    temp += name + "\n";
                }
                medsLabel.setText(temp);
           }
        };
        submitButton.addActionListener(action);
        nameField.addActionListener(action);
        constr.gridx=0;
        constr.gridy=1;
        constr.gridwidth=2;
        nameField.setPreferredSize(new Dimension(100, 25));
        panel1.add(nameField,constr);
        
        constr.gridx =1;
        constr.gridy =1;
        
        submitButton.setPreferredSize(new Dimension(100, 25));
        panel1.add(submitButton,constr);
        Setup.add(panel1);

        medsLabel = new JTextArea();
        constr.gridx=1;
        constr.gridy=2;
       
        JScrollPane scroll = new JScrollPane (medsLabel);
        scroll.setPreferredSize(new Dimension(150, 50));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setAutoscrolls(true);
        medsLabel.setEditable(false);
        medsLabel.setForeground(Color.magenta);
        panel1.add(scroll,constr);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
             try{
                File names = new File("names_list.txt");
                if(names.createNewFile()){
                     FileWriter writer = new FileWriter("names_list.txt");              
                    Iterator<String> it = Pnames.iterator();
                    while(it.hasNext()){
                        String name = it.next();
                        
                        writer.write(name+"\n");
                    }
                    writer.close();
                 }
                 else if(names.exists()){
                    FileWriter writer = new FileWriter("names_list.txt",true);
              
                    Iterator<String> it = Pnames.iterator();
                    while(it.hasNext()){
                        String name = it.next();
                        
                        writer.write(name+"\n");
                    }
                    writer.close();
                 }

             }catch(IOException e){
                e.printStackTrace();
             }
             Setup.setVisible(false);
             InputScreen b = new InputScreen();
         }   
        });

        constr.gridx=0;
        constr.gridy=3;
        panel1.add(okButton,constr);
        
        Setup.setTitle("Setup Screen");
        Setup.setVisible(true);
        Setup.setLocationRelativeTo(null);
        Setup.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public ArrayList<String> getNames(){
        return Pnames;
    }
    public void iterate(){
        Iterator<String> it = Pnames.iterator();
        while(it.hasNext()){
            String name = it.next();
            System.out.println(name);
        }
    }
    public void populate(String toAdd){
        Pnames.add(toAdd);
    }
}