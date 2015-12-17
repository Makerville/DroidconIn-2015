package droidcon_phyweb.makerville.com.droidcon_phyweb.AllFeatures;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import droidcon_phyweb.makerville.com.droidcon_phyweb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class feature_1 extends Fragment {

    ImageView imageView;
    WebView webView;
   public  Map<String,String> map = new HashMap<String, String>();
    public feature_1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_feature_1, container, false);
        // Inflate the layout for this fragment
        Log.d("ABC","Inside fragment");
        imageView = (ImageView)view.findViewById(R.id.image);
        webView = (WebView)view.findViewById(R.id.webView);

        webView.loadUrl("http://s28.postimg.org/ibf3vya2l/yoda200.gif");

        map.put("ff00","This is Arrival!");
        map.put("00ff","This is entry to the auditorium!");

        if(imageView != null)
        {
            Log.d("ABC","If madhe");
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int action = event.getAction();
                    int x = (int)event.getX();
                    int y = (int)event.getY();
                    if(action == MotionEvent.ACTION_UP)
                    {
                        ImageView img = (ImageView)view.findViewById (R.id.image_areas);
                        img.setDrawingCacheEnabled(true);
                        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
                        if (hotspots == null) {
                            Log.d ("ABC", "Hot spot bitmap was not created");
                        } else {
                            img.setDrawingCacheEnabled(false);
                            int touchedColor = hotspots.getPixel(x,y);
                            String color = Integer.toHexString(Color.red(touchedColor))+Integer.toHexString(Color.green(touchedColor))+Integer.toHexString(Color.blue(touchedColor));
                            Log.d("ABC","The touched color is: "+map.get(color));
                            if(map.get(color)!=null)
                            {
                                Toast.makeText(getContext(),map.get(color),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    return true;
                }
            });
        }
        else
        {
            Log.d("ABC","Else madhe");
        }
        return view;
    }
     // end match
}
