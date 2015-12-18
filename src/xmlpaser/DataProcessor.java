package xmlpaser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    private List<ReadabilityData> readability;
    private xmlparser bugData;
    ArrayList<String> readabilityValue = new ArrayList<String>();
    ArrayList<String> matchedClasses = new ArrayList<String>();
    
    public DataProcessor(xmlparser xml, String fileName) throws IOException{
    	this.bugData = xml;
    	ReadabilityExcelReader dataReader = new ReadabilityExcelReader();
    	this.readability = dataReader.readXls(fileName);
    	this.match();
    }
    
    private void match(){
    	ArrayList<String> bugMethods = new ArrayList<String>();
    	bugMethods = bugData.getClassNumbers();
    	/*for(int i =0; i < bugMethods.size();i++){
    		for(int j =0; j < readability.size();j++){
    			String className = bugMethods.get(i);
    			String compareName = readability.get(j).getClassName();
    			if((className.contains(compareName)||compareName.contains(className))&&!matchedClasses.contains(className))
    			{
    				System.out.println(bugMethods.get(i));
    				System.out.println(readability.get(j).getClassName());
    				matchedClasses.add(readability.get(j).getClassName());
    				readabilityValue.add(readability.get(j).getReadability());
    			}
    		}
    	}*/
    	
    	//matchedClasses = this.getClassNumbers(matchedClasses);
    	
    	
    	for(int j =0; j < readability.size();j++){
			
			
			
				matchedClasses.add(readability.get(j).getClassName());
				readabilityValue.add(readability.get(j).getReadability());
			
		}
    }
    
    public ArrayList<String> getClassNumbers(ArrayList<String> classes){
   	 ArrayList<String> newarr = new ArrayList<String>();
   	 
   	 for(int i=0 ;i < classes.size(); i++){
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
    
    
    
    public void outputAsCatagory(String address) {
	    try {
	      File csv = new File("results/"+address+"Modified.csv"); // CSV数据文件

	      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
	      
	      String lineTitle = "ClassAndMethodname";
	      String lineContent = "";
	      
	      ArrayList<String> rows = new ArrayList<String>();
	      
	      
	      rows.add("BAD_PRACTICE");  
	      rows.add("I18N");
	      rows.add("PERFORMANCE");
	      rows.add("MALICIOUS_CODE");
	      rows.add("STYLE");
	      rows.add("MT_CORRECTNESS");
	      rows.add("CORRECTNESS");
	      rows.add("EXPERIMENTAL");
	      rows.add("SECURITY");
	      
	      for(int i = 0; i < rows.size(); i++){
	    	  lineTitle = lineTitle + "," + rows.get(i);
	      }
	      
	      lineTitle = lineTitle + "," + "Readability";
	      bw.write(lineTitle);
 	  bw.newLine();
 	  
 	  for(int i = 0; i < matchedClasses.size(); i++){
 		  lineContent = matchedClasses.get(i);
 		  int[] content = new int[rows.size()];
	    	  for(int j = 0;j<bugData.classes.size(); j ++){
	    		  if(bugData.classes.get(j).equals(matchedClasses.get(i))
	    			||bugData.classes.get(j).contains(matchedClasses.get(i))||matchedClasses.get(i).contains(bugData.classes.get(j))
	    			)
	    		  {
	    			  for(int k = 0; k <rows.size(); k ++){
	    				  if((bugData.category.get(j)).equals(rows.get(k))){
	    					  content[k] = content[k] + 1;
	    				  }
	    			  }
	    		  }
	    	  }
	    	  //contents.add(content);/
	    	  for(int k = 0; k <rows.size(); k ++){
	    		 // if(content[k]==0){lineContent = lineContent + "," + "NO";}
	    		 // else{lineContent = lineContent + "," + "YES";}
 			  lineContent = lineContent + "," + String.valueOf(content[k]);
 		  }
	    	  
	    	  String readability = "";
	    	  if(Double.valueOf(readabilityValue.get(i))>3.586){readability = "HIGH";}
	    	  else if(Double.valueOf(readabilityValue.get(i))<0.952){readability = "LOW";}
	    	  else{readability = "NORMAL";}
	    	  
	    	  //mean 2.284, stddev is 1.319, so we set the range of 60% as normal, then low is from 0 to 0.965, high is from 3.603 to 5
	    	  
	    	  lineContent = lineContent + "," + 
	    	  //readability;
	    	  readabilityValue.get(i);
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
}
