package Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import org.openqa.selenium.By;

public class ActionResult
{
    public Connection conn;
	public String comments;
    public boolean status;
    public String screenShot;
    public String propertyValue;
    public String columnName;
    public boolean stepFailure;
    private String path;
    public TestInstanceStep testInstanceStep;           
    public List<String> ColumnNames = new ArrayList<String>();
    public List<String> PropertyValues = new ArrayList<String>();
    public List<CustomStep> steps = new ArrayList<CustomStep>();
    private boolean skippedStep = false;

    public boolean getStepFailure() {
		return stepFailure;
	}
	public void setStepFailure(boolean stepFailure) {
		this.stepFailure = stepFailure;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getScreenShot() {
		return screenShot;
	}
	public void setScreenShot(String screenShot) {
		this.screenShot = screenShot;
	}
	public String getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public String getPath() {
		if(path!=null)
			return path;
		else
			return "";
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Connection getConnection() {
		return conn;
	}
	public void setConnection(Connection connection) {
		this.conn = connection;
	}
	
	public void setSkippedStep(boolean skipped){this.skippedStep = skipped;}
	public  boolean getSkippedStep(){return this.skippedStep;}

	public List<String> getColumnValues() {
		return  Collections.unmodifiableList(ColumnNames);
	}
			
	public List<String> getPropertyValues() {
		return  Collections.unmodifiableList(PropertyValues);
	}
	
	public List<CustomStep> getSteps(){
		return Collections.unmodifiableList(steps);
	}
		
	public void SaveParameterValue(Object parameter, Object Value) {
		if(!ColumnNames.contains(parameter)) {
			String value = (Value != null) ? Value.toString().trim() : "";
			ColumnNames.add(parameter.toString().trim());
			PropertyValues.add(value);
		}else {
			int index = ColumnNames.indexOf(parameter.toString());
			if(index != -1) {
				String value = (Value != null) ? Value.toString().trim() : "";
				ColumnNames.add(index, parameter.toString().trim());
				PropertyValues.add(index, value);
			}
		}
	}
	
	public void setTestInstanceStep(TestInstanceStep step){
		this.testInstanceStep = step;
	}
	
	public TestInstanceStep getTestInstanceStep(){
		return testInstanceStep;
	}
	
	public By element(int id) throws SQLException, ClassNotFoundException, Exception {
		By findBy = null;
		String locatorType  = "";
		String locator = "";		
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  					
		/*
		 * String conexion = "jdbc:sqlserver://localhost" +
		 * ":1433;databaseName=STA.Db;user=stadbadmin;password=Hexaware123;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False"
		 * ;
		 */
		Connection con2 = DriverManager.getConnection(main.Main.connectionString);  	
		Statement stmt = con2.createStatement();
		ResultSet result = stmt.executeQuery("SELECT l.Name, o.LOCATOR FROM OBJECTDICTIONARY o inner join Locator_Type l on l.id = o.LocatorType WHERE o.ID ='" + id + "'");				
		
		if (result.next()) {
			locatorType = result.getString(1);
			locator = result.getString(2);			
		}
		
		con2.close();
				
		if(locatorType != "" && locator != "") {
			switch (locatorType) {
			case "Xpath":
				findBy = By.xpath(locator);		
				break;
			case "Id":
				findBy = By.id(locator);
				break;
			case "Name":
				findBy = By.name(locator);
				break;
			case "CSS Selector":
				findBy = By.cssSelector(locator);
				break;
			case "LinkText":
				findBy = By.linkText(locator);
				break;
			case "PartialLinkText":
				findBy = By.partialLinkText(locator);
				break;
			case "TagName":
				findBy = By.tagName(locator);
				break;
			case "ClassName":
				findBy = By.className(locator);
				break;			
			default:
				findBy = null;
				break;
			}
		}	else {
			throw new Exception("Unable to find element with specified id.");
		}
		
		return findBy;
	}
			
	/*
	 * public Map<String,String> getIterationData(){
	 * stmJava.DriverHandlerSource.DT_Current_Iteration_Data;//main.Main.
	 * DT_current_iteration; }
	 */
	
	public Map<String,String> getIterationData() throws ClassNotFoundException, SQLException, Exception{		
		Map<String, String> currentIteration = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  					
		Connection con = DriverManager.getConnection(main.Main.connectionString);
		try {
			Get_DT_Iteration getDTIteration = new Get_DT_Iteration();						  	
			return getDTIteration.GetIterationRow(con, stmJava.DriverHandlerSource.datasheetId, stmJava.DriverHandlerSource.iteration);			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return currentIteration;
		}
		finally {
			if (con != null)
			{
				con.close();
			}
		}
	}
	
	public String getGDTParameter(String parameterName) throws SQLException, ClassNotFoundException, Exception {
		String value  = "";		
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  					

		Connection con2 = DriverManager.getConnection(main.Main.connectionString);  	
		Statement stmt = con2.createStatement();
		String query = "SELECT Value FROM GlobalDatasheet WHERE ProjectId = " + stmJava.DriverHandlerSource.Project_Id + " AND "
				+ "GlobalSheetId " + (stmJava.DriverHandlerSource.Global_Datasheet_Id!=null? "= " + stmJava.DriverHandlerSource.Global_Datasheet_Id.toString():"is NULL" )  + " AND "
				+ "[Key] = '" + parameterName + "'";
		ResultSet result = stmt.executeQuery(query);				
		
		if (result.next()) {
			value = result.getString(1);			
		}
		
		con2.close();
		
		return value;
	}
	
	public void saveIterationData(Map<String,String> iterationData) throws ClassNotFoundException, Exception{		
		try { 
			Map<String, String> formerIterationData = getIterationData();
			stmJava.Get_DT_Iteration.saveTable(iterationData, formerIterationData);
		}
		catch(SQLException ex) { 
			System.out.println(ex.getMessage()); 
		}
	}
	
}
