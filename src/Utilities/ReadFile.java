package Utilities;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadFile {
	
	public static String[][] Excel(String dataSheetName) throws BiffException, IOException {

		String FilePath = ".//DataSheets//"+dataSheetName;
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet("Sheet1");
		int totalNoOfRows = sh.getRows();
		int totalNoOfCols = sh.getColumns();
		
		String[][] inputDataArray = new String[totalNoOfCols][totalNoOfRows];
		Cell valCellContents;
		String cellContents = null;
		
		for (int row = 0; row < totalNoOfRows; row++) {

			for (int col = 0; col < totalNoOfCols; col++) {
				
				 valCellContents = sh.getCell(col,row);
				  cellContents = valCellContents.getContents();
				  inputDataArray[col][row]=cellContents;		 				 
			}		
		}
		
		return inputDataArray;
        	
	}
	
	
	@SuppressWarnings("deprecation")
	public String[][] readExcelCsv(String dataSheetName) throws IOException {

		String FilePath = "src\\hex\\TALOS\\dataSheets\\"+dataSheetName;
		String thisLine;
		FileInputStream fs = new FileInputStream(FilePath);
		DataInputStream myInput = new DataInputStream(fs);

		String[][] inputDataArray = new String[6][10];
		int x=0;
		while ((thisLine = myInput.readLine()) != null)	
		{
			int y=0;
			String[] values = thisLine.split(",");
        	for (String str : values)
        	{
        		
        		inputDataArray[x][y]=str;
				y=y+1;
        	}
        	x=x+1;
		}
		myInput.close();
		
		int k = 0;
		 for (int i = 0; i < inputDataArray.length; i++) {
		        ArrayList<Object> list = new ArrayList<Object>();
		        for (int j = 0; j < inputDataArray[i].length; j++) {
		            if (inputDataArray[i][j] != null) {
		                list.add(inputDataArray[i][j]);
		            }
		        }
		        inputDataArray[k++] = list.toArray(new String[list.size()]);
		    }
		
		return inputDataArray;
        	
	}
	
	public static String GetparameterValue(String[][] inputDataArray, String parameterName){
		
		int fila = 0;
		int columna = 0;
		
        for ( int c = 0; c < inputDataArray.length; c++) {  //
            for (int r = 0; r < inputDataArray[c].length; r++) {
            	if(inputDataArray[c][r].equalsIgnoreCase(parameterName)){
            		fila = r;
            		columna = c+1;
            	}	
                }
            } 
		      
		return inputDataArray[columna][fila];
	}
	public int GetIteration(String[][] inputDataArray){
		
		int columnas =0;
		
        for ( int c = 0; c < inputDataArray.length; c++) {
        	 for (int r = 0; r < inputDataArray[c].length; r++) {
             	columnas++;
             	}	
        }
     	columnas = columnas/2;
       
		return columnas;
	}
	
	
	public String GetLocalDataSheetValue(String[][] inputDataArray, int iteration, String parameterName){

		int columna = 0;
		
        for ( int r = 0; r < inputDataArray.length; r++) {  //
            for (int c = 0; c < inputDataArray[r].length; c++) {
            	if(inputDataArray[r][c].equalsIgnoreCase(parameterName)){
            		columna = c;
            	}	
                }
            } 
				
		return inputDataArray[iteration][columna];
	}
	

	 public static void setCellDataGDS(String Result, String[][] inputDataArray, String parameterName) throws Exception {
		 
		 int fila = 0;
			int columna = 0;
			
	        for ( int c = 0; c < inputDataArray.length; c++) {  
	            for (int r = 0; r < inputDataArray[c].length; r++) {
	            	if(inputDataArray[c][r].equalsIgnoreCase(parameterName)){
	            		fila = r;
	            		columna = c+1;
	            	}	
	                }
	            } 
		 FileInputStream fsIP= new FileInputStream(new File("src\\hex\\TALOS\\dataSheets\\DataSheet1.xls"));                   
		 @SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook(fsIP);
		 HSSFSheet worksheet = wb.getSheetAt(0); 
		 HSSFCell cell = null; 
		 cell = worksheet.getRow(fila).getCell(columna);   
		 cell.setCellValue(Result);
		 fsIP.close(); 
		 FileOutputStream output_file =new FileOutputStream(new File("src\\hex\\TALOS\\dataSheets\\DataSheet1.xls"));  
		 wb.write(output_file);
		 output_file.close();
	 }
	 
	 public static void setCellDataLDS(String dataSheetName, String Result, String[][] inputDataArray, String parameterName) throws IOException {

		 String fileToUpdate = "src\\hex\\TALOS\\dataSheets\\"+dataSheetName;
		 int row = 0;
			int col = 0;
			
	        for ( int c = 0; c < inputDataArray.length; c++) {  
	            for (int r = 0; r < inputDataArray[c].length; r++) {
	            	if(inputDataArray[c][r].equalsIgnoreCase(parameterName)){
	            		row = r;
	            		col = c+1;
	            	}	
	                }
	            } 
		 
			File inputFile = new File(fileToUpdate);

			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			csvBody.get(row)[col] = Result;
			reader.close();
			CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
			writer.writeAll(csvBody);
			writer.flush();
			writer.close();
			}
	 
	 
	 
	public static HashMap<String, HashMap<String,  String>> ReadLocalDS (String path)
	{
		try {
			FileReader reader = new FileReader(path);
			CSVReader csvReader = new CSVReader(reader);
			String[] nextRecord;
			List<String[]> all = csvReader.readAll();
			String[] keys = all.get(0);

			HashMap<String, HashMap<String, String>> dt = new HashMap<String, HashMap<String, String>>();

			FileReader reader2 = new FileReader(path);
			CSVReader csvReader2 = new CSVReader(reader2);
			// we are going to read data line by line			
			while ((nextRecord = csvReader2.readNext()) != null) {
				int cellIndex = 0;				
				HashMap<String, String> iteration = new HashMap<String, String>();
				for (String cell : nextRecord) {
					iteration.put(keys[cellIndex].toString(), cell);
					dt.put(nextRecord[0], iteration);
					cellIndex++;
				}
			}
			return dt;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String getLocal (HashMap<String, String> iteration, String parameterValue) {
		try {								
			return iteration.get(parameterValue);
		} catch(Exception ex) {
			return "";
		}
	}
}



