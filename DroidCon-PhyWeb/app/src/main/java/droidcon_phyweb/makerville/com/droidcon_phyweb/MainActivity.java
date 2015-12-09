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

import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.ScanningService;
import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.feature_1;
import droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures.feature_2;


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

        Log.d("ABC","create kela ");
        Intent intent = new Intent(MainActivity.this, ScanningService.class);
        startService(intent);
//        initializeBuilder();


        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothAdapter.isEnabled()){
            Log.d("ABC","Bluetooth is off");
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT,REQUEST_ENABLE_BT);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                View view = (View)findViewById(R.id.content_main);
                switch (item.getItemId()) {
                    case R.id.nav_fea1:
                        Snackbar.make(view, "Feature 1", Snackbar.LENGTH_LONG).show();
                        feature_1 f1 = new feature_1();
                        mCurrentSelectedPosition = 0;
                        getSupportActionBar().setTitle("Home");
                        drawer.closeDrawer(navigationView);
                        fragmentManager.beginTransaction().replace(R.id.content_main, f1).commit();
                        return true;
                    case R.id.nav_fea2:
                        Snackbar.make(view, "Feature 2", Snackbar.LENGTH_LONG).show();
                        feature_2 f2 = new feature_2();
                        mCurrentSelectedPosition = 1;
                        getSupportActionBar().setTitle("First Time UX");
                        drawer.closeDrawer(navigationView);
                        fragmentManager.beginTransaction().replace(R.id.content_main, f2).commit();
                        return true;
                    case R.id.nav_contact:
                        Snackbar.make(view, "Contact", Snackbar.LENGTH_LONG).show();
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
                        Snackbar.make(view, "Developers", Snackbar.LENGTH_LONG).show();
                        return true;
                    case R.id.nav_phyweb:
                        drawer.closeDrawer(navigationView);
                        Snackbar.make(view, "Physical Web", Snackbar.LENGTH_LONG).show();
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.github.io/physical-web/"));
                        startActivity(browserIntent);
                        return true;
                    case R.id.nav_git:
                        drawer.closeDrawer(navigationView);
                        Snackbar.make(view, "Github", Snackbar.LENGTH_LONG).show();
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/makerville/DroidconIn-2015"));
                        startActivity(browserIntent);
                }    return true;
            }
        });
    }

    @Override
    protected void onPause() {
        Log.d("ABC","paused");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.d("ABC","Baher alo ");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

