package PillApp;

import java.io.File;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;


public class Main {
    public static void main(String[] args){
            try {
                UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
            } catch (UnsupportedLookAndFeelException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            UIManager.put( "Button.arc", 999 );
            UIManager.put( "Component.arc", 999 );
            UIManager.put( "ProgressBar.arc", 999 );
            UIManager.put( "TextComponent.arc", 999 );
            UIManager.put( "CheckBox.arc", 999 );
        
        File names = new File("names_list.txt");
        if(names.exists()){
            InputScreen b = new InputScreen();
        }
        else{
        SetupScreen a = new SetupScreen();
        }
    }
}