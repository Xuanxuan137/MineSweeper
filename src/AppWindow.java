import javax.swing.*;
import java.awt.*;

public class AppWindow extends Frame {

    private Image iBuffer;
    private Graphics gBuffer;


    Image one = Toolkit.getDefaultToolkit().getImage("one.png");
    Image two = Toolkit.getDefaultToolkit().getImage("two.png");
    Image three = Toolkit.getDefaultToolkit().getImage("three.png");
    Image four = Toolkit.getDefaultToolkit().getImage("four.png");
    Image five = Toolkit.getDefaultToolkit().getImage("five.png");
    Image six = Toolkit.getDefaultToolkit().getImage("six.png");
    Image seven = Toolkit.getDefaultToolkit().getImage("seven.png");
    Image eight = Toolkit.getDefaultToolkit().getImage("eight.png");
    Image redMine = Toolkit.getDefaultToolkit().getImage("redMine.png");
    Image blackMine = Toolkit.getDefaultToolkit().getImage("blackMine.png");
    Image flag = Toolkit.getDefaultToolkit().getImage("flag.png");
    Image redFlag = Toolkit.getDefaultToolkit().getImage("redFlag.png");


    AppWindow() {
        addWindowListener(new MyWindowAdapter());

        addMouseListener(new MyMouseAdapterPressed(this));

        addMouseListener(new MyMouseAdapterReleased(this));
    }


    @Override
    public void paint(Graphics g) {
        if(Main.gameMode == 0) {
            paint0(g);
        }
        else if(Main.gameMode == 1) {
            paint1(g);
        }
        else if(Main.gameMode == 2) {
            paint2(g);
        }
        else if(Main.gameMode == 3) {
            paint3(g);
        }
    }

    public void paint0(Graphics g) {
        if(Main.modeChooseButtonStatus[0] == 0) {
            g.setColor(new Color(186, 189, 182));
        } else if(Main.modeChooseButtonStatus[0] == 1) {
            g.setColor(new Color(211, 215, 207));
        }
        g.fillRoundRect(190, 30, 200, 200, 15, 15);
        g.setColor(Color.black);
        g.drawString("9X9", 280, 125);

        if(Main.modeChooseButtonStatus[1] == 0) {
            g.setColor(new Color(186, 189, 182));
        } else if(Main.modeChooseButtonStatus[1] == 1) {
            g.setColor(new Color(211, 215, 207));
        }
        g.fillRoundRect(410, 30, 200, 200, 15, 15);
        g.setColor(Color.black);
        g.drawString("16X16", 490, 125);

        if(Main.modeChooseButtonStatus[2] == 0) {
            g.setColor(new Color(186, 189, 182));
        } else if(Main.modeChooseButtonStatus[2] == 1) {
            g.setColor(new Color(211, 215, 207));
        }
        g.fillRoundRect(190, 250, 200, 200, 15, 15);
        g.setColor(Color.black);
        g.drawString("16X30", 270, 345);
    }

    public void paint1(Graphics g) {
        if (Main.exitPressed) {
            g.setColor(new Color(211, 215, 207));
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(450, 25, 50, 30);
        g.setColor(Color.black);
        g.drawString("Exit", 460, 44);
        if (Main.restartPressed) {
            g.setColor(new Color(211, 215, 207));
        } else if (Main.gameover) {
            g.setColor(Color.red);
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(375, 25, 50, 30);        //draw restart button
        g.setColor(Color.black);
        g.drawString("Restart", 378, 44);           //draw "restart" on restart button
        g.drawLine(0, 60, 800, 60);
        g.drawLine(0, 65, 800, 65);           //draw two lines under restart button
        g.setColor(new Color(186, 189, 182));
        for (int i = 0; i < 9; i++) {                              //draw mine buttons
            for (int j = 0; j < 9; j++) {
                if (Main.status1[i][j] == 0 || Main.status1[i][j] == 3) {
                    g.setColor(new Color(186, 189, 182));
                } else if (Main.status1[i][j] == 1) {
                    g.setColor(new Color(211, 215, 207));
                } else if (Main.status1[i][j] == 2) {
                    g.setColor(new Color(222, 222, 220));
                }
                g.fillRoundRect(280 + 25 * j, 80 + 25 * i, 22, 22, 3, 3);
            }
        }
        for (int i = 0; i < 9; i++) {           //draw open mine buttons
            for (int j = 0; j < 9; j++) {
                g.setColor(Color.black);
                if (Main.imageType1[i][j] == 0) {
                    //draw nothing
                } else if (Main.imageType1[i][j] == 1) {
                    g.drawImage(one, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 2) {
                    g.drawImage(two, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 3) {
                    g.drawImage(three, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 4) {
                    g.drawImage(four, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 5) {
                    g.drawImage(five, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 6) {
                    g.drawImage(six, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 7) {
                    g.drawImage(seven, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 8) {
                    g.drawImage(eight, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 9) {
                    g.drawImage(flag, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 10) {
                    g.drawImage(redMine, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 11) {
                    g.drawImage(blackMine, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType1[i][j] == 12) {
                    g.drawImage(redFlag, 280 + 25 * j, 80 + 25 * i, 25, 25, this);
                }
            }
        }
        g.drawImage(flag, 20, 30, 25, 25, this);  //draw left mine recorder on left-up
        g.drawString(Main.leftMine1, 50, 50);                                 //draw left mine
        g.drawString(String.valueOf(Main.timer.time) + " s", 750, 50);         //draw left time
        if (Main.succeed) {                                                         //draw succeed time
            g.setColor(Color.gray);
            g.fillRect(300, 150, 200, 200);
            g.setColor(Color.white);
            g.drawString("Your time:", 370, 250);
            g.drawString(String.valueOf(Timer.finalTime), 395, 270);
        }
    }

    public void paint2(Graphics g) {
        if (Main.exitPressed) {
            g.setColor(new Color(211, 215, 207));
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(450, 25, 50, 30);
        g.setColor(Color.black);
        g.drawString("Exit", 460, 44);
        if (Main.restartPressed) {
            g.setColor(new Color(211, 215, 207));
        } else if (Main.gameover) {
            g.setColor(Color.red);
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(375, 25, 50, 30);        //draw restart button
        g.setColor(Color.black);
        g.drawString("Restart", 378, 44);           //draw "restart" on restart button
        g.drawLine(0, 60, 800, 60);
        g.drawLine(0, 65, 800, 65);           //draw two lines under restart button
        g.setColor(new Color(186, 189, 182));
        for (int i = 0; i < 16; i++) {                              //draw mine buttons
            for (int j = 0; j < 16; j++) {
                if (Main.status2[i][j] == 0 || Main.status2[i][j] == 3) {
                    g.setColor(new Color(186, 189, 182));
                } else if (Main.status2[i][j] == 1) {
                    g.setColor(new Color(211, 215, 207));
                } else if (Main.status2[i][j] == 2) {
                    g.setColor(new Color(222, 222, 220));
                }
                g.fillRoundRect(190 + 25 * j, 80 + 25 * i, 22, 22, 3, 3);
            }
        }
        for (int i = 0; i < 16; i++) {           //draw open mine buttons
            for (int j = 0; j < 16; j++) {
                g.setColor(Color.black);
                if (Main.imageType2[i][j] == 0) {
                    //draw nothing
                } else if (Main.imageType2[i][j] == 1) {
                    g.drawImage(one, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 2) {
                    g.drawImage(two, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 3) {
                    g.drawImage(three, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 4) {
                    g.drawImage(four, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 5) {
                    g.drawImage(five, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 6) {
                    g.drawImage(six, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 7) {
                    g.drawImage(seven, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 8) {
                    g.drawImage(eight, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 9) {
                    g.drawImage(flag, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 10) {
                    g.drawImage(redMine, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 11) {
                    g.drawImage(blackMine, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType2[i][j] == 12) {
                    g.drawImage(redFlag, 190 + 25 * j, 80 + 25 * i, 25, 25, this);
                }
            }
        }
        g.drawImage(flag, 20, 30, 25, 25, this);  //draw left mine recorder on left-up
        g.drawString(Main.leftMine2, 50, 50);                                 //draw left mine
        g.drawString(String.valueOf(Main.timer.time) + " s", 750, 50);         //draw left time
        if (Main.succeed) {                                                         //draw succeed time
            g.setColor(Color.gray);
            g.fillRect(300, 150, 200, 200);
            g.setColor(Color.white);
            g.drawString("Your time:", 370, 250);
            g.drawString(String.valueOf(Timer.finalTime), 395, 270);
        }
    }

    public void paint3(Graphics g) {
        if (Main.exitPressed) {
            g.setColor(new Color(211, 215, 207));
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(450, 25, 50, 30);
        g.setColor(Color.black);
        g.drawString("Exit", 460, 44);
        if (Main.restartPressed) {
            g.setColor(new Color(211, 215, 207));
        } else if (Main.gameover) {
            g.setColor(Color.red);
        } else {
            g.setColor(new Color(186, 189, 182));
        }
        g.fillRect(375, 25, 50, 30);       //draw restart button
        g.setColor(Color.black);
        g.drawString("Restart", 378, 44);           //draw "restart" on restart button
        g.drawLine(0, 60, 800, 60);
        g.drawLine(0, 65, 800, 65);           //draw two lines under restart button
        g.setColor(new Color(186, 189, 182));
        for (int i = 0; i < 16; i++) {                              //draw mine buttons
            for (int j = 0; j < 30; j++) {
                if (Main.status3[i][j] == 0 || Main.status3[i][j] == 3) {
                    g.setColor(new Color(186, 189, 182));
                } else if (Main.status3[i][j] == 1) {
                    g.setColor(new Color(211, 215, 207));
                } else if (Main.status3[i][j] == 2) {
                    g.setColor(new Color(222, 222, 220));
                }
                g.fillRoundRect(24 + 25 * j, 80 + 25 * i, 22, 22, 3, 3);
            }
        }
        for (int i = 0; i < 16; i++) {           //draw open mine buttons
            for (int j = 0; j < 30; j++) {
                g.setColor(Color.black);
                if (Main.imageType3[i][j] == 0) {
                    //draw nothing
                } else if (Main.imageType3[i][j] == 1) {
                    g.drawImage(one, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 2) {
                    g.drawImage(two, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 3) {
                    g.drawImage(three, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 4) {
                    g.drawImage(four, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 5) {
                    g.drawImage(five, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 6) {
                    g.drawImage(six, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 7) {
                    g.drawImage(seven, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 8) {
                    g.drawImage(eight, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 9) {
                    g.drawImage(flag, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 10) {
                    g.drawImage(redMine, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 11) {
                    g.drawImage(blackMine, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                } else if (Main.imageType3[i][j] == 12) {
                    g.drawImage(redFlag, 24 + 25 * j, 80 + 25 * i, 25, 25, this);
                }
            }
        }
        g.drawImage(flag, 20, 30, 25, 25, this);  //draw left mine recorder on left-up
        g.drawString(Main.leftMine3, 50, 50);                                 //draw left mine
        g.drawString(String.valueOf(Main.timer.time) + " s", 750, 50);         //draw left time
        if (Main.succeed) {                                                         //draw succeed time
            g.setColor(Color.gray);
            g.fillRect(300, 150, 200, 200);
            g.setColor(Color.white);
            g.drawString("Your time:", 370, 250);
            g.drawString(String.valueOf(Timer.finalTime), 395, 270);
        }
    }



    public void update(Graphics g) {
        if(iBuffer==null)
        {
            iBuffer=createImage(this.getSize().width,this.getSize().height);
            gBuffer=iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        paint(gBuffer);
        g.drawImage(iBuffer,0,0,this);
    }
}
