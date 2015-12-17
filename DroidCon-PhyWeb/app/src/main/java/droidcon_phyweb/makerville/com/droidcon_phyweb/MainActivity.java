package droidcon_phyweb.makerville.com.droidcon_phyweb;


import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.Developer;
import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.PhysicalWeb;
import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.ScanningService;
import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.feature_1;


public class MainActivity extends AppCompatActivity {
    final int REQUEST_ENABLE_BT = 1;
    boolean MULTIPLE_NOTIFICATION = true;
    private int mCurrentSelectedPosition;
    DrawerLayout drawer;
    NavigationView navigationView;
    public NotificationCompat.Builder mbuilder;

    //----------------------------------------------------
    // FragmentManager to handle the fragments
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent1 = new Intent(this,NotificationHandler.class);

        Log.d("ABC","create kela ");
//        initializeBuilder();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()){
            Log.d("ABC","Bluetooth is off");
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT,REQUEST_ENABLE_BT);
        }
        Intent intent = new Intent(MainActivity.this, ScanningService.class);
        startService(intent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle("Home");
        fragmentManager.beginTransaction().replace(R.id.content_main, new feature_1()).commit();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                View view = (View)findViewById(R.id.content_main);
                switch (item.getItemId()) {
                    case R.id.nav_fea1:
                        feature_1 f1 = new feature_1();
                        getSupportActionBar().setTitle("Home");
                        drawer.closeDrawer(navigationView);
                        fragmentManager.beginTransaction().replace(R.id.content_main, f1).commit();
                        return true;
                    case R.id.nav_contact:
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact@makerville.io"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                        i.putExtra(Intent.EXTRA_TEXT, "body of email");
                        drawer.closeDrawer(navigationView);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Snackbar.make(view, "no email client installed", Snackbar.LENGTH_LONG).show();
                        }
                        return true;
                    case R.id.nav_devs:
                        drawer.closeDrawer(navigationView);
                        Developer devs = new Developer();
                        getSupportActionBar().setTitle("Developers");
                        drawer.closeDrawer(navigationView);
                        fragmentManager.beginTransaction().replace(R.id.content_main, devs).commit();
//                        startActivity(intent1);
                        return true;
                    case R.id.nav_phyweb:
                        drawer.closeDrawer(navigationView);
                        PhysicalWeb physicalWeb = new PhysicalWeb();
                        getSupportActionBar().setTitle("Physical Web");
                        drawer.closeDrawer(navigationView);
                        fragmentManager.beginTransaction().replace(R.id.content_main, physicalWeb).commit();
                        return true;
                    case R.id.nav_git:
                        drawer.closeDrawer(navigationView);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/makerville/DroidconIn-2015"));
                        startActivity(browserIntent);
                        return true;
                    case R.id.nav_version:
                        drawer.closeDrawer(navigationView);
                }    return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

