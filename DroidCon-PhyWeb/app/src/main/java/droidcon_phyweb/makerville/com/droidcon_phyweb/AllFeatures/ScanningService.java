package droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.eddystone.Eddystone;

import java.util.List;


public class ScanningService extends Service {

    private BeaconManager beaconManager;
    private String scanId;
    boolean isOn = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        Log.d("ABC","Service started");

        if(!isOn) {
            beaconManager.setEddystoneListener(new BeaconManager.EddystoneListener() {
                @Override
                public void onEddystonesFound(List<Eddystone> eddystones) {
                    Log.d("ABC", "Nearby Eddystone beacons: " + eddystones.toString());
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
}
