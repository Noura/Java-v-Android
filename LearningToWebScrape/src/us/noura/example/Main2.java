package us.noura.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

class Main2
{  
	public static void main2(String args[])
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
		
		String attr = "price range";
		
		if (attr == "price range") {
			Document doc = null;
			try{
				doc = Jsoup.connect("http://www.yelp.com/biz/cafe-luna-cambridge").get();
			} catch (Exception e) {
				System.err.println("Exception: " + e.getMessage());
			}
			
			String doc_string = doc.toString();
			String key = "<dt class=\"attr-RestaurantsPriceRange2\">";
			int i = doc_string.indexOf(key);
			String relevant_section = doc_string.substring(i, i + 1000); 
			Document doc_smaller = Jsoup.parse(relevant_section);
			Elements elems = doc_smaller.getElementsByClass("pricerange");
			for (Element elem : elems) {
				System.out.println(elem.text());
			}
		}
		
		if (attr == "hours") {
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
		}
	}
	
/*//writing document to a file
	public static void main(String args[])
	{
		try {
			Document doc = Jsoup.connect("http://www.yelp.com/biz/cafe-luna-cambridge").get();
			FileWriter fstream = new FileWriter("tmp_info.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			System.out.println(doc);
			out.write(doc.toString());
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}
*/
	
/*//parse doesn't seem to work as well as connect... must do something different i don't understand
 	public static void main(String args[])
 	{
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Element content = doc.getElementById("content");
		System.out.println("doc: "+doc);
		System.out.println("content: "+content);
		System.out.println("finished");
 	}
 */

/*// document, element, attribute
	public static void main(String args[])
	{
		String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
		Document doc = Jsoup.parse(html);
		Element link = doc.select("a").first();
		
		System.out.println(link);

		String text = doc.body().text(); // "An example link"
		String linkHref = link.attr("href"); // "http://example.com/"
		String linkText = link.text(); // "example""

		String linkOuterH = link.outerHtml(); 
		    // "<a href="http://example.com"><b>example</b></a>"
		String linkInnerH = link.html(); // "<b>example</b>"
		
	}
*/
}
