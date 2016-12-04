package AirCraftWar;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class AirCraftClass extends JPanel implements KeyListener, ActionListener {
    //enemy Plane String Name
    public String enemyPlane1 = "enemyplane.PNG";

    private boolean gameIsOn = true;


    Timer tm = new Timer(5, this);//change the value here,to move your game faster or slower... more value will decrese the speed and less value will increase the speed.


    private BufferedImage imagePlane;
    //private BufferedImage  imagePlaneExplosion;
    private BufferedImage imageEnemyPlane;
    private BufferedImage imageBullet;

    //Plane movement control
    private int planeXAxis = 0;
    private int planeYAxis = 200;
    private int xVelocity = 0;
    private int yVelocity = 0;

    //plane firing
    private boolean fireBullet = false;
    private boolean isFireBulletOn = false;

    //bullet movement control
    private int bulletXAxis = planeXAxis + 65;
    private int bulletYAxis = planeYAxis + 25;


    //enemyplane control
    private int enemyPlaneXAxis = 1000;
    private int enemyPlaneYAxis = 200;
    private boolean coalitionOccurred = true;


    AirCraftClass() {
        if (gameIsOn) {
            tm.start();

            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            addKeyListener(this);
        }
    }


    public void frame() {
       /* GameOverWindow gameOverWindow =new GameOverWindow();
                gameOverWindow.popUpWindow(scoreCounter);*/
        addKeyListener(this);
        if (gameIsOn) {

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setTitle("AirCraft War");
            frame.setResizable(false);


            AirCraftClass airCraftClass = new AirCraftClass();
            frame.getContentPane().add(airCraftClass);

            frame.revalidate();
            frame.repaint();


            frame.setLocation(200, 50);
            frame.setSize(1000, 500);
            frame.setVisible(true);

        }
    }


    int scoreCounter = 0;
    int planeCounter = 1;

    //game control


    public void paintComponent(Graphics g) {


        try {
            if (!gameIsOn) {
                super.paintComponent(g);
                g.fill3DRect(0, 0, 1000, 75, true);
                g.fill3DRect(0, 400, 1000, 75, true);

            } else {


                if (planeCounter > 9) {
                    FirstStageComplete firstStageComplete = new FirstStageComplete();
                    firstStageComplete.popUpWindow(scoreCounter);

                    gameIsOn = false;
                }

                super.paintComponent(g);
                g.fill3DRect(0, 0, 1000, 75, true);
                g.fill3DRect(0, 400, 1000, 75, true);


                if (planeYAxis <= 73) {
                    planeYAxis = 74;

                }
                if (planeYAxis >= 330) {
                    planeYAxis = 328;

                }


                if (planeXAxis >= 925) {
                    planeXAxis = 925;

                }

                if (planeXAxis <= 0) {
                    planeXAxis = 1;

                }


                //fighter plane control
                imagePlane = ImageIO.read(new File("plane.PNG"));
                g.drawImage(imagePlane, planeXAxis, planeYAxis, null);


                //fighter plane bullet control
                if (fireBullet) {
                    isFireBulletOn = true;
                    imageBullet = ImageIO.read(new File("bullet.PNG"));
                    g.drawImage(imageBullet, bulletXAxis, bulletYAxis, null);
                    bulletXAxis++;

                    if (bulletXAxis >= 950) {


                        isFireBulletOn = false;

                        fireBullet = false;
                    }


                    //bullet collision
                    if (bulletXAxis >= enemyPlaneXAxis - 40) {

                        if (bulletYAxis <= enemyPlaneYAxis + 70 & bulletYAxis >= enemyPlaneYAxis - 30) {

                         /* imagePlaneExplosion = ImageIO.read(new File("explosion.png"));
                            g.drawImage(imagePlaneExplosion, enemyPlaneXAxis-40, enemyPlaneYAxis, null);
                            imagePlaneExplosion=null;*/
                            g.drawImage(null, enemyPlaneXAxis - 40, enemyPlaneYAxis, null);
                            if (planeCounter % 2 == 1) {
                                enemyPlaneXAxis = 1100 + planeCounter * 10;
                                enemyPlaneYAxis = enemyPlaneYAxis - planeCounter * 15;
                                planeCounter++;
                                if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {
                                    gameIsOn = false;


                                }
                            } else {
                                enemyPlaneXAxis = 1100 + planeCounter * 10;
                                enemyPlaneYAxis = 200 + planeCounter * 15;
                                planeCounter++;
                                if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {
                                    gameIsOn = false;
                                    planeCounter++;


                                }
                            }

                            isFireBulletOn = false;
                            fireBullet = false;
                            scoreCounter += 500;


                        }
                    }

                }


                //enemy pane control
                if (coalitionOccurred) {
                    imageEnemyPlane = ImageIO.read(new File(enemyPlane1));
                    g.drawImage(imageEnemyPlane, enemyPlaneXAxis, enemyPlaneYAxis, null);

                    enemyPlaneXAxis--;
                }
                if (enemyPlaneXAxis < 0) {
                    if (planeCounter % 2 == 1) {
                        enemyPlaneXAxis = 1100 + planeCounter * 10;
                        enemyPlaneYAxis = enemyPlaneYAxis - planeCounter * 15;
                        planeCounter++;
                        if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {

                            FirstStageComplete firstStageComplete = new FirstStageComplete();
                            firstStageComplete.popUpWindow(scoreCounter);
                            gameIsOn = false;


                        }
                    } else {
                        enemyPlaneXAxis = 1100 + planeCounter * 10;
                        enemyPlaneYAxis = 200 + planeCounter * 15;
                        planeCounter++;
                        if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {

                            FirstStageComplete firstStageComplete = new FirstStageComplete();
                            firstStageComplete.popUpWindow(scoreCounter);
                        }
                    }
                }


                if (planeXAxis + 65 >= enemyPlaneXAxis) {

                    if (planeYAxis + 70 >= enemyPlaneYAxis && planeYAxis - 70 <= enemyPlaneYAxis) {

                        gameIsOn = false;
                       /*   imagePlaneExplosion = ImageIO.read(new File("explosion.png"));
                        g.drawImage(imagePlaneExplosion, planeXAxis, planeYAxis, null);
                        imagePlaneExplosion=null;*/
                        GameOverWindow gameOverWindow = new GameOverWindow();
                        gameOverWindow.popUpWindow(scoreCounter);
                    }
                }


            }
            g.setColor(Color.WHITE);


            g.drawString("Score: " + scoreCounter, 20, 40);
            g.drawString("Up: W" , 600, 15);
            g.drawString("Down: S" , 600, 30);
            g.drawString("Left: A " , 600, 45);
            g.drawString("Right: D " , 600, 60);
                
            g.drawString("Fire: P" , 750, 35);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            if (!(planeYAxis < 80)) {

                xVelocity = 0;
                yVelocity = -1;
            }

        }
        if (keyCode == KeyEvent.VK_S) {


            if (!(planeYAxis > 318)) {
                xVelocity = 0;
                yVelocity = 1;

            }

        }

        if (keyCode == KeyEvent.VK_D) {


            if (planeXAxis < 970) {
                xVelocity = 1;
                yVelocity = 0;
            }

        }

        if (keyCode == KeyEvent.VK_A) {

            if (planeXAxis > 0) {
                xVelocity = -1;
                yVelocity = 0;
            }


        }
        if (keyCode == KeyEvent.VK_P) {

            if (!isFireBulletOn) {

                bulletXAxis = planeXAxis + 65;
                bulletYAxis = planeYAxis + 25;
                fireBullet = true;
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        xVelocity = 0;
        yVelocity = 0;

        if (!isFireBulletOn) {
            bulletXAxis = planeXAxis + 65;
            bulletYAxis = planeYAxis + 25;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        planeXAxis = planeXAxis + xVelocity;
        planeYAxis = planeYAxis + yVelocity;


        repaint();
    }
}
