import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
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
		
		//css_selector_test(); [OK]
		
		//test_access_declaration(); [OK]
		
		test_parse_declaration();
			

		
	}
	public static void mix_test(){
		try {
			File input = new File("1.html");
			//HTML Parsing
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");			
			//***CSS Parsing
			Element style_tag = doc.select("style").first();			
			InputSource source = new InputSource( new StringReader (style_tag.data()));
			CSSOMParser css_parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet css_tree;			
				css_tree = css_parser.parseStyleSheet(source, null, null);
			//Get html tag
			Element p_tag = doc.select("p.P81").first();
			//Get the CSS rule of the tag
			String css_class = p_tag.className();
			
			// List out all css rules
			String css_rule = "";
			CSSRuleList rules = css_tree.getCssRules();
			
			css_rule = get_css_rule_by_class_name(rules, p_tag.className());// primitive non standard way
			
			// Get 
			InputSource source1 = new InputSource(new StringReader("font-size: 12pt; font-family: Liberation Serif; writing-mode: page; margin-left: 0.4638in; margin-right: 0.4035in; margin-top: 0.0575in; margin-bottom: 0in; line-height: 95%; text-indent: 0in"));
			CSSStyleDeclaration decl = css_parser.parseStyleDeclaration(source1);

			for (int i = 0; i < decl.getLength(); i++) {
			    final String propName = decl.item(i);

			    System.out.println("'" + propName + "' has value: '" + decl.getPropertyValue(propName) + "'");
			}
			System.out.println(p_tag.className());
			System.out.println(css_rule);
			
			
			} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
			}
		
	}
	public static String get_css_rule_by_class_name(CSSRuleList css_rules, String css_class_name ) {
		for (int i = 0; i < css_rules.getLength(); i++) {
		    final CSSRule rule = css_rules.item(i);
		    if (rule.getCssText().contains(css_class_name))
		    	return rule.getCssText();		    
		}	
	     return "";
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
	public static void css_selector_test() {
		// Accessing CSS selector
		

		try {
			
			InputSource source = new InputSource( new StringReader (".h1 {background: #ffcc44}"));
			CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet;
			
				sheet = parser.parseStyleSheet(source, null, null);
			
			CSSRuleList rules = sheet.getCssRules();
			for (int i = 0; i < rules.getLength(); i++) {
			    final CSSRule rule = rules.item(i);
				    if (rule instanceof CSSStyleRule){
				    	CSSStyleRule styleRule = (CSSStyleRule) rule;					    	
					    String selector = styleRule.getSelectorText();
					    System.out.println(rule.getCssText());
					    System.out.println(selector);
				    }
			}
			
			} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
			}			

	}
	public static void test_access_declaration() {
		// Accessing CSS selector
		

		try {
			
			InputSource source = new InputSource( new StringReader (".h1 {background: #ffcc44}"));
			CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet;
			
				sheet = parser.parseStyleSheet(source, null, null);
			
			CSSRuleList rules = sheet.getCssRules();
			for (int i = 0; i < rules.getLength(); i++) {
			    final CSSRule rule = rules.item(i);
				    if (rule instanceof CSSStyleRule){
				    	CSSStyleRule styleRule = (CSSStyleRule) rule;					    	
				    	CSSStyleDeclaration decl = styleRule.getStyle();
					    System.out.println("CSS Rule:\t\t" + rule.getCssText());
					    System.out.println("CSS Declaration:\t" + decl.toString());
				    }
			}
			
			} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
			}			

	}
	public static void test_parse_declaration() {
		// Accessing CSS selector
		

		try {
			
			InputSource source = new InputSource( new StringReader (".h1 {background: #ffcc44; margin-top: 4px}"));
			CSSOMParser parser = new CSSOMParser(new SACParserCSS3());
			CSSStyleSheet sheet;
			
				sheet = parser.parseStyleSheet(source, null, null);
			
			CSSRuleList rules = sheet.getCssRules();
			for (int i = 0; i < rules.getLength(); i++) {
			    final CSSRule rule = rules.item(i);
				    if (rule instanceof CSSStyleRule){
				    	CSSStyleRule styleRule = (CSSStyleRule) rule;					    	
				    	CSSStyleDeclaration decl = styleRule.getStyle();				    						    	
					    String selector = styleRule.getSelectorText();					    
					    

					    System.out.println("CSS Rule:\t\t" + rule.getCssText());
					    System.out.println("CSS Declaration:\t" + decl.toString());
						System.out.println("CSS Selector:\t\t" + selector);
					    System.out.println("Declaration Parsed:");
						for (int j = 0; j < decl.getLength(); j++) {
						    final String propName = decl.item(j);
						    System.out.println("'" + propName + "' has value: '" + decl.getPropertyValue(propName) + "'");
						}

				    }
			}
			
		} 
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("In catch block !!!");
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
