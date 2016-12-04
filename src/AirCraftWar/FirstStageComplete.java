package AirCraftWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
/**
 * Created by Torab on 08-Mar-16.
 */
public class FirstStageComplete implements ActionListener {


    JLabel label;
    public  void popUpWindow(int x){


        JFrame jf = new JFrame();






        jf.setSize(800,200);
        jf.setLocation(350,200);
        jf.setTitle("Congratulation :D ");





        label = new JLabel("YOU HAVE SUCCESSFULLY COMPLETED THE FIRST STAGE & YOUR SCORE IS: "+x);
        label.setFont(new Font("Serif", Font.BOLD, 16));



        Button buttonNextLevel=new Button("Next Level");
        Font newButtonFont=new Font("Next Level",Font.BOLD,20);
        buttonNextLevel.setFont(newButtonFont);
        buttonNextLevel.setBounds(0, 120, 800, 36);

        buttonNextLevel.addActionListener(this::actionPerformed);

        jf.add(buttonNextLevel);
        jf.add(label);
        jf.getDefaultCloseOperation();
        jf.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("Sorry,Next Level Is Only For  Premium User");
    }
}
