package xmlpaser;

import java.util.ArrayList;

public class Run {
	 public static void main(String[] args) throws Exception{
		 /*ArrayList<String> names = new ArrayList<String>();
		 names.add("Avuze");
		 names.add("datacraw");
		 names.add("freechat");
		 names.add("freecol");
		 names.add("gannt");
		 names.add("hibernate");
		 names.add("jasperreports");
		 names.add("jEdit");
		 names.add("jmencode");
		 names.add("jsch");
		 names.add("junit");
		 names.add("risk");
		 names.add("soapui");
		 names.add("Xholon");
		 ArrayList<xmlparser> xmllist = new ArrayList<xmlparser>();
		 for(int i =0; i < names.size(); i ++){
			 xmlparser bugs = new xmlparser(names.get(i)); 
	     
			 bugs.start();
			 xmllist.add(bugs); 
		 }
		 
		 xmlparser all = new xmlparser();
		 all.mergeall(xmllist);
		 all.output();*/

		 
		 
		 ArrayList<String> names = new ArrayList<String>();
		 names.add("Avuze");
		 /*names.add("Domination");
		 names.add("junit");
		 
		 names.add("datacrow");
		 names.add("freechat");
		 names.add("freecol");
		 names.add("ganttproject");
		 names.add("hibernate");
		 names.add("jasperreports");
		 names.add("jEdit");
		 names.add("jmencode");
		 names.add("jsch");		 
		 names.add("soapui");
		 names.add("Xholon");*/
		 for(int i =0; i < names.size(); i ++){
			 xmlparser bugs = new xmlparser(names.get(i)); 
	     
			 bugs.start();

			 DataProcessor attempt = new DataProcessor(bugs, names.get(i));
			 
			 attempt.outputAsCatagory(names.get(i)+"-final");
			 
		 }
		 
		 
		 //xmlparser bugs1 = new xmlparser(name);
		 //bugs1.start();
		 //bugs1.outputAsCatagory();
		 //DataProcessor attempt = new DataProcessor(bugs1, name);
		 
		 //attempt.outputAsCatagory(name+"-final");
		}
}
