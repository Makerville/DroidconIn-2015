package droidcon_phyweb.makerville.com.droidcon_phyweb;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class NotificationHandler extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_handler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("DroidCon-PhyWeb");
        setSupportActionBar(toolbar);

        imageView = (ImageView)findViewById(R.id.exitplan);
        imageView.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            boolean type = bundle.getBoolean("URL");
            String what_to_do = bundle.getString("Current");
            if(type)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(what_to_do));
                startActivity(intent);
            }
            else
            {
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
}
