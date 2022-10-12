package PillApp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class InputScreen {
    private JFrame inputScreen;
    private JCheckBox rButtonBox;
    private JButton submitButton;
    private int x, y;

    public InputScreen() {
        inputScreen = new JFrame();
        inputScreen.setSize(300, 300);
        inputScreen.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        int size = 0;
        final ArrayList<JCheckBox> group = new ArrayList<>();

        try {
            File names = new File("names_list.txt");
            Scanner reader = new Scanner(names);

            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                rButtonBox = new JCheckBox(name);
                group.add(rButtonBox);
                con.gridx = x;
                con.gridy = y;
                size++;
                inputScreen.add(rButtonBox, con);
                y++;
            }
        } catch (FileNotFoundException e) {

        }

        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    File out = new File("out.txt");
                    FileWriter writer = new FileWriter("out.txt", true);
                    Iterator<JCheckBox> it = group.iterator();
                    while (it.hasNext()) {
                        JCheckBox box = it.next();
                        if (box.isSelected()) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            String time = dtf.format(now);
                            writer.write(box.getText() + " @" + time + "\n");

                        }
                    }

                    writer.close();
                } catch (IOException e) {

                }
                /*
                 * try{
                 * InputStream out = ClassLoader.getSystemResourceAsStream("out.txt");
                 * Scanner scan = new Scanner(out);
                 * FileWriter writer = new FileWriter("out.txt",true);
                 * while(scan.hasNextLine()){
                 * String temp = scan.nextLine();
                 * System.out.println(temp);
                 * String[] temps = temp.split("@");
                 * for(String s : temps){
                 * System.out.println(s);
                 * }
                 * writer.write(temps[1]);
                 * }
                 * writer.close();
                 * }catch(IOException e){
                 * 
                 * }
                 */

            }
        });
        con.gridx = 1;
        con.gridy = size / 2;
        inputScreen.add(submitButton, con);

        JButton reset = new JButton("RESET");
        con.gridx=1;
        con.gridy=(size/2)+1;
        reset.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            File out = new File("out.txt");
            out.delete();
            /*File name = new File("names_list.txt");
            name.delete();*/
         }   
        });
        inputScreen.add(reset,con);
        JMenuBar menuBar = new JMenuBar();
        inputScreen.setJMenuBar(menuBar);
        JMenu menu = new JMenu();
        menu.setText("File");
        JMenuItem resetI = new JMenuItem("Reset");
        resetI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                File name = new File("names_list.txt");
                name.delete();            
                
            }
        });
        menu.add(resetI);
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               inputScreen.setVisible(false);
               SetupScreen setup = new SetupScreen();
               inputScreen.dispose();
               
           } 
        });
        menu.add(restart);
        JMenuItem log = new JMenuItem("Log");
        log.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
             JPopupMenu pop = new JPopupMenu();

         }   
        });
        menu.add(log);
        menuBar.add(menu);

        inputScreen.setTitle("Input Screen");
        inputScreen.setLocationRelativeTo(null);
        inputScreen.setVisible(true);
        inputScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
