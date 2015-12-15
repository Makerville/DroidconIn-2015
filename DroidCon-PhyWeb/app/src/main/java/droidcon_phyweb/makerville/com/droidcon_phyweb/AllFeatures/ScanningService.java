package droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.List;

import droidcon_phyweb.makerville.com.droidcon_phyweb.MainActivity;
import droidcon_phyweb.makerville.com.droidcon_phyweb.NotificationHandler;
import droidcon_phyweb.makerville.com.droidcon_phyweb.R;


public class ScanningService extends Service {
    final String NOTIFICATION_GROUP_KEY = "DroidCon-PhyWeb";
    NotificationCompat.Builder mbuilder;
    public NotificationManager mNotificationManager;
    private BeaconManager beaconManager;
    private String scanId;
    public String notification_info;
    boolean isOn = false;
    Intent mainItent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.d("ABC","Service started");
        initializeBuilder();
        if(!isOn) {
            beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
                @Override
                public void onEddystonesFound(List<Eddystone> eddystones) {
                    Log.d("ABC", "Nearby Eddystone beacons: " + eddystones.toString());
                    for(int i =0;i<eddystones.size();i++)
                    {
                        if(eddystones.get(i).isUid())
                        {
                            notification_info = eddystones.get(i).namespace + eddystones.get(i).instance;
                            Log.d("ABCD", notification_info);
                            Bundle bundle = new Bundle();
                            bundle.putString("Current", notification_info);
                            bundle.putBoolean("URL",false);
                            pushNotification(i, bundle, "DroidCon-PhyWeb [UID]", "Exit plan");
                        }
                        else
                        {
                            notification_info = eddystones.get(i).url.toString();
                            Log.d("ABCD", notification_info);
                            Bundle bundle = new Bundle();
                            bundle.putString("Current", notification_info);
                            bundle.putBoolean("URL",true);
                            pushNotification(i, bundle, "DroidCon-PhyWeb [URL]", notification_info);
                        }
                    }
                }
            });
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = new BeaconManager(getApplicationContext());
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override public void onServiceReady() {
                scanId = beaconManager.startEddystoneScanning();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();

    }
    public void initializeBuilder(){

        mbuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification);
        mbuilder.setAutoCancel(true);
        mbuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        mainItent = new Intent(this,NotificationHandler.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(mainItent);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,mainItent,PendingIntent.FLAG_UPDATE_CURRENT);
        stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mbuilder.setContentIntent(resultPendingIntent);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void pushNotification(int id, Bundle bundle, String title, String text) {
        Log.d("ABC","in singlepushnoti");
        if (mbuilder != null) {
            mbuilder.setGroup(NOTIFICATION_GROUP_KEY);
            mbuilder.setGroupSummary(true);
            mbuilder.setContentText(text);
            mbuilder.setContentTitle(title);
            mbuilder.setContentIntent(getPendingIntent(bundle,id));
            mNotificationManager.notify(id, mbuilder.build());
        }
    }
    private PendingIntent getPendingIntent (Bundle bundle,int x)
    {
        mainItent.putExtras(bundle);
        return PendingIntent.getActivity(this,x,mainItent,PendingIntent.FLAG_UPDATE_CURRENT);

    }
}
