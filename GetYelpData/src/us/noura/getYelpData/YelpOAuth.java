package us.noura.getYelpData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import us.noura.example.R;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class YelpOAuth {
	static String TAG = "YelpOAuth";
	public static String getAuthorized() {
		
		/* scribe will do this, it works, have no idea what it does
		OAuthService service = new ServiceBuilder()
		.provider(LinkedInApi.class)
		.apiKey(Integer.toString(R.string.oauth_consumer_key))
		.apiSecret(Integer.toString(R.string.oauth_consumer_secret))
		.build();
		*/		
		
		String res = "";
		
		String url = "http://api.yelp.com/v2/search?term=food&location=San+Francisco";
    	HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		HttpResponse httpresponse = null;
		
	   	try {
	   		List<NameValuePair> form = new ArrayList<NameValuePair>();
	   		
	   		form.add(new BasicNameValuePair("oauth_consumer_key", Integer.toString(R.string.oauth_consumer_key)));
			form.add(new BasicNameValuePair("oauth_consumer_secret", Integer.toString(R.string.oauth_consumer_secret)));
			form.add(new BasicNameValuePair("oauth_token", Integer.toString(R.string.oauth_token)));
			form.add(new BasicNameValuePair("oauth_signature_method", Integer.toString(R.string.oauth_signature_method)));
			form.add(new BasicNameValuePair("oauth_signature", "content"));
			form.add(new BasicNameValuePair("oauth_timestamp", "content"));
			form.add(new BasicNameValuePair("oauth_nonce", "content"));
  
			httppost.setEntity(new UrlEncodedFormEntity(form));
			HttpResponse httpResponse = httpclient.execute(httppost);
			String response1 = httpResponse.toString();
			Header[] response2 = httpResponse.getAllHeaders();
			String response4 = "";
			for (Header h : response2) {
				response4 = response4 + h.getName() + h.getValue();
			}
			String response3 = httpResponse.getParams().toString();
			res = response3;
			
	   	 } catch(IOException e) {
	   		 Log.e(TAG, e.toString());
	   	 }
	   	 
	   	 return res;
	}
}
