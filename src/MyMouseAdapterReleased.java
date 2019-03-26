import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MyMouseAdapterReleased extends MouseAdapter {
    AppWindow appWindow;
    int willCheck[] = new int[10000];
    int willCheckTop = 0;
    int willCheckBottom = 0;

    MyMouseAdapterReleased(AppWindow appWindow) {
        this.appWindow = appWindow;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (Main.gameMode == 0) {
            int button = e.getButton();
            int mouseX = e.getX();
            int mouseY = e.getY();
            int position = calculatePosition0(mouseX, mouseY);
            if(button == 1) {
                if(position == 0) { //pressed on 9x9, release on space
                    Main.modeChooseButtonStatus[0] = 0;
                    Main.modeChooseButtonStatus[1] = 0;
                    Main.modeChooseButtonStatus[2] = 0;
                } else if(position == 1 && Main.modeChooseButtonStatus[0] == 1) {   //pressed on 9x9, release on 9x9
                    Main.gameMode = 1;
                } else if(position == 2 && Main.modeChooseButtonStatus[1] == 1) {   //pressed on 16x16, release on 16x16
                    Main.gameMode = 2;
                } else if(position == 3 && Main.modeChooseButtonStatus[2] == 1) {   //pressed on 16x30, release on 16x30
                    Main.gameMode = 3;
                }
            }
        } else if (Main.gameMode == 1) {
            int button = e.getButton();
            int mouseX = e.getX();
            int mouseY = e.getY();
            int position = calculatePosition1(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {     //release on restart button
                    if (Main.restartPressed) {      //if restart button is already pressed, and release on restart button
                        //restart
                        restart1();
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 9;
                        int j = Main.buttonPressing % 9;
                        Main.status1[i][j] = 0;
                        Main.buttonPressing = -1;
                    } else {                        //nothing is pressed, and release on restart button
                        //do nothing
                    }
                } else if(position == -3) {   //release on exit button
                    if(Main.exitPressed) {
                        exitToStart();
                    } else if(Main.restartPressed) {
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 9;
                        int j = Main.buttonPressing % 9;
                        Main.status1[i][j] = 0;
                        Main.buttonPressing = -1;
                    }
                } else if (position == -2) {   //release on blank
                    if (Main.restartPressed) {       //if restart button is pressed, and release on blank
                        //do not restart
                        Main.restartPressed = false;
                    }
                    if (Main.buttonPressing != -1) {  //if a Mine button is pressed, and release on blank
                        int i = Main.buttonPressing / 9;
                        int j = Main.buttonPressing % 9;
                        if (Main.status1[i][j] == 1) {
                            Main.status1[i][j] = 0;
                        }
                        Main.buttonPressing = -1;
                    } else {             //nothing is pressed, and release on nothing
                        //do nothing
                    }
                } else {                      //release on a Mine button
                    if (Main.restartPressed) {        //if restart button is pressed, and release on a Mine button
                        //do not restart
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {   //if a Mine button is pressed, and release on a Mine button
                        int i = Main.buttonPressing / 9;
                        int j = Main.buttonPressing % 9;
                        if (Main.buttonPressing == position) {
                            if (Main.status1[i][j] == 1) {
                                Main.status1[i][j] = 2;
                                if (!Main.gameStarted) {
                                    startNewGame1(i, j);
                                }
                                int num = calculateAroundMineNumber1(i, j);
                                if (Main.map1[i][j] == 1) {           //click on a Mine
                                    Main.gameover = true;
                                    Main.imageType1[i][j] = 10;
                                    makeGameOver1();
                                } else if (Main.map1[i][j] == 0) {
                                    if (num == 0) {
                                        //do not add new msg
                                        openCover1(i, j);
                                    } else if (num > 0) {
                                        Main.imageType1[i][j] = num;
                                        if(!Main.succeed)
                                            checkSucceed1();
                                    }
                                }

                            } else if (Main.status1[i][j] == 2) {
                                openCover1(i, j);
                            }
                        } else {
                            if (Main.status1[i][j] == 1) {
                                Main.status1[i][j] = 0;
                            }
                        }
                    } else {              //nothing is pressed, and release on nothing
                        //do nothing
                    }
                }
            } else if (button == 3) {

            }
        } else if (Main.gameMode == 2) {
            int button = e.getButton();
            int mouseX = e.getX();
            int mouseY = e.getY();
            int position = calculatePosition2(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {     //release on restart button
                    if (Main.restartPressed) {      //if restart button is already pressed, and release on restart button
                        //restart
                        restart2();
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 16;
                        int j = Main.buttonPressing % 16;
                        Main.status2[i][j] = 0;
                        Main.buttonPressing = -1;
                    } else {                        //nothing is pressed, and release on restart button
                        //do nothing
                    }
                } else if(position == -3) {   //release on exit button
                    if(Main.exitPressed) {
                        exitToStart();
                    } else if(Main.restartPressed) {
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 16;
                        int j = Main.buttonPressing % 16;
                        Main.status2[i][j] = 0;
                        Main.buttonPressing = -1;
                    }
                }else if (position == -2) {   //release on blank
                    if (Main.restartPressed) {       //if restart button is pressed, and release on blank
                        //do not restart
                        Main.restartPressed = false;
                    }
                    if (Main.buttonPressing != -1) {  //if a Mine button is pressed, and release on blank
                        int i = Main.buttonPressing / 16;
                        int j = Main.buttonPressing % 16;
                        if (Main.status2[i][j] == 1) {
                            Main.status2[i][j] = 0;
                        }
                        Main.buttonPressing = -1;
                    } else {             //nothing is pressed, and release on nothing
                        //do nothing
                    }
                } else {                      //release on a Mine button
                    if (Main.restartPressed) {        //if restart button is pressed, and release on a Mine button
                        //do not restart
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {   //if a Mine button is pressed, and release on a Mine button
                        int i = Main.buttonPressing / 16;
                        int j = Main.buttonPressing % 16;
                        if (Main.buttonPressing == position) {
                            if (Main.status2[i][j] == 1) {
                                Main.status2[i][j] = 2;
                                if (!Main.gameStarted) {
                                    startNewGame2(i, j);
                                }
                                int num = calculateAroundMineNumber2(i, j);
                                if (Main.map2[i][j] == 1) {           //click on a Mine
                                    Main.gameover = true;
                                    Main.imageType2[i][j] = 10;
                                    makeGameOver2();
                                } else if (Main.map2[i][j] == 0) {
                                    if (num == 0) {
                                        //do not add new msg
                                        openCover2(i, j);
                                    } else if (num > 0) {
                                        Main.imageType2[i][j] = num;
                                        if(!Main.succeed)
                                            checkSucceed2();
                                    }
                                }

                            } else if (Main.status2[i][j] == 2) {
                                openCover2(i, j);
                            }
                        } else {
                            if (Main.status2[i][j] == 1) {
                                Main.status2[i][j] = 0;
                            }
                        }
                    } else {              //nothing is pressed, and release on nothing
                        //do nothing
                    }
                }
            } else if (button == 3) {

            }
        } else if (Main.gameMode == 3) {
            int button = e.getButton();
            int mouseX = e.getX();
            int mouseY = e.getY();
            int position = calculatePosition3(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {     //release on restart button
                    if (Main.restartPressed) {      //if restart button is already pressed, and release on restart button
                        //restart
                        restart3();
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 30;
                        int j = Main.buttonPressing % 30;
                        Main.status3[i][j] = 0;
                        Main.buttonPressing = -1;
                    } else {                        //nothing is pressed, and release on restart button
                        //do nothing
                    }
                } else if(position == -3) {   //release on exit button
                    if(Main.exitPressed) {
                        exitToStart();
                    } else if(Main.restartPressed) {
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {      //if a Mine button is pressed, and release on restart button
                        int i = Main.buttonPressing / 30;
                        int j = Main.buttonPressing % 30;
                        Main.status3[i][j] = 0;
                        Main.buttonPressing = -1;
                    }
                }else if (position == -2) {   //release on blank
                    if (Main.restartPressed) {       //if restart button is pressed, and release on blank
                        //do not restart
                        Main.restartPressed = false;
                    }
                    if (Main.buttonPressing != -1) {  //if a Mine button is pressed, and release on blank
                        int i = Main.buttonPressing / 30;
                        int j = Main.buttonPressing % 30;
                        if (Main.status3[i][j] == 1) {
                            Main.status3[i][j] = 0;
                        }
                        Main.buttonPressing = -1;
                    } else {             //nothing is pressed, and release on nothing
                        //do nothing
                    }
                } else {                      //release on a Mine button
                    if (Main.restartPressed) {        //if restart button is pressed, and release on a Mine button
                        //do not restart
                        Main.restartPressed = false;
                    } else if (Main.buttonPressing != -1) {   //if a Mine button is pressed, and release on a Mine button
                        int i = Main.buttonPressing / 30;
                        int j = Main.buttonPressing % 30;
                        if (Main.buttonPressing == position) {
                            if (Main.status3[i][j] == 1) {
                                Main.status3[i][j] = 2;
                                if (!Main.gameStarted) {
                                    startNewGame3(i, j);
                                }
                                int num = calculateAroundMineNumber3(i, j);
                                if (Main.map3[i][j] == 1) {           //click on a Mine
                                    Main.gameover = true;
                                    Main.imageType3[i][j] = 10;
                                    makeGameOver3();
                                } else if (Main.map3[i][j] == 0) {
                                    if (num == 0) {
                                        //do not add new msg
                                        openCover3(i, j);
                                    } else if (num > 0) {
                                        Main.imageType3[i][j] = num;
                                        if(!Main.succeed)
                                            checkSucceed3();
                                    }
                                }

                            } else if (Main.status3[i][j] == 2) {
                                openCover3(i, j);
                            }
                        } else {
                            if (Main.status3[i][j] == 1) {
                                Main.status3[i][j] = 0;
                            }
                        }
                    } else {              //nothing is pressed, and release on nothing
                        //do nothing
                    }
                }
            } else if (button == 3) {

            }
        }
        Main.exitPressed = false;
        appWindow.repaint();
    }

    public void exitToStart() {
        Main.gameMode = 0;
        for(int i = 0; i<3; i++) {
            Main.modeChooseButtonStatus[i] = 0;
        }

        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                Main.map1[i][j] = -1;
                Main.status1[i][j] = 0;
                Main.imageType1[i][j] = 0;
            }
        }
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<16; j++) {
                Main.map2[i][j] = -1;
                Main.status2[i][j] = 0;
                Main.imageType2[i][j] = 0;
            }
        }
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<30; j++) {
                Main.map3[i][j] = -1;
                Main.status3[i][j] = 0;
                Main.imageType3[i][j] = 0;
            }
        }
        Main.restartPressed = false;
        Main.exitPressed = false;
        Main.buttonPressing = -1;
        Main.gameStarted = false;
        Main.gameover = false;
        Main.flagNumber = 0;
        Main.succeed = false;
        Main.leftMine1 = "0/10";
        Main.leftMine2 = "0/40";
        Main.leftMine3 = "0/99";
        Main.timer.time = 0;
        Main.timer = new Timer(appWindow);
    }

    public void restart1() {
        Main.gameStarted = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Main.map1[i][j] = -1;
                Main.status1[i][j] = 0;
                Main.imageType1[i][j] = 0;
            }
        }
        Main.buttonPressing = -1;
        Main.restartPressed = false;
        Main.gameStarted = false;
        Main.gameover = false;
        Main.leftMine1 = "0/10";
        Main.flagNumber = 0;
        Main.succeed = false;
        Main.timer.time = 0;
        Main.timer = new Timer(appWindow);
    }

    public void restart2() {
        Main.gameStarted = false;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Main.map2[i][j] = -1;
                Main.status2[i][j] = 0;
                Main.imageType2[i][j] = 0;
            }
        }
        Main.buttonPressing = -1;
        Main.restartPressed = false;
        Main.gameStarted = false;
        Main.gameover = false;
        Main.leftMine2 = "0/40";
        Main.flagNumber = 0;
        Main.succeed = false;
        Main.timer.time = 0;
        Main.timer = new Timer(appWindow);
    }

    public void restart3() {
        Main.gameStarted = false;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                Main.map3[i][j] = -1;
                Main.status3[i][j] = 0;
                Main.imageType3[i][j] = 0;
            }
        }
        Main.buttonPressing = -1;
        Main.restartPressed = false;
        Main.gameStarted = false;
        Main.gameover = false;
        Main.leftMine3 = "0/99";
        Main.flagNumber = 0;
        Main.succeed = false;
        Main.timer.time = 0;
        Main.timer = new Timer(appWindow);
    }

    public void checkSucceed1() {
        int total = 0;
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                if(Main.status1[i][j] == 2)
                    total++;
            }
        }
        if(total == 71) {
            Timer.finalTime = ((double) (System.currentTimeMillis() - Timer.startTime)) / 1000;
            Main.succeed = true;
        }
    }

    public void checkSucceed2() {
        int total = 0;
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<16; j++) {
                if(Main.status2[i][j] == 2)
                    total++;
            }
        }
        if(total == 216) {
            Timer.finalTime = ((double) (System.currentTimeMillis() - Timer.startTime)) / 1000;
            Main.succeed = true;
        }
    }

    public void checkSucceed3() {
        int total = 0;
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<30; j++) {
                if(Main.status3[i][j] == 2)
                    total++;
            }
        }
        if(total == 381) {
            Timer.finalTime = ((double) (System.currentTimeMillis() - Timer.startTime)) / 1000;
            Main.succeed = true;
        }
    }

    public void makeGameOver1() {
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                if(Main.map1[i][j] == 1 && Main.imageType1[i][j] != 9 && Main.imageType1[i][j] != 10) {
                    Main.imageType1[i][j] = 11;
                }
            }
        }
        findWrongFlag1();
    }

    public void makeGameOver2() {
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<16; j++) {
                if(Main.map2[i][j] == 1 && Main.imageType2[i][j] != 9 && Main.imageType2[i][j] != 10) {
                    Main.imageType2[i][j] = 11;
                }
            }
        }
        findWrongFlag2();
    }

    public void makeGameOver3() {
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<30; j++) {
                if(Main.map3[i][j] == 1 && Main.imageType3[i][j] != 9 && Main.imageType3[i][j] != 10) {
                    Main.imageType3[i][j] = 11;
                }
            }
        }
        findWrongFlag3();
    }

    public void openCover1(int i, int j) {
        if(calculateAroundMineNumber1(i, j) == 0) {
            willCheck[willCheckTop] = i*9+j;
            willCheckTop++;
        }
        else if (calculateAroundMarkedNumber1(i, j) == calculateAroundMineNumber1(i, j)) {
            willCheck[willCheckTop] = i*9+j;
            willCheckTop++;
            explodeWithError1(i, j);
        }
        while(willCheckBottom < willCheckTop) {
            //1111111
            try {
                if(Main.map1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9-1] == 0 && Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9-1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9-1, willCheck[willCheckBottom]%9-1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 - 1][willCheck[willCheckBottom] % 9 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9-1) * 9 + (willCheck[willCheckBottom]%9-1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9-1] = 2;
                }
            } catch (Exception ignore) { }
            //2222222
            try {
                if(Main.map1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9] == 0 && Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9-1, willCheck[willCheckBottom]%9);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 - 1][willCheck[willCheckBottom] % 9] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9-1) * 9 + (willCheck[willCheckBottom]%9);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9] = 2;
                }
            } catch (Exception ignore) { }
            //3333333
            try {
                if(Main.map1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9+1] == 0 && Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9+1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9-1, willCheck[willCheckBottom]%9+1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 - 1][willCheck[willCheckBottom] % 9 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9-1) * 9 + (willCheck[willCheckBottom]%9+1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9-1][willCheck[willCheckBottom]%9+1] = 2;
                }
            } catch (Exception ignore) { }
            //4444444
            try {
                if(Main.map1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9-1] == 0 && Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9-1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9, willCheck[willCheckBottom]%9-1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9][willCheck[willCheckBottom] % 9 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9) * 9 + (willCheck[willCheckBottom]%9-1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9-1] = 2;
                }
            } catch (Exception ignore) { }
            //5555555
            try {
                if(Main.map1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9] == 0 && Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9, willCheck[willCheckBottom]%9);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9][willCheck[willCheckBottom] % 9] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9) * 9 + (willCheck[willCheckBottom]%9);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9] = 2;
                }
            } catch (Exception ignore) { }
            //6666666
            try {
                if(Main.map1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9+1] == 0 && Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9+1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9, willCheck[willCheckBottom]%9+1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9][willCheck[willCheckBottom] % 9 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9) * 9 + (willCheck[willCheckBottom]%9+1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9][willCheck[willCheckBottom]%9+1] = 2;
                }
            } catch (Exception ignore) { }
            //7777777
            try {
                if(Main.map1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9-1] == 0 && Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9-1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9+1, willCheck[willCheckBottom]%9-1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 + 1][willCheck[willCheckBottom] % 9 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9+1) * 9 + (willCheck[willCheckBottom]%9-1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9-1] = 2;
                }
            } catch (Exception ignore) { }
            //8888888
            try {
                if(Main.map1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9] == 0 && Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9+1, willCheck[willCheckBottom]%9);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 + 1][willCheck[willCheckBottom] % 9] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9+1) * 9 + (willCheck[willCheckBottom]%9);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9] = 2;
                }
            } catch (Exception ignore) { }
            //9999999
            try {
                if(Main.map1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9+1] == 0 && Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9+1] == 0) {
                    int n = calculateAroundMineNumber1(willCheck[willCheckBottom]/9+1, willCheck[willCheckBottom]%9+1);
                    if(n > 0) {
                        Main.imageType1[willCheck[willCheckBottom] / 9 + 1][willCheck[willCheckBottom] % 9 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/9+1) * 9 + (willCheck[willCheckBottom]%9+1);
                        willCheckTop++;
                    }
                    Main.status1[willCheck[willCheckBottom]/9+1][willCheck[willCheckBottom]%9+1] = 2;
                }
            } catch (Exception ignore) { }
            willCheckBottom++;
        }
        if(!Main.succeed)
            checkSucceed1();
    }

    public void openCover2(int i, int j) {
        if(calculateAroundMineNumber2(i, j) == 0) {
            willCheck[willCheckTop] = i*16+j;
            willCheckTop++;
        }
        else if (calculateAroundMarkedNumber2(i, j) == calculateAroundMineNumber2(i, j)) {
            willCheck[willCheckTop] = i*16+j;
            willCheckTop++;
            explodeWithError2(i, j);
        }
        while(willCheckBottom < willCheckTop) {
            //1111111
            try {
                if(Main.map2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16-1] == 0 && Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16-1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16-1, willCheck[willCheckBottom]%16-1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 - 1][willCheck[willCheckBottom] % 16 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16-1) * 16 + (willCheck[willCheckBottom]%16-1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16-1] = 2;
                }
            } catch (Exception ignore) { }
            //2222222
            try {
                if(Main.map2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16] == 0 && Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16-1, willCheck[willCheckBottom]%16);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 - 1][willCheck[willCheckBottom] % 16] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16-1) * 16 + (willCheck[willCheckBottom]%16);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16] = 2;
                }
            } catch (Exception ignore) { }
            //3333333
            try {
                if(Main.map2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16+1] == 0 && Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16+1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16-1, willCheck[willCheckBottom]%16+1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 - 1][willCheck[willCheckBottom] % 16 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16-1) * 16 + (willCheck[willCheckBottom]%16+1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16-1][willCheck[willCheckBottom]%16+1] = 2;
                }
            } catch (Exception ignore) { }
            //4444444
            try {
                if(Main.map2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16-1] == 0 && Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16-1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16, willCheck[willCheckBottom]%16-1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16][willCheck[willCheckBottom] % 16 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16) * 16 + (willCheck[willCheckBottom]%16-1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16-1] = 2;
                }
            } catch (Exception ignore) { }
            //5555555
            try {
                if(Main.map2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16] == 0 && Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16, willCheck[willCheckBottom]%16);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16][willCheck[willCheckBottom] % 16] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16) * 16 + (willCheck[willCheckBottom]%16);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16] = 2;
                }
            } catch (Exception ignore) { }
            //6666666
            try {
                if(Main.map2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16+1] == 0 && Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16+1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16, willCheck[willCheckBottom]%16+1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16][willCheck[willCheckBottom] % 16 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16) * 16 + (willCheck[willCheckBottom]%16+1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16][willCheck[willCheckBottom]%16+1] = 2;
                }
            } catch (Exception ignore) { }
            //7777777
            try {
                if(Main.map2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16-1] == 0 && Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16-1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16+1, willCheck[willCheckBottom]%16-1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 + 1][willCheck[willCheckBottom] % 16 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16+1) * 16 + (willCheck[willCheckBottom]%16-1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16-1] = 2;
                }
            } catch (Exception ignore) { }
            //8888888
            try {
                if(Main.map2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16] == 0 && Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16+1, willCheck[willCheckBottom]%16);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 + 1][willCheck[willCheckBottom] % 16] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16+1) * 16 + (willCheck[willCheckBottom]%16);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16] = 2;
                }
            } catch (Exception ignore) { }
            //9999999
            try {
                if(Main.map2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16+1] == 0 && Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16+1] == 0) {
                    int n = calculateAroundMineNumber2(willCheck[willCheckBottom]/16+1, willCheck[willCheckBottom]%16+1);
                    if(n > 0) {
                        Main.imageType2[willCheck[willCheckBottom] / 16 + 1][willCheck[willCheckBottom] % 16 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/16+1) * 16 + (willCheck[willCheckBottom]%16+1);
                        willCheckTop++;
                    }
                    Main.status2[willCheck[willCheckBottom]/16+1][willCheck[willCheckBottom]%16+1] = 2;
                }
            } catch (Exception ignore) { }
            willCheckBottom++;
        }
        if(!Main.succeed)
            checkSucceed2();
    }

    public void openCover3(int i, int j) {
        if(calculateAroundMineNumber3(i, j) == 0) {
            willCheck[willCheckTop] = i*30+j;
            willCheckTop++;
        }
        else if (calculateAroundMarkedNumber3(i, j) == calculateAroundMineNumber3(i, j)) {
            willCheck[willCheckTop] = i*30+j;
            willCheckTop++;
            explodeWithError3(i, j);
        }
        while(willCheckBottom < willCheckTop) {
            //1111111
            try {
                if(Main.map3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30-1] == 0 && Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30-1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30-1, willCheck[willCheckBottom]%30-1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 - 1][willCheck[willCheckBottom] % 30 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30-1) * 30 + (willCheck[willCheckBottom]%30-1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30-1] = 2;
                }
            } catch (Exception ignore) { }
            //2222222
            try {
                if(Main.map3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30] == 0 && Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30-1, willCheck[willCheckBottom]%30);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 - 1][willCheck[willCheckBottom] % 30] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30-1) * 30 + (willCheck[willCheckBottom]%30);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30] = 2;
                }
            } catch (Exception ignore) { }
            //3333333
            try {
                if(Main.map3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30+1] == 0 && Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30+1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30-1, willCheck[willCheckBottom]%30+1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 - 1][willCheck[willCheckBottom] % 30 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30-1) * 30 + (willCheck[willCheckBottom]%30+1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30-1][willCheck[willCheckBottom]%30+1] = 2;
                }
            } catch (Exception ignore) { }
            //4444444
            try {
                if(Main.map3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30-1] == 0 && Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30-1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30, willCheck[willCheckBottom]%30-1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30][willCheck[willCheckBottom] % 30 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30) * 30 + (willCheck[willCheckBottom]%30-1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30-1] = 2;
                }
            } catch (Exception ignore) { }
            //5555555
            try {
                if(Main.map3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30] == 0 && Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30, willCheck[willCheckBottom]%30);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30][willCheck[willCheckBottom] % 30] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30) * 30 + (willCheck[willCheckBottom]%30);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30] = 2;
                }
            } catch (Exception ignore) { }
            //6666666
            try {
                if(Main.map3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30+1] == 0 && Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30+1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30, willCheck[willCheckBottom]%30+1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30][willCheck[willCheckBottom] % 30 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30) * 30 + (willCheck[willCheckBottom]%30+1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30][willCheck[willCheckBottom]%30+1] = 2;
                }
            } catch (Exception ignore) { }
            //7777777
            try {
                if(Main.map3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30-1] == 0 && Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30-1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30+1, willCheck[willCheckBottom]%30-1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 + 1][willCheck[willCheckBottom] % 30 - 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30+1) * 30 + (willCheck[willCheckBottom]%30-1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30-1] = 2;
                }
            } catch (Exception ignore) { }
            //8888888
            try {
                if(Main.map3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30] == 0 && Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30+1, willCheck[willCheckBottom]%30);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 + 1][willCheck[willCheckBottom] % 30] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30+1) * 30 + (willCheck[willCheckBottom]%30);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30] = 2;
                }
            } catch (Exception ignore) { }
            //9999999
            try {
                if(Main.map3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30+1] == 0 && Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30+1] == 0) {
                    int n = calculateAroundMineNumber3(willCheck[willCheckBottom]/30+1, willCheck[willCheckBottom]%30+1);
                    if(n > 0) {
                        Main.imageType3[willCheck[willCheckBottom] / 30 + 1][willCheck[willCheckBottom] % 30 + 1] = n;
                    }
                    else {
                        willCheck[willCheckTop] = (willCheck[willCheckBottom]/30+1) * 30 + (willCheck[willCheckBottom]%30+1);
                        willCheckTop++;
                    }
                    Main.status3[willCheck[willCheckBottom]/30+1][willCheck[willCheckBottom]%30+1] = 2;
                }
            } catch (Exception ignore) { }
            willCheckBottom++;
        }
        if(!Main.succeed)
            checkSucceed3();
    }

    public void explodeWithError1(int i, int j) {
        try {
            if(Main.map1[i-1][j-1] == 1 && Main.status1[i-1][j-1] != 3) {
                Main.imageType1[i-1][j-1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i-1][j] == 1 && Main.status1[i-1][j] != 3) {
                Main.imageType1[i-1][j] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i-1][j+1] == 1 && Main.status1[i-1][j+1] != 3) {
                Main.imageType1[i-1][j+1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i][j-1] == 1 && Main.status1[i][j-1] != 3) {
                Main.imageType1[i][j-1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i][j] == 1 && Main.status1[i][j] != 3) {
                Main.imageType1[i][j] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i][j+1] == 1 && Main.status1[i][j+1] != 3) {
                Main.imageType1[i][j+1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i+1][j-1] == 1 && Main.status1[i+1][j-1] != 3) {
                Main.imageType1[i+1][j-1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i+1][j] == 1 && Main.status1[i+1][j] != 3) {
                Main.imageType1[i+1][j] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map1[i+1][j+1] == 1 && Main.status1[i+1][j+1] != 3) {
                Main.imageType1[i+1][j+1] = 10;
                Main.gameover = true;
                makeGameOver1();
                return;
            }
        } catch (Exception ignore) { }
    }

    public void explodeWithError2(int i, int j) {
        try {
            if(Main.map2[i-1][j-1] == 1 && Main.status2[i-1][j-1] != 3) {
                Main.imageType2[i-1][j-1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i-1][j] == 1 && Main.status2[i-1][j] != 3) {
                Main.imageType2[i-1][j] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i-1][j+1] == 1 && Main.status2[i-1][j+1] != 3) {
                Main.imageType2[i-1][j+1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i][j-1] == 1 && Main.status2[i][j-1] != 3) {
                Main.imageType2[i][j-1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i][j] == 1 && Main.status2[i][j] != 3) {
                Main.imageType2[i][j] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i][j+1] == 1 && Main.status2[i][j+1] != 3) {
                Main.imageType2[i][j+1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i+1][j-1] == 1 && Main.status2[i+1][j-1] != 3) {
                Main.imageType2[i+1][j-1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i+1][j] == 1 && Main.status2[i+1][j] != 3) {
                Main.imageType2[i+1][j] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map2[i+1][j+1] == 1 && Main.status2[i+1][j+1] != 3) {
                Main.imageType2[i+1][j+1] = 10;
                Main.gameover = true;
                makeGameOver2();
                return;
            }
        } catch (Exception ignore) { }
    }

    public void explodeWithError3(int i, int j) {
        try {
            if(Main.map3[i-1][j-1] == 1 && Main.status3[i-1][j-1] != 3) {
                Main.imageType3[i-1][j-1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i-1][j] == 1 && Main.status3[i-1][j] != 3) {
                Main.imageType3[i-1][j] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i-1][j+1] == 1 && Main.status3[i-1][j+1] != 3) {
                Main.imageType3[i-1][j+1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i][j-1] == 1 && Main.status3[i][j-1] != 3) {
                Main.imageType3[i][j-1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i][j] == 1 && Main.status3[i][j] != 3) {
                Main.imageType3[i][j] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i][j+1] == 1 && Main.status3[i][j+1] != 3) {
                Main.imageType3[i][j+1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i+1][j-1] == 1 && Main.status3[i+1][j-1] != 3) {
                Main.imageType3[i+1][j-1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i+1][j] == 1 && Main.status3[i+1][j] != 3) {
                Main.imageType3[i+1][j] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
        try {
            if(Main.map3[i+1][j+1] == 1 && Main.status3[i+1][j+1] != 3) {
                Main.imageType3[i+1][j+1] = 10;
                Main.gameover = true;
                makeGameOver3();
                return;
            }
        } catch (Exception ignore) { }
    }

    public void findWrongFlag1() {
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                if(Main.map1[i][j] == 0 && Main.status1[i][j] == 3) {
                    Main.imageType1[i][j] = 12;
                }
            }
        }
    }

    public void findWrongFlag2() {
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<16; j++) {
                if(Main.map2[i][j] == 0 && Main.status2[i][j] == 3) {
                    Main.imageType2[i][j] = 12;
                }
            }
        }
    }

    public void findWrongFlag3() {
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<30; j++) {
                if(Main.map3[i][j] == 0 && Main.status3[i][j] == 3) {
                    Main.imageType3[i][j] = 12;
                }
            }
        }
    }

    public int calculateAroundMineNumber1(int i, int j) {
        int num = 0;
        try {
            num += Main.map1[i - 1][j - 1];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i - 1][j];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i - 1][j + 1];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i][j - 1];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i][j];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i][j + 1];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i + 1][j - 1];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i + 1][j];
        } catch (Exception ignore) {
        }
        try {
            num += Main.map1[i + 1][j + 1];
        } catch (Exception ignore) {
        }
        return num;
    }

    public int calculateAroundMineNumber2(int i, int j) {
        int num = 0;
        try {
            num += Main.map2[i-1][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i-1][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i-1][j+1];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i][j+1];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i+1][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i+1][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map2[i+1][j+1];
        } catch (Exception ignore) { }
        return num;
    }

    public int calculateAroundMineNumber3(int i, int j) {
        int num = 0;
        try {
            num += Main.map3[i-1][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i-1][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i-1][j+1];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i][j+1];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i+1][j-1];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i+1][j];
        } catch (Exception ignore) { }
        try {
            num += Main.map3[i+1][j+1];
        } catch (Exception ignore) { }
        return num;
    }

    public int calculateAroundMarkedNumber1(int i, int j) {
        int num = 0;
        try {
            if(Main.status1[i-1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i-1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i-1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i+1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i+1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status1[i+1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        return num;
    }

    public int calculateAroundMarkedNumber2(int i, int j) {
        int num = 0;
        try {
            if(Main.status2[i-1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i-1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i-1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i+1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i+1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status2[i+1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        return num;
    }

    public int calculateAroundMarkedNumber3(int i, int j) {
        int num = 0;
        try {
            if(Main.status3[i-1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i-1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i-1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i+1][j-1] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i+1][j] == 3)
                num++;
        } catch (Exception ignore) { }
        try {
            if(Main.status3[i+1][j+1] == 3)
                num++;
        } catch (Exception ignore) { }
        return num;
    }

    public void startNewGame1(int i, int j) {
        if(i>0 && j>0)
            Main.map1[i-1][j-1] = 0;
        if(i>0)
            Main.map1[i - 1][j] = 0;
        if(i>0 && j<8)
            Main.map1[i - 1][j + 1] = 0;
        if(j>0)
            Main.map1[i][j-1] = 0;
        Main.map1[i][j] = 0;
        if(j<8)
            Main.map1[i][j+1] = 0;
        if(i<8 && j>0)
            Main.map1[i+1][j-1] = 0;
        if(i<8)
            Main.map1[i+1][j] = 0;
        if(i<8 && j<8)
            Main.map1[i+1][j+1] = 0;
        int total = 0;
        Random random = new Random();
        while(total < 10) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if(Main.map1[row][col] != -1) {
                continue;
            }
            Main.map1[row][col] = 1;
            total++;
        }
        for(int m = 0; m<9; m++) {
            for(int n = 0; n<9; n++) {
                if(Main.map1[m][n] == -1) {
                    Main.map1[m][n] = 0;
                }
            }
        }
        Main.gameStarted = true;
        Main.leftMine3 = "0/10";

        Main.timer.start();
    }

    public void startNewGame2(int i, int j) {
        if(i>0 && j>0)
            Main.map2[i-1][j-1] = 0;
        if(i>0)
            Main.map2[i - 1][j] = 0;
        if(i>0 && j<15)
            Main.map2[i - 1][j + 1] = 0;
        if(j>0)
            Main.map2[i][j-1] = 0;
        Main.map2[i][j] = 0;
        if(j<15)
            Main.map2[i][j+1] = 0;
        if(i<15 && j>0)
            Main.map2[i+1][j-1] = 0;
        if(i<15)
            Main.map2[i+1][j] = 0;
        if(i<15 && j<15)
            Main.map2[i+1][j+1] = 0;
        int total = 0;
        Random random = new Random();
        while(total < 40) {
            int row = random.nextInt(16);
            int col = random.nextInt(16);

            if(Main.map2[row][col] != -1) {
                continue;
            }
            Main.map2[row][col] = 1;
            total++;
        }
        for(int m = 0; m<16; m++) {
            for(int n = 0; n<16; n++) {
                if(Main.map2[m][n] == -1) {
                    Main.map2[m][n] = 0;
                }
            }
        }
        Main.gameStarted = true;
        Main.leftMine3 = "0/40";

        Main.timer.start();
    }

    public void startNewGame3(int i, int j) {
        if(i>0 && j>0)
            Main.map3[i-1][j-1] = 0;
        if(i>0)
            Main.map3[i - 1][j] = 0;
        if(i>0 && j<29)
            Main.map3[i - 1][j + 1] = 0;
        if(j>0)
            Main.map3[i][j-1] = 0;
        Main.map3[i][j] = 0;
        if(j<29)
            Main.map3[i][j+1] = 0;
        if(i<15 && j>0)
            Main.map3[i+1][j-1] = 0;
        if(i<15)
            Main.map3[i+1][j] = 0;
        if(i<15 && j<29)
            Main.map3[i+1][j+1] = 0;
        int total = 0;
        Random random = new Random();
        while(total < 99) {
            int row = random.nextInt(16);
            int col = random.nextInt(30);

            if(Main.map3[row][col] != -1) {
                continue;
            }
            Main.map3[row][col] = 1;
            total++;
        }
        for(int m = 0; m<16; m++) {
            for(int n = 0; n<30; n++) {
                if(Main.map3[m][n] == -1) {
                    Main.map3[m][n] = 0;
                }
            }
        }
        Main.gameStarted = true;
        Main.leftMine3 = "0/99";

        Main.timer.start();
    }

    public int calculatePosition0(int mouseX, int mouseY) {
        //return 0 for nothing
        //return 1 for 9x9
        //return 2 for 16x16
        //return 3 for 16x30
        if(mouseX >= 190 && mouseX <= 390 && mouseY >= 30 && mouseY <= 230) {
            return 1;
        }
        else if(mouseX >= 410 && mouseX <= 610 && mouseY >= 30 && mouseY <= 230) {
            return 2;
        }
        else if(mouseX >= 190 && mouseX <= 390 && mouseY >= 250 && mouseY <= 450) {
            return 3;
        }
        else {
            return 0;
        }
    }

    public int calculatePosition1(int mouseX, int mouseY) {
        //return -1 for Restart
        //return -3 for exit
        //return i*9+j for others
        //return -2 for nothing
        if(mouseX >= 375 && mouseX <= 425 && mouseY >= 25 && mouseY <= 55) {
            return -1;
        }
        else if(mouseX >= 450 && mouseX <= 500 && mouseY >= 25 && mouseY <= 55) {
            return -3;
        }
        else {
            for(int i = 0; i<9; i++) {
                for(int j = 0; j<9; j++) {
                    if(mouseX >= (280+25*j) && mouseX <= (302+25*j) && mouseY >= (80+25*i) && mouseY <= (102+25*i)) {
                        return i*9+j;
                    }
                }
            }
            return -2;
        }
    }

    public int calculatePosition2(int mouseX, int mouseY) {
        //return -1 for Restart
        //return i*16+j for others
        //return -2 for nothing
        if(mouseX >= 375 && mouseX <= 425 && mouseY >= 25 && mouseY <= 55) {
            return -1;
        }
        else if(mouseX >= 450 && mouseX <= 500 && mouseY >= 25 && mouseY <= 55) {
            return -3;
        }
        else {
            for(int i = 0; i<16; i++) {
                for(int j = 0; j<16; j++) {
                    if(mouseX >= (190+25*j) && mouseX <= (212+25*j) && mouseY >= (80+25*i) && mouseY <= (102+25*i)) {
                        return i*16+j;
                    }
                }
            }
            return -2;
        }
    }

    public int calculatePosition3(int mouseX, int mouseY) {
        //return -1 for Restart
        //return i*30+j for others
        //return -2 for nothing
        if(mouseX >= 375 && mouseX <= 425 && mouseY >= 25 && mouseY <= 55) {
            return -1;
        }
        else if(mouseX >= 450 && mouseX <= 500 && mouseY >= 25 && mouseY <= 55) {
            return -3;
        }
        else {
            for(int i = 0; i<16; i++) {
                for(int j = 0; j<30; j++) {
                    if(mouseX >= (24+25*j) && mouseX <= (46+25*j) && mouseY >= (80+25*i) && mouseY <= (102+25*i)) {
                        return i*30+j;
                    }
                }
            }
            return -2;
        }
    }

}
