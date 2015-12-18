package xmlpaser;

import java.util.ArrayList;

public class Run2 {
	public static void main(String[] args) throws Exception{
		MethodLineNumReader readLines = new MethodLineNumReader();
		//CompareAndClassify compareAll = new CompareAndClassify(readLines,"All_number");
		 ArrayList<String> names = new ArrayList<String>();
		 names.add("Domination-finalModified");
		 names.add("junit-finalModified");
		 names.add("Avuze-finalModified");
		 names.add("datacrow-finalModified");
		 names.add("freechat-finalModified");
		 names.add("freecol-finalModified");
		 names.add("ganttproject-finalModified");
		 names.add("hibernate-finalModified");
		 names.add("jasperreports-finalModified");
		 names.add("jEdit-finalModified");
		 names.add("jmencode-finalModified");
		 names.add("jsch-finalModified");		 
		 names.add("soapui-finalModified");
		 names.add("Xholon-finalModified");
		 for(int i =0; i < names.size(); i ++){
			
			 CompareAndClassify compare = new CompareAndClassify(readLines,names.get(i)); 
		 }
	}
}
