package xmlpaser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CompareAndClassify {
	ArrayList<String> lineNumber = new ArrayList<String>();
	ArrayList<String> readability = new ArrayList<String>();
    ArrayList<String> classMethodName = new ArrayList<String>();
    ArrayList<String[]> otherContent = new ArrayList<String[]>();
    List<String[]> allClassMethods;
    
    CompareAndClassify(MethodLineNumReader lines,String fileName) throws IOException{
    	read(fileName);
    	compare(lines);
    	output(fileName);
    }
	
	public void read(String fileName) throws IOException{
		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader("results/"+fileName+".csv"));
		allClassMethods = reader.readAll();
		//System.out.println(allClassMethods.size());
		for(int i = 0; i < allClassMethods.size();i++){
			//String[] lineString = allClassMethods.get(i)[0].split(",");
			classMethodName.add(allClassMethods.get(i)[0]);
			System.out.println(classMethodName.get(i));
		}		
		//System.out.println(classMethodName.size());
	}
	
	public void compare(MethodLineNumReader lines){
		for(int i =0; i < classMethodName.size(); i++ ){
			for(int j =0 ; j <lines.classMethodName.size();j++){
				if(classMethodName.get(i).equals(lines.classMethodName.get(j))){
					lineNumber.add(lines.lineNumber.get(j));
					otherContent.add(allClassMethods.get(i));
					readability.add(allClassMethods.get(i)[allClassMethods.get(i).length-1]);
					break;
				}
			}
		}
		System.out.println(lineNumber.size());
		System.out.println(otherContent.size());
	}
	
	public void output(String fileName){
		try {
		      File csv = new File("src/xmlpaser/"+fileName+"-ReadabilityandLineNumbers.csv"); // CSV数据文件
		      File classified = new File("src/xmlpaser/"+fileName+"-ClassifiedReadabilityandLineNumbers.csv");
		      
		      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
		      
		      String lineTitle = "ClassAndMethodname";
		      ArrayList<double[]> bugsReadability = new ArrayList<double[]>();
		      for(int i = 0; i< 51; i++){
		    	  double[] bugCount = {0,0,0,0,0,0,0,0,0,0};
		    	  bugsReadability.add(bugCount);
		      }
		      double[] linesReadability = {0,0,0,0,0,0,0,0,0,0,0
		    		  //,0,0,0,0,0,0,0,0,0,0
		    		  //,0,0,0,0,0,0,0,0,0,0
		    		  //,0,0,0,0,0,0,0,0,0,0
		    		  //,0,0,0,0,0,0,0,0,0,0
		      };
		      
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
		      
		      lineTitle = lineTitle + "," + "Readability"+ "," + "Lines";
		      bw.write(lineTitle);
	 	      bw.newLine();
	 	  
	 	      for(int i = 0; i < otherContent.size(); i++){
	 	      String lineContent = "";
	 	      double[] bugdata = {0,0,0,0,0,0,0,0,0,0};
	 	      for(int j = 0; j < otherContent.get(i).length;j++) {
		    	  lineContent = lineContent + otherContent.get(i)[j]+ ",";
		    	  if(j>=1&&j<=9){
		    		  bugdata[j-1] = Double.valueOf(otherContent.get(i)[j]);
		    		  bugdata[9] = bugdata[9] + bugdata[j-1];
		    	  }
		        }
 
	 	      
		      if(Double.valueOf(readability.get(i))==0){
		    	  linesReadability[0] = linesReadability[0] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(0);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(0, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>=0&&Double.valueOf(readability.get(i))<=0.5){
		    	  linesReadability[1] = linesReadability[1] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(1);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(1, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>0.5&&Double.valueOf(readability.get(i))<=1){
		    	  linesReadability[2] = linesReadability[2] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(2);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(2, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>1&&Double.valueOf(readability.get(i))<=1.5){
		    	  linesReadability[3] = linesReadability[3] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(3);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(3, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>1.5&&Double.valueOf(readability.get(i))<=2){
		    	  linesReadability[4] = linesReadability[4] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(4);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(4, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>2&&Double.valueOf(readability.get(i))<=2.5){
		    	  linesReadability[5] = linesReadability[5] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(5);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(5, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>2.5&&Double.valueOf(readability.get(i))<=3){
		    	  linesReadability[6] = linesReadability[6] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(6);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(6, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>3&&Double.valueOf(readability.get(i))<=3.5){
		    	  linesReadability[7] = linesReadability[7] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(7);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(7, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>3.5&&Double.valueOf(readability.get(i))<=4){
		    	  linesReadability[8] = linesReadability[8] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(8);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(8, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>4&&Double.valueOf(readability.get(i))<=4.5){
		    	  linesReadability[9] = linesReadability[9] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(9);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(9, newbugCount);
		      }
		      else if(Double.valueOf(readability.get(i))>4.5&&Double.valueOf(readability.get(i))<=5){
		    	  linesReadability[10] = linesReadability[10] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(10);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(10, newbugCount);
		      }
		      
	 	     /*if(Double.valueOf(readability.get(i))==0){
		    	  linesReadability[0] = linesReadability[0] + Double.valueOf(lineNumber.get(i));
		    	  double[] newbugCount = bugsReadability.get(0);
		    	  for(int k =0; k< 10; k++){
		    		  newbugCount[k]= newbugCount[k] + bugdata[k];
		    	  }
		    	  bugsReadability.set(0, newbugCount);
		      }
	 	     
	 	     else {
	 	    	 for(int n = 0; n<50 ;n ++){
	 	    		if(Double.valueOf(readability.get(i))>0.1*(double)n && Double.valueOf(readability.get(i))<=0.1*(double)(n+1))
	 	    		{
	 	    			linesReadability[n+1] = linesReadability[n+1] + Double.valueOf(lineNumber.get(i));
	 	    			double[] newbugCount = bugsReadability.get(n+1);
	 	    			for(int k =0; k< 10; k++){
	 	    				newbugCount[k]= newbugCount[k] + bugdata[k];
	 	    			}
	 	    			bugsReadability.set(n+1, newbugCount);
	 	    			break;
	 	    		}
	 	    	 }
		    	  
		      }*/
		      
		      lineContent = lineContent + lineNumber.get(i);
		      bw.write(lineContent);
		      bw.newLine();
		      }
	 	  
	 	  
		      bw.close();
		      
		      BufferedWriter bw1 = new BufferedWriter(new FileWriter(classified, true));
		      
		      String lineTitle1 ="readability";
		      for(int i = 0; i < rows.size(); i++){
		    	   lineTitle1 = lineTitle1 + "," + rows.get(i);
		      }
		      lineTitle1 = lineTitle1 + ",All,Lines";
		      bw1.write(lineTitle1);
	 	      bw1.newLine();
		      for(int i =0; i < 11; i++){
		    	  String lineContent = String.valueOf(0.5*(double)i);
		    	  for(int j = 0; j< 10;j++){
		    		  lineContent = lineContent +","+ String.valueOf(bugsReadability.get(i)[j]);
		    	  }
		    	  lineContent = lineContent +","+ String.valueOf(linesReadability[i]);
		    	  bw1.write(lineContent);
			      bw1.newLine();
		      }
		      bw1.close();

		    } catch (FileNotFoundException e) {
		      
		      e.printStackTrace();
		    } catch (IOException e) {
		      
		      e.printStackTrace();
		    }
	}
}
