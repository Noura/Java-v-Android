package us.noura.getYelpData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.noura.example.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



public class Home2 extends Activity {
	String TAG = "Noura";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	Log.v(TAG, "set content view in oncreate");
        
        TextView textview = (TextView) findViewById(R.id.textview);
        
        String text_display = "set text, still not yelp";
        textview.setText(text_display);
        
        Log.v(TAG, "set text to default");
        
        String hours_display = getYelpHours();
        if (hours_display == null) {
        	textview.setText("hours_display is null");
        } else {
        	textview.setText(hours_display);
        }
    }
  
	public String getYelpHours()
	{
		/*
		 * <dt class="attr-BusinessHours">
            Hours:
           </dt> 
		   <dd class="attr-BusinessHours">
            <p class="hours">Mon-Wed 7:30 am - 9 pm</p> 
            <p class="hours">Thu-Fri 7:30 am - 11 pm</p> 
            <p class="hours">Sat 10 am - 11 pm</p> 
            <p class="hours">Sun 10 am - 8 pm</p>
           </dd> 
		 */
		Document doc = null;
		try{
			doc = Jsoup.connect("http://www.yelp.com/biz/cafe-luna-cambridge").get();
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		
		String doc_string = doc.toString();
		String key = "<dt class=\"attr-BusinessHours\">";
		int i = doc_string.indexOf(key);
		String relevant_section = doc_string.substring(i, i + 400); 
		Document doc_smaller = Jsoup.parse(relevant_section);
		Elements hours = doc_smaller.getElementsByClass("hours");
		ArrayList<String> hours_list = new ArrayList<String>();
		for (Element hour : hours) {
			hours_list.add(hour.text());
		}
		String hours_display = "";
		String hours_item;
		for (i = 0; i < hours_list.size(); i++) {
			hours_item = hours_list.get(i);
			System.out.println(hours_display);
			if (i > 0) {
				hours_display = hours_display + ", " + hours_item;
			}
			else {
				hours_display = hours_display + hours_item;
			}
		}
		System.out.println(hours_display);
		return hours_display;
	}

}