package xmlpaser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public class xmlparser {
	
 private String address;
 ArrayList<String> types = new ArrayList<String>();
 ArrayList<String> classes = new ArrayList<String>();
 ArrayList<String> methods = new ArrayList<String>();
 ArrayList<String> category = new ArrayList<String>();
 //private ArrayList<int[]> contents = new ArrayList<int[]>();
 
 public xmlparser(){
	 
 }
 
 public ArrayList<String> getTypes() {
	return types;
}

public void setTypes(ArrayList<String> types) {
	this.types = types;
}

public ArrayList<String> getClasses() {
	return classes;
}

public void setClasses(ArrayList<String> classes) {
	this.classes = classes;
}

public ArrayList<String> getMethods() {
	return methods;
}

public void setMethods(ArrayList<String> methods) {
	this.methods = methods;
}

public ArrayList<String> getCategory() {
	return category;
}

public void setCategory(ArrayList<String> category) {
	this.category = category;
}

public xmlparser(String add){
	 address = add;
 }
 public xmlparser(String add,ArrayList<String> totypes,ArrayList<String> toclasses,ArrayList<String> tocategory){
	 address = add;
	 types = totypes;
	 classes = toclasses;
	 category = tocategory;
 }
 public void mergeall(ArrayList<xmlparser> xmllist){
	 xmlparser temp = xmllist.get(0);
	 for(int i =1; i < xmllist.size(); i ++){
		 temp = mergetwo(temp,xmllist.get(i),"temp");
	 }
	 address = "MergedAll";
	 types = temp.types;
	 classes = temp.classes;
	 category = temp.category;
 }
 
 public ArrayList<String> MakeTitle(ArrayList<String> title1, ArrayList<String> title2){
	 ArrayList<String> newarr = new ArrayList<String>();
	 
	 for(int i=0 ;i < title1.size(); i++){
		 newarr.add(title1.get(i));
	 }
	 
	 for(int i=0 ;i < title2.size(); i++){
		 newarr.add(title2.get(i));
	 }
	 
	 for(int i=0 ;i < newarr.size(); i++){
		 for(int j=i+1 ;j < newarr.size(); j++){
			 if(newarr.get(i).equals(newarr.get(j))){
				 newarr.remove(j);
				 j = j - 1;
			 }
		 }		 
	 }
	 	 
	 return newarr;
	 
 }
 
 public xmlparser mergetwo(xmlparser contents1,xmlparser contents2, String target){
	 
	
	ArrayList<String> newtypes = new ArrayList<String>();
	ArrayList<String> newclasses = new ArrayList<String>();
	ArrayList<String> newcategory = new ArrayList<String>();
	
	for(int i=0 ;i < contents1.types.size(); i++){
		newtypes.add(contents1.types.get(i));
		newclasses.add(contents1.classes.get(i));
		newcategory.add(contents1.category.get(i));
		
	 }
	 
	 for(int i=0 ;i < contents2.types.size(); i++){
		newtypes.add(contents2.types.get(i));
		newclasses.add(contents2.classes.get(i));
		newcategory.add(contents2.category.get(i));		
	 }
	 
	xmlparser Mergedcontents = new xmlparser(target,newtypes,newclasses,newcategory);
	return Mergedcontents;
	 
 }
 
 public void getType(NodeList nl){
	   for(int i=0;i<nl.getLength();i++){
	         Node n=nl.item(i);
	         if(n.hasChildNodes())
	         {
	             NamedNodeMap attributes=n.getAttributes(); 
	             
	              Node attribute=attributes.item(0);
	               
	              String attributeName=attribute.getNodeName();
	             
	              String attributeValue=attribute.getNodeValue();
	              System.out.println(attributeName+ " : " +attributeValue);
	            
	              Node attribute2=attributes.item(4);
	               
	              String attributeName2=attribute2.getNodeName();
	              
	              String attributeValue2=attribute2.getNodeValue();
	              System.out.println(attributeName2+ " : " +attributeValue2);
	            
	              
	              types.add(attributeValue);
	              category.add(attributeValue2);
	              //NodeList child = ((Element) n.getChildNodes()).getElementsByTagName("Class");
	              NodeList child = ((Element) n.getChildNodes()).getElementsByTagName("Method");
	              //getClass(child);
	              if(child.getLength() != 0){
	            	  getMethod(child);
	              }
	              else{ 
	            	  NodeList child1 = ((Element) n.getChildNodes()).getElementsByTagName("Class");
	            	  getClass(child1);
	              }
	         }
	             
	          
	          System.out.println("---finish---"+n.getNodeName());   
	   }
	  
	            
	            
  }
 public void getClass(NodeList nl){
	   
	         Node n=nl.item(0);
	         
	             NamedNodeMap attributes=n.getAttributes(); 
	             
	              Node attribute=attributes.item(0);

	              String attributeName=attribute.getNodeName();

	              String attributeValue=attribute.getNodeValue();

	              System.out.println(attributeName+ " : " +attributeValue);
	            
	              classes.add(attributeValue+".classLevel");
	              methods.add("NULL");
	         
	         //System.out.println("---finish---"+n.getNodeName());   
	               
	            
  }
 
 public void getMethod(NodeList nl){
	   
     Node n=nl.item(0);
     
         NamedNodeMap attributes=n.getAttributes(); 
         
          Node attribute=attributes.item(0);

          String attributeName=attribute.getNodeName();

          String attributeValue=attribute.getNodeValue();

          System.out.println(attributeName+ " : " +attributeValue);
          
          Node attribute2=attributes.item(1);
          
          String attributeName2=attribute2.getNodeName();
          
          String attributeValue2=attribute2.getNodeValue();
          
          System.out.println(attributeName2+ " : " +attributeValue2);
          
          classes.add(attributeValue+"."+attributeValue2);
          methods.add(attributeValue2);
     
     //System.out.println("---finish---"+n.getNodeName());   
           
        
}
 public void start() throws ParserConfigurationException, SAXException, IOException{
	 DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
     Document document=db.parse(new File("src/xmlpaser/"+address+".xml"));
     
     //System.out.println("*****下面遍历XML元素*****");  
     Element root=document.getDocumentElement() ;
     String rootName=root.getNodeName();
     //System.out.println("XML文件根节点的名字："+rootName); 
     NodeList list=root.getElementsByTagName("BugInstance");
     getType(list);
 }
 
 public void output() {
	    try {
	      File csv = new File("src/xmlpaser/"+address+".csv"); // CSV数据文件

	      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
	      
	      String lineTitle = "ClassAndMethodname";
	      String lineContent = "";
	      
	      ArrayList<String> rows = new ArrayList<String>();
	      ArrayList<String> lines = new ArrayList<String>();
	      
	      rows = getTypeNumbers();
	      lines = getClassNumbers();
	      
	      for(int i = 0; i < rows.size(); i++){
	    	  lineTitle = lineTitle + "," + rows.get(i);
	      }
	      bw.write(lineTitle);
    	  bw.newLine();
    	  
    	  for(int i = 0; i < lines.size(); i++){
    		  lineContent = lines.get(i);
    		  int[] content = new int[rows.size()];
	    	  for(int j = 0;j<classes.size(); j ++){
	    		  if(classes.get(j).equals(lines.get(i))){
	    			  for(int k = 0; k <rows.size(); k ++){
	    				  if((types.get(j)+ " " + category.get(j)).equals(rows.get(k))){
	    					  content[k] = content[k] + 1;
	    				  }
	    			  }
	    		  }
	    	  }
	    	  //contents.add(content);
	    	  for(int k = 0; k <rows.size(); k ++){
	    		  //if(content[k]==0){lineContent = lineContent + "," + "NO";}
	    		  //else{lineContent = lineContent + "," + "YES";}
    			  lineContent = lineContent + "," + String.valueOf(content[k]);
    		  }
	    	  bw.write(lineContent);
	    	  bw.newLine();
	      }
    	  
    	  
	      bw.close();

	    } catch (FileNotFoundException e) {
	      
	      e.printStackTrace();
	    } catch (IOException e) {
	      
	      e.printStackTrace();
	    }
	  } 
 
 public void outputAsCatagory() {
	    try {
	      File csv = new File("src/xmlpaser/"+address+"Modified.csv"); // CSV数据文件

	      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
	      
	      String lineTitle = "ClassAndMethodname";
	      String lineContent = "";
	      
	      ArrayList<String> rows = new ArrayList<String>();
	      ArrayList<String> lines = new ArrayList<String>();
	      
	      rows = getCategoryNumbers();
	      lines = getClassNumbers();
	      
	      for(int i = 0; i < rows.size(); i++){
	    	  lineTitle = lineTitle + "," + rows.get(i);
	      }
	      bw.write(lineTitle);
 	  bw.newLine();
 	  
 	  for(int i = 0; i < lines.size(); i++){
 		  lineContent = lines.get(i);
 		  int[] content = new int[rows.size()];
	    	  for(int j = 0;j<classes.size(); j ++){
	    		  if(classes.get(j).equals(lines.get(i))){
	    			  for(int k = 0; k <rows.size(); k ++){
	    				  if((category.get(j)).equals(rows.get(k))){
	    					  content[k] = content[k] + 1;
	    				  }
	    			  }
	    		  }
	    	  }
	    	  //contents.add(content);
	    	  for(int k = 0; k <rows.size(); k ++){
	    		  //if(content[k]==0){lineContent = lineContent + "," + "NO";}
	    		  //else{lineContent = lineContent + "," + "YES";}
 			  lineContent = lineContent + "," + String.valueOf(content[k]);
 		  }
	    	  bw.write(lineContent);
	    	  bw.newLine();
	      }
 	  
 	  
	      bw.close();

	    } catch (FileNotFoundException e) {
	      
	      e.printStackTrace();
	    } catch (IOException e) {
	      
	      e.printStackTrace();
	    }
	  } 
 
 public ArrayList<String> getCategoryNumbers(){
	 ArrayList<String> newarr = new ArrayList<String>();
	 for(int i=0 ;i < category.size(); i++){
		 newarr.add(category.get(i));
	 }
	 
	 for(int i=0 ;i < newarr.size(); i++){
		 for(int j=i+1 ;j < newarr.size(); j++){
			 if(newarr.get(i).equals(newarr.get(j))){
				 newarr.remove(j);
				 j = j - 1;
			 }
		 }		 
	 }
	 	 
	 return newarr;
 }
 
 public ArrayList<String> getTypeNumbers(){
	 ArrayList<String> newarr = new ArrayList<String>();
	 for(int i=0 ;i < types.size(); i++){
		 newarr.add(types.get(i)+ " " + category.get(i));
	 }
	 
	 for(int i=0 ;i < newarr.size(); i++){
		 for(int j=i+1 ;j < newarr.size(); j++){
			 if(newarr.get(i).equals(newarr.get(j))){
				 newarr.remove(j);
				 j = j - 1;
			 }
		 }		 
	 }
	 	 
	 return newarr;
 }
 
 public ArrayList<String> getClassNumbers(){
	 ArrayList<String> newarr = new ArrayList<String>();
	 
	 for(int i=0 ;i < types.size(); i++){
		 newarr.add(classes.get(i));
	 }
	 
	 for(int i=0 ;i < newarr.size(); i++){
		 for(int j=i+1 ;j < newarr.size(); j++){
			 if(newarr.get(i).equals(newarr.get(j))){
				 newarr.remove(j);
				 j = j - 1;
			 }
		 }		 
	 }
	 	 
	 return newarr;
	 
 }
 
 

 
 }