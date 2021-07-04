package com.dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Dice extends JFrame{
    JButton roll;
    JLabel dice;
    JLabel rollStatus;
    JProgressBar jp;
    public Dice(){
        super("Dice");

        roll = new JButton("Roll");
        roll.setFont(new Font("Montserrat",Font.PLAIN | Font.BOLD,30));
        roll.setBackground(Color.darkGray);
        roll.setForeground(Color.white);
        roll.setBounds(130,230,120,50);
        add(roll);

        rollStatus = new JLabel("Press Roll");
        rollStatus.setBounds(160,185,190,20);
        add(rollStatus);

          jp = new JProgressBar();
          jp.setBorderPainted(true);
          jp.setForeground(Color.RED);
          jp.setVisible(false);
          jp.setBounds(130,210,120,10);
          add(jp);

          class pbthred extends Thread{
              JProgressBar jProgressBar;
              public boolean PB_STAT = false;

              public pbthred(JProgressBar jp){
                  jProgressBar = jp;
              }

              @Override
              public void run() {
                  roll.setEnabled(false);
                  jp.setVisible(true);

                  for (int i = 0; i <= 100; i++){
                      jProgressBar.setValue(i);

                      try {
                          sleep(200);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

                      if(i == 0 && i <= 70)
                          rollStatus.setText("Rolling....");
                      if(i == 80 && i <=100)
                          rollStatus.setText("Rolling Done...");

                  }//end for

                  Random random = new Random();
                  int diceNo = random.nextInt(6) + 1;

                  String path = "com/dice/icons/" + diceNo + ".png";

                  ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource(path));
                  Image icon2 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
                  ImageIcon icon3 = new ImageIcon(icon2);
                  dice.setIcon(icon3);

                  jProgressBar.setValue(0);
                  roll.setEnabled(true);
                  jp.setVisible(false);
                  rollStatus.setText("Press Roll");
              }//end run

          }//pbthred class


        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pbthred p = new pbthred(jp);
                p.start();
            }
        });//actionlisteners


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("com/dice/icons/1.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        dice = new JLabel(i3);
        dice.setBounds(130,80,120,120);
        add(dice);

        setSize(400,400);
        setLayout(null);
        setLocation(600,120);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
