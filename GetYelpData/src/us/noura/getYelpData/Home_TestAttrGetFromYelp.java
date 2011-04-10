package us.noura.getYelpData;

import us.noura.example.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Home_TestAttrGetFromYelp extends Activity{
	String TAG = "GetYelpData Home Activity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	Log.v(TAG, "set content view in oncreate");
        
        TextView textview = (TextView) findViewById(R.id.textview);
        
        String text_display = "set text, still not yelp";
        textview.setText(text_display);
        
        Log.v(TAG, "set text to default");
        //String url = "http://www.yelp.com/biz/cafe-luna-cambridge";
        String url = "http://www.yelp.com/biz/the-middle-east-restaurant-and-nightclub-cambridge";
        String data = YelpWebScrape.getYelpVenueInfo(url, "price range");
        
        textview.setText(data);
    }
}
