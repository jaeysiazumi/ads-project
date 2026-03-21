/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adsfinalproject;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author kleir
 */
public class ADSFinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch (Exception e){
            e.printStackTrace();
    }
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new LoadingScreen().setVisible(true);
            }
        });
    }
    
}
