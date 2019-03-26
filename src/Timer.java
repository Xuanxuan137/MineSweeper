public class Timer implements Runnable{
    static long time;
    static long startTime;
    Thread thread;
    AppWindow appWindow;
    static double finalTime;

    Timer(AppWindow appWindow) {
        thread = new Thread(this);
        this.appWindow = appWindow;
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        time = 1;
        while(true) {
            try {
                while(System.currentTimeMillis()-startTime-time*1000 < 0) {
                    Thread.sleep(10);
                    if(Main.succeed) {
//                        finalTime = ((double)(System.currentTimeMillis()-startTime))/1000;
                        return;
                    }
                    if(Main.gameover)
                        return;
                }
                time++;
            } catch (Exception ignore) { }
            appWindow.repaint();
        }
    }
}
