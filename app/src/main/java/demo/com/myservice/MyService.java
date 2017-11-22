package demo.com.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.IntDef;

public class MyService extends Service {

    public static boolean isStop = true;
    public static int count;
    private Thread t;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        isStop = false;
        t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!isStop) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("count " + count);
                            count++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("結束服務");
                        break;
                    }
                }
            }
        };
        t.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isStop = true;
        t=null;
        super.onDestroy();
    }
}
