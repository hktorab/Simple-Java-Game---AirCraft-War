package AirCraftWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
/**
 * Created by Torab on 05-Mar-16.
 */
public class GameOverWindow extends JPanel implements ActionListener {

private boolean gameStartAgain=false;

    JFrame jf = new JFrame();
        public  void popUpWindow(int x){


            JPanel jPanel = new JPanel();
            jPanel.setLayout(null);


            jf.add(jPanel);

            jPanel.setBackground(Color.YELLOW);
            jf.setSize(800,200);
            jf.setLocation(250,300);
            jf.setTitle("Dead");

            JLabel label;
            label = new JLabel(" YOUR SCORE IS: "+x);
            jPanel.setLocation(100, 20);
            label.setFont(new Font("Serif", Font.BOLD, 16));

            /*Button buttonRetry=new Button("ReTry");
            Font newButtonFont=new Font("ReTry",Font.BOLD,20);
            buttonRetry.setFont(newButtonFont);
            buttonRetry.setBounds(0, 120, 350, 36);

            buttonRetry.addActionListener(this::actionPerformed);*/

            Button closeButton=new Button("Close");
            Font newButtonFont=new Font("ReTry",Font.BOLD,20);
           closeButton.setFont(newButtonFont);
            closeButton.setBounds(0, 120, 800, 36);

            closeButton.addActionListener(this::actionPerformed);

            jf.add(closeButton);
            //jf.add(buttonRetry);
            jf.add(label);

            jf.setVisible(true);

        }


    @Override
    public void actionPerformed(ActionEvent e) {

        String check=e.getActionCommand();
        if(check.equalsIgnoreCase("close")){
            System.exit(0);
        }else {

            jf.setVisible(false);
            gameStartAgain=true;

        }



    }
}
