package test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.parser.CSSOMParser;
import com.steadystate.css.parser.SACParserCSS3;

import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.CSSProperty;
import cz.vutbr.web.css.CSSProperty.Margin;
import cz.vutbr.web.css.NodeData;
import cz.vutbr.web.css.TermLength;
import cz.vutbr.web.domassign.StyleMap;

public class Test {
	private static String m_path = "dev/parser/Java/input_data/colomns_tokenization/standart_input/su-su'lu'k.html";
	private static Document Jsoup_DOM_doc;
	private static org.w3c.dom.Document w3c_DOM_doc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println("hellooo!!");
		
		//css_test(); [OK]
		
		//css_selector_test(); [OK]
		
		//test_access_declaration(); [OK]
		
		//test_parse_declaration(); //[OK]
		
		//test_get_cssSelector(m_path); //[OK]
		 
		//test_w3cDOM(); //[OK]
		
		 try {
			test_Jstyle();  //[FAILURE]
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

		
	}
	public static void test_Jstyle() throws MalformedURLException{
		URL url = new URL("http://example.com/");
		//get the element style
		
		StyleMap map = CSSFactory.assignDOM(get_w3cDOM(m_path), "UTF-8", url, "text/css", true);
		org.w3c.dom.Element element = w3c_DOM_doc.getElementById("Sect1");
		NodeData style = map.get(element);
		//get the type of the assigned value
		CSSProperty.Margin mm = style.getProperty("margin-top");
		System.out.println("margin-top=" + mm);
		//if a length is specified, obtain the exact value
		if (mm == Margin.length)
		{
		    TermLength mtop = style.getValue(TermLength.class, "margin-top");
		    System.out.println("value=" + mtop);
		}

		
	}
	public static void test_w3cDOM(){
		org.w3c.dom.Document doc = get_w3cDOM("dev/parser/Java/input_data/colomns_tokenization/standart_input/su-su'lu'k.html");
		NodeList els = doc.getElementsByTagName("style");
		org.w3c.dom.Element el = (org.w3c.dom.Element) els.item(0);
		System.out.println(el.getTextContent());
		
		
	}
	public static void test_change_JsoupDOM(){
		Document doc = get_JsoupDOM();
		Elements els = doc.select("style");
		Element el = els.first();
		System.out.println(el.toString());
		
		
	}
	public static org.w3c.dom.Document get_w3cDOM(String _path){
		//Native method to get css selector of DOM element
		try {
			File input = new File(_path);
			//HTML Parsing
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			W3CDom w3cdom = new W3CDom();
			//w3cdom.convert();			
			w3c_DOM_doc = w3cdom.fromJsoup(doc);
			return w3c_DOM_doc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w3c_DOM_doc;
		
	}
	public static Document get_JsoupDOM(){
		//Native method to get css selector of DOM element
		try {
			File input = new File("dev/parser/JSoup based/src/colomns_tokenization/standart_input/su-su'lu'k.html");
			//HTML Parsing
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			Jsoup_DOM_doc = doc;
			return doc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Jsoup_DOM_doc;
		
	}
	public static void test_get_cssSelector(String _path){
		//Native method to get css selector of DOM element
		try {
			File input = new File(_path);
			//HTML Parsing
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");			
			//***CSS Parsing
			Element style_tag = doc.select("div").first();
			System.out.println("" + style_tag.cssSelector());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
