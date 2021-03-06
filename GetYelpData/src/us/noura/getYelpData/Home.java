package us.noura.getYelpData;

import us.noura.example.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends Activity {
	String TAG = "GetYelpData Home Activity";
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView textview = (TextView) findViewById(R.id.textview);
        
        //String res = Yelp.VenuesSearch("restaurant", 42.3000, -71.3000);
        
        String consumerKey = getString( R.string.oauth_consumer_key );
        String consumerSecret = getString( R.string.oauth_consumer_secret);
        String token = getString(R.string.oauth_token);
        String tokenSecret = getString(R.string.oauth_token_secret);
        	
        String res = Yelp.otherVenuesSearch("restaurant", 42.3000, -71.3000, 
        		consumerKey, consumerSecret, token, tokenSecret);
        textview.setText(res);
    }

}
