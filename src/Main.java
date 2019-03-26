public class Main {
    static int map1[][] = new int[9][9];
    static int map2[][] = new int[16][16];
    static int map3[][] = new int[16][30];

    static int status1[][] = new int[9][9];
    static int status2[][] = new int[16][16];
    static int status3[][] = new int[16][30];  //0 for initial, 1 for pressed, 2 for released, 3 for marked


    static int modeChooseButtonStatus[] = new int[3];     //0 for not pressed, 1 for pressed

    static int imageType1[][] = new int[9][9];
    static int imageType2[][] = new int[16][16];
    static int imageType3[][] = new int[16][30];  //0 for blank, 1 for one...8 for eight,  9 for flag, 10 for redMine, 11 for blackMine, 12 for redFlag


    static boolean restartPressed = false;
    static boolean exitPressed = false;

    static int buttonPressing = -1;

    static boolean gameStarted = false;

    static boolean gameover = false;

    static String leftMine1 = "0/10";
    static String leftMine2 = "0/40";
    static String leftMine3 = "0/99";

    static int flagNumber = 0;

    static boolean succeed = false;

    static int gameMode = 0;  //0 for choose, 1 for easy, 2 for medium, 3 for hard

    static Timer timer;

    public static void main(String args[]) {
        for(int i = 0; i<3; i++) {
            modeChooseButtonStatus[i] = 0;
        }
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<30; j++) {
                map3[i][j] = -1;
                status3[i][j] = 0;
            }
        }
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                map1[i][j] = -1;
                status1[i][j] = 0;
            }
        }
        for(int i = 0; i<16; i++) {
            for(int j = 0; j<16; j++) {
                map2[i][j] = -1;
                status2[i][j] = 0;
            }
        }
        AppWindow appWindow = new AppWindow();
        appWindow.setSize(800, 500);
        appWindow.setTitle("MineSweeper");
        appWindow.setVisible(true);
        appWindow.setAlwaysOnTop(true);
        timer = new Timer(appWindow);
    }
}
