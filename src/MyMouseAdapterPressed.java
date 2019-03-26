import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapterPressed extends MouseAdapter {
    AppWindow appWindow;

    MyMouseAdapterPressed(AppWindow appWindow) {
        this.appWindow = appWindow;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (Main.gameMode == 0) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int button = e.getButton();
            int position = calculatePosition0(mouseX, mouseY);
            if(button == 1) {
                if(position == 0) {
                    //do nothing
                } else if(position == 1) {
                    Main.modeChooseButtonStatus[0] = 1;
                } else if(position == 2) {
                    Main.modeChooseButtonStatus[1] = 1;
                } else if(position == 3) {
                    Main.modeChooseButtonStatus[2] = 1;
                }
            }
        } else if (Main.gameMode == 1) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int button = e.getButton();
            int position = calculatePosition1(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {
                    Main.restartPressed = true;
                } else if (position == -2) {
                    //do nothing
                } else if(position == -3) {
                    Main.exitPressed = true;
                } else {
                    if (!Main.gameover) {
                        int i = position / 9;
                        int j = position % 9;
                        if (Main.status1[i][j] == 0) {
                            Main.status1[i][j] = 1;
                        }
                        Main.buttonPressing = position;
                    }
                }
            } else if (button == 3) {
                if (position >= 0 && !Main.gameover) {
                    int i = position / 9;
                    int j = position % 9;
                    if (Main.status1[i][j] == 0) {
                        Main.status1[i][j] = 3;
                        Main.imageType1[i][j] = 9;
                        Main.flagNumber++;
                        Main.leftMine1 = String.valueOf(Main.flagNumber) + "/10";
                    } else if (Main.status1[i][j] == 3) {
                        Main.status1[i][j] = 0;
                        Main.imageType1[i][j] = 0;
                        Main.flagNumber--;
                        Main.leftMine1 = String.valueOf(Main.flagNumber) + "/10";
                    }
                }
            }
        } else if (Main.gameMode == 2) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int button = e.getButton();
            int position = calculatePosition2(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {
                    Main.restartPressed = true;
                } else if (position == -2) {
                    //do nothing
                } else if(position == -3) {
                    Main.exitPressed = true;
                } else {
                    if (!Main.gameover) {
                        int i = position / 16;
                        int j = position % 16;
                        if (Main.status2[i][j] == 0) {
                            Main.status2[i][j] = 1;
                        }
                        Main.buttonPressing = position;
                    }
                }
            } else if (button == 3) {
                if (position >= 0 && !Main.gameover) {
                    int i = position / 16;
                    int j = position % 16;
                    if (Main.status2[i][j] == 0) {
                        Main.status2[i][j] = 3;
                        Main.imageType2[i][j] = 9;
                        Main.flagNumber++;
                        Main.leftMine2 = String.valueOf(Main.flagNumber) + "/40";
                    } else if (Main.status2[i][j] == 3) {
                        Main.status2[i][j] = 0;
                        Main.imageType2[i][j] = 0;
                        Main.flagNumber--;
                        Main.leftMine2 = String.valueOf(Main.flagNumber) + "/40";
                    }
                }
            }
        } else if (Main.gameMode == 3) {
            int mouseX = e.getX();
            int mouseY = e.getY();
            int button = e.getButton();
            int position = calculatePosition3(mouseX, mouseY);
            if (button == 1) {
                if (position == -1) {
                    Main.restartPressed = true;
                } else if (position == -2) {
                    //do nothing
                } else if(position == -3) {
                    Main.exitPressed = true;
                } else {
                    if (!Main.gameover) {
                        int i = position / 30;
                        int j = position % 30;
                        if (Main.status3[i][j] == 0) {
                            Main.status3[i][j] = 1;
                        }
                        Main.buttonPressing = position;
                    }
                }
            } else if (button == 3) {
                if (position >= 0 && !Main.gameover) {
                    int i = position / 30;
                    int j = position % 30;
                    if (Main.status3[i][j] == 0) {
                        Main.status3[i][j] = 3;
                        Main.imageType3[i][j] = 9;
                        Main.flagNumber++;
                        Main.leftMine3 = String.valueOf(Main.flagNumber) + "/99";
                    } else if (Main.status3[i][j] == 3) {
                        Main.status3[i][j] = 0;
                        Main.imageType3[i][j] = 0;
                        Main.flagNumber--;
                        Main.leftMine3 = String.valueOf(Main.flagNumber) + "/99";
                    }
                }
            }
        }
        appWindow.repaint();
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
