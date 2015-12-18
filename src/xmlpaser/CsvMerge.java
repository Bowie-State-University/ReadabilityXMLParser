package xmlpaser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class CsvMerge {
	private ArrayList<String> files = new ArrayList<String>();
	private ArrayList<String> content;
	private String title;
	
	public CsvMerge(ArrayList<String> namelist){
		files = namelist;
	}
	
	
	public void readfile() {
		ArrayList<ArrayList<String>> content_old = new ArrayList<ArrayList<String>>();
		ArrayList<String> titles = new ArrayList<String>();
		try {
			BufferedReader br= new BufferedReader(new FileReader(""));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void writefile() {
	    try {
	      File csv = new File("src/xmlpaser/MergedCsv.csv"); // CSV数据文件

	      BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); 
	      
	      
	      
	      ArrayList<String> rows = new ArrayList<String>();
	      ArrayList<String> lines = new ArrayList<String>();
	      
	      
	      
	      
	    	  bw.write("");
	    	  bw.newLine();
	      
    	  
    	  
	      bw.close();

	    } catch (FileNotFoundException e) {
	      
	      e.printStackTrace();
	    } catch (IOException e) {
	      
	      e.printStackTrace();
	    }
}

}