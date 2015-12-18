package xmlpaser;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class MethodLineNumReader {
	ArrayList<String> lineNumber = new ArrayList<String>();
    ArrayList<String> classMethodName = new ArrayList<String>();
	
	MethodLineNumReader() throws IOException{
		read();
	}
	
	public void read() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("data/methodLOC.csv"));
		List<String[]> allClassMethods = reader.readAll();
		System.out.println(allClassMethods.size());
		for(int i =0; i < allClassMethods.size();i++){
			String[] lineString = allClassMethods.get(i)[0].split(",");
			lineNumber.add(lineString[1]);
			classMethodName.add(lineString[0]);
		}
		System.out.println(lineNumber.size());
		System.out.println(classMethodName.size());
	}
}
