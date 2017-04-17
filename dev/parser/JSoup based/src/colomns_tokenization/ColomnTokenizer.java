import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;





public class ColomnTokenizer {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//css_test(); [OK]
			
		try {
			File input = new File("1.html");
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			Element page = doc.select("div.Sect1").first();
			System.out.println(page.id());
			
			
			} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
			}
			

		
	}
	 public static void create_dir() {
	      String dirname = "/tmp/user/java/bin";
	      File d = new File(dirname);
	      
	      // Create directory now.
	      d.mkdirs();
	   }
	 public static void show_dir() {
	      File file = null;
	      String[] paths;
	  
	      try {      
	         // create new file object
	         file = new File("/tmp");

	         // array of files and directory
	         paths = file.list();

	         // for each name in the path array
	         for(String path:paths) {
	            // prints filename and directory name
	            System.out.println(path);
	         }
	      }catch(Exception e) {
	         // if any error occurs
	         e.printStackTrace();
	      }
	   }
	 
	public static void css_test() {
		// TODO Auto-generated method stub
		

		try {
			
			InputSource source = new InputSource( new StringReader ("h1 {background: #ffcc44}"));
			CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet;
			
				sheet = parser.parseStyleSheet(source, null, null);
			
			CSSRuleList rules = sheet.getCssRules();
			for (int i = 0; i < rules.getLength(); i++) {
			    final CSSRule rule = rules.item(i);

			    System.out.println(rule.getCssText());
			}
			InputSource source1 = new InputSource(new StringReader("background: #ffcc44; margin-top: 4px"));
			CSSOMParser parser1 = new CSSOMParser(new SACParserCSS3());
			CSSStyleDeclaration decl = parser1.parseStyleDeclaration(source1);

			for (int i = 0; i < decl.getLength(); i++) {
			    final String propName = decl.item(i);

			    System.out.println("'" + propName + "' has value: '" + decl.getPropertyValue(propName) + "'");
			}
			} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
			}
			

		
	}

}
