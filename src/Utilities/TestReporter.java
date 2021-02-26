package Utilities;

import java.io.File;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import Utilities.Action;

public class TestReporter {
			
	private PrintWriter fileWriter;		
	private long start;	
	private String date;
	private String time;	
	private int stepsCounter = 0;
	private int componentNumber = 0;
	private boolean fileHandlerClosed;
	private String configBrowser = "";
	private String testName = "";
	private String AUT;
	private String testDescription;

	public TestReporter(String filePath, String fileName, String testName) {		
		String fileAbsoluteName = filePath + fileName;
		this.fileHandlerClosed = false;				
		this.configBrowser = "chrome";
		this.testName = testName;
		this.AUT = "";
		this.testDescription = "";
		
		try {						
			File dir = new File(filePath);
			dir.mkdir();
			CloseReport();
			fileWriter = new PrintWriter(fileAbsoluteName, "UTF-8");
			writeShortHeader();			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void CloseReport() {
		if (fileWriter != null) {
			fileWriter.close();
			fileWriter.flush();
		}
	}

		
	public void writeIteration(int i) {
		if (fileHandlerClosed)
			return;
		fileWriter.write("<tr><td colspan=\"8\"><b>Iteration " + i + "</b></td></tr>");
		fileWriter.flush();
	}

	public void writeIterationSkipped(int i) {
		if (fileHandlerClosed)
			return;
		fileWriter.write("<tr><td colspan=\"8\"><b>Iteration " + i + " (Skipped)" + "</b></td></tr>");
		fileWriter.flush();
	}

	public void writeShortHeader() {
		if (fileHandlerClosed)
			return;
		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm:ss");

		start = System.currentTimeMillis();

		date = formateador.format(fecha);
		time = formateador2.format(fecha);
		String[] hora1 = time.split(":");
		System.out.println(hora1[0] + ":" + hora1[1] + ":" + hora1[2]);
		//Date initialTime = new Date(); 

		/* HTML header */
		fileWriter.write("<!Doctype html><html lang=\"eng\">" + "<head>" + "<meta charset=\"UTF-8\">"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">" + "<title>"+ testName + " Execution Report</title>"
				+ "<script src=\"https://code.jquery.com/jquery-3.2.1.min.js\" integrity=\"sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=\" crossorigin=\"anonymous\"></script>"
				+ "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">"
				+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.9.0/css/lightbox.css\">"
				+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.9.0/js/lightbox-plus-jquery.min.js\"></script>"
				+ "</head>");

		/* Style Tag */

		fileWriter.write("<style>" + "body,html,*{margin:0px; padding:0px;}"
				+ "body{background-color:#FFFFFA; font-family:sans-serif;}"
				+ "header { height: 20px; padding: 65px; background: #2c3e50; color: white; text-align: center; margin-bottom: 40px;box-shadow: 0 2px 10px rgba(0,0,0,0.25), 0 5px 5px rgba(0,0,0,0.22);}"
				+ ".execution{ display:inline-flex; } .execution p{ margin-left:10px; } p span{ padding:5px !important; }"
				+ ".title h2{ margin-top:-20px;}" + "main{ max-width:90%; margin:auto; }"
				+ ".table{ text-align:center;}" + ".testNumber { width: 50px; text-align: center; }"
				+ ".testDescription{text-align:center;padding-top:10px;width:200px;word-break:break-all;}"
				+ "th{text-align:center;}" + "svg{width:20px;height:20px;}" + "circle{stroke:none;fill:#73c124;}"
				+ "polyline{stroke:#fff;stroke-width:.4;stroke-linejoin:round;stroke-linecap:round;fill:none;}"
				+ ".redxsvg line{stroke:#fff; stroke-width:.4; stroke-linecap:round;}"
				+ "svg:hover,svg:focus{transform:scale(1.2);}"
				+ ".action_information{width:100%;height:auto;list-style:none;display:inline-flex; margin-bottom:30px;}"
				+ ".information_box{margin-top:10px;width:100%;height:100px;margin-left:10px;}"
				+ ".information_box h5{text-align:center;font-size:16px;color:#456;font-weight:700}"
				+ ".information_box p{margin-top:10px;text-align:center}"
				+ "@media screen and (max-width: 800px){.title h2{font-size:22px;font-weight:700}.information_box{margin-bottom:20px}}"
				+ ".ajustar{ word-wrap:break-word;border-style:none;text-align:center;overflow: hidden;overflow-y: auto; max-height: 115px; margin-bottom: 10px;}"
				+ ".information{ max-width:300px !important; text-align:center; margin-left:45px;}"
				+ ".space{width:100%; height:20; }" + "</style>");

		fileWriter.write("<script>" + "$(document).ready(function() {" + "var char = $('.description').text().length;"
				+ "console.log(char);" + "if (char > 140) {" + "$('.space').css('height', \"50px\");"
				+ " }else{ $('.space').css('height', \"20px\"); }" + " });" + "</script>");

		/* Inicio del Body */
		fileWriter.write("<body>" + "<header><div class=\"title\"><h2>Test Execution Report</h2></div>"
				+ "<div class=\"execution\"><p><span class=\"label label-success\">Execution date: " + date
				+ "</span></p>" + "<p><span class=\"label label-danger\">Execution Start Time: " + time + "</span></p>"
				+ "</div></header>");

		/* Main */
		fileWriter.write("<main>" + "<div class=\"action_information\">"
				+ "<div class=\"information_box\"><li><h5>Test Name</h5><p class=\"ajustar\" data-toggle=\"tooltip\" data-placement=\"right\" title = \""
				+ (testName.length() > 0 ? testName : "-") + "\">" + (testName.length() > 0 ? testName : "-")
				+ "</p></li></div>" // TestCase Name
				+ "<div class=\"information_box\"><div class=\"information\"><h5>Test Description</h5><p class=\"ajustar description\">"+ testDescription +"</p></div></div>"
				+ "<div class=\"information_box\"><li><h5>AUT</h5><p>"+ AUT +"</p></li></div>"
				+ "<div class=\"information_box\"><li><h5>Browser</h5><p>");

		if (configBrowser.length() > 0) {
			if (configBrowser.equals("Chrome"))
				fileWriter.write(
						"<svg xmlns=\"http://www.w3.org/2000/svg\" id=\"chrome\" viewBox=\"0 0 620 630\"><path d=\"M301.042 623.443c0-1.98 34.1-56.05 75.775-120.156 41.677-64.105 79.358-127.16 83.737-140.124 14.77-43.72 7.623-102.404-16.615-136.442-6.04-8.48-10.183-16.174-9.21-17.1.973-.925 39.667-3.283 85.988-5.24l84.22-3.556 8.136 32.395c4.666 18.57 8.138 51.973 8.138 78.275 0 164.984-121.01 297.038-286.273 312.398-18.644 1.732-33.898 1.53-33.898-.45zm-87.205-14.674C134.763 583.157 71.1 528.214 32.207 452.014c-40.49-79.33-43-184.933-6.412-269.982 5.687-13.22 13.184-26.396 16.66-29.28 4.926-4.09 20.622 23.005 71.102 122.727 56.723 112.055 67.847 130.73 89.43 150.134 29.75 26.748 68.88 42.392 106.04 42.392 15.023 0 26.577 1.812 25.674 4.027-11.356 27.852-76.775 148.517-80.452 148.394-2.71-.09-20.894-5.337-40.41-11.658zm55.188-201.947c-19.72-8.83-44.526-35.553-53.933-58.107-27.334-65.536 24.113-141.555 95.8-141.555 73.477 0 124.73 79.042 94.083 145.093-10.687 23.033-36.51 48.91-56.228 56.343-19.05 7.182-61.844 6.23-79.722-1.773zm-154.49-223.36l-46.05-71.078L87.48 92.812c23.096-23.798 74.245-57.898 107.66-71.776C260.192-5.984 355.224-7.096 420.2 18.4c64.344 25.25 126.697 77.14 156.58 130.313l11.222 19.964-38.81-3.01c-131.937-10.23-249.223-13.63-273.044-7.916-36.47 8.75-79.83 39.66-99.303 70.793l-16.26 25.996-46.05-71.078z\"/></svg>");

			else if (configBrowser.equals("FireFox"))
				fileWriter.write(
						"<svg xmlns=\"http://www.w3.org/2000/svg\" id=\"firefox\" viewBox=\"0 0 610 570\"><path d=\"M249.334 584.652C136.427 565.752 33.17 467.91 6.53 354.584c-11.728-49.9-7.165-144.055 9.73-200.756 14.883-49.945 43.78-107.235 67.907-134.63L101.075 0l-3.148 45.615-3.15 45.615 27.43-.778c24.416-.692 29.343-2.643 44.88-17.766 19.162-18.653 59.918-38.954 88.686-44.175l18.438-3.344-17.51 22.938c-19.51 25.562-33.26 51.056-37.85 70.19-3.814 15.9 1.11 18.014 50.886 21.853l37.638 2.902-3.156 14.87c-1.735 8.18-6.695 18.88-11.02 23.78-9.57 10.843-40.478 27.09-51.536 27.09-7.04 0-7.65 3.924-4.856 31.197 1.757 17.16 1.97 31.198.473 31.198-1.5 0-11.573-4.513-22.385-10.03-15.932-8.127-20.43-8.81-23.734-3.6-8.687 13.71-3.945 29.434 14.413 47.793 24.438 24.438 43.136 24.815 91.087 1.835 39.884-19.114 58.873-20.996 72.67-7.2 16.355 16.356 8.973 33.048-15.503 35.055-6.57.54-18.552 8.98-27.955 19.696-21.11 24.055-48.103 36.206-80.724 36.34-21.67.088-24.888 1.187-20.154 6.89 8.238 9.926 42.895 25.133 66.123 29.012 65.045 10.864 157.71-41.304 177.11-99.707 11.41-34.356 7.453-93.687-8.45-126.67-6.626-13.74-9.71-23.682-6.85-22.09 18.078 10.072 37.495 28.08 44.15 40.95 11.968 23.144 17.372 7.123 11.518-34.15-7.59-53.517-30.438-104.897-63.28-142.303-8.022-9.135-13.056-16.608-11.19-16.608 1.87 0 18.296 7.828 36.508 17.398C511.79 65.425 555.173 110.492 575 152.215c17.442 36.706 29.54 92.902 29.445 136.767-.29 134.376-97.967 256.866-232.525 291.6-26.4 6.816-92.98 9.026-122.584 4.07z\"/></svg>");

			else if (configBrowser.equals("Internet Explorer"))
				fileWriter.write(
						"<svg xmlns=\"http://www.w3.org/2000/svg\" id=\"ie\" viewBox=\"0 0 670 700\"> <path d=\"M54.615 683.797c-22.592-9.646-43.407-37.823-51.276-69.553-17.008-67.522 31.983-192.16 123.62-313.75 41.884-55.59 107.122-123.113 139.106-143.674 35.792-23.354 35.538-35.03-.762-21.323-45.184 17.26-103.82 60.16-164.997 121.337-62.953 62.7-66.253 62.953-47.976 3.807 21.83-70.313 68.792-123.62 139.106-157.127 53.307-25.638 86.56-32.746 150.783-32.746 47.467 0 51.275-1.015 88.336-21.577 92.652-51.022 181.75-63.968 214.497-31.222 14.215 14.215 14.977 17.515 14.977 66.253 0 35.285-2.285 53.56-7.108 58.384-6.092 5.84-6.6 4.316-3.046-9.138 7.615-30.715 4.823-70.568-6.6-90.114C620.43 4.006 571.946 4.26 490.21 44.367 439.44 68.99 427 79.652 442.74 84.727c64.73 21.324 132.506 75.646 166.775 133.523 22.592 38.076 38.584 98.744 35.792 136.313l-1.523 23.86-196.22 2.793-196.22 2.793 2.03 21.83c2.792 34.522 19.292 65.998 39.853 76.66 10.154 5.33 31.223 9.646 48.23 9.9 37.06.507 63.46-13.708 78.945-41.885l10.915-20.307h199.52l-3.553 12.185c-7.87 26.907-39.853 72.345-71.838 102.298-60.668 56.862-131.998 83.008-222.366 80.977-42.645-1.016-57.622.76-68.03 7.615-25.13 16.246-84.275 41.63-117.02 50.007-39.346 10.153-70.823 10.407-93.415.507zM167.83 662.22c50.26-10.915 118.797-46.707 88.844-46.707-5.077 0-29.7-9.646-55.084-21.576-34.776-16.246-54.83-30.462-81.737-57.37C100.307 517.023 82.283 502.3 80 503.57c-10.662 6.598-28.685 80.213-26.146 106.612 5.076 54.07 36.553 68.284 113.975 52.038zm266.026-375.18c0-22.083-23.1-59.652-43.407-70.313-23.862-12.44-70.823-12.946-95.954-1.016-25.638 12.186-53.053 62.193-42.9 78.692 1.778 2.793 41.885 5.077 92.653 5.077h89.606v-12.44z\"/></svg>");
			else if (configBrowser.equals("Safari"))
				fileWriter.write(
						"<svg xmlns=\"http://www.w3.org/2000/svg\" id=\"safari\" viewBox=\"0 0 640 680\"><path d=\"M255.752 669.534C166.447 653.974 69.93 580.85 30.368 498.772c-42.053-87.25-40.32-197.05 4.51-285.656 27.544-54.44 100.514-122.807 152.1-142.508 32.47-12.4 35.063-15.424 25.986-30.307-8.376-13.734-7.295-18.598 6.387-28.742C228.41 4.843 241.773-.35 249.048.018c1.02.428 6.782.523 7.62 1.047 3.992 2.49.907 6.01-1.492 5.367-3.393-.908-6.088-1.196-7.595-.842-3.06 1.293-7.11 2.74-10.818 4.093C223.93 14.37 215.42 20.33 215.42 27.44c0 4.313-.17 5.923 2.336 8.01 2.315 1.932 7.133 4.574 11.273 2.986 7.38-2.833 17.228-5.53 23.504-5.53s10.706-2.36 12.7-8.194c3.174-13.476-11.9-19.743-18.986-21.926 1.093-.698 9.546-2.373 9.94-2.2C258.643 1.683 265.43 4.474 265.7 5c1.115 2.152 5.16 6.72 5.544 11.953.317 4.33-.987 9.735-1.352 12.09-2.385 15.368 2.447 17.075 51.21 18.098 99.56 2.09 187.255 47.763 246.347 128.305 39.748 54.18 58.207 112.683 58.207 184.486 0 71.804-18.46 130.308-58.208 184.486-71.087 96.89-192.947 145.806-311.698 125.118zm95.562-97.033l4.292-24.99 10.67 23.42c8.59 18.852 14.38 22.296 29.693 17.66 86.025-26.05 154.58-119.78 154.7-211.518.068-49.526-.524-50.84-24.922-55.15l-24.99-4.42 23.418-10.67c18.853-8.59 22.297-14.38 17.66-29.69C515.79 191.113 422.058 122.56 330.32 122.437c-49.526-.068-50.84.528-55.152 24.924l-4.418 24.99-10.67-23.42c-8.59-18.852-14.38-22.296-29.692-17.66-86.027 26.05-154.58 119.78-154.702 211.518-.068 49.506.552 50.868 25.385 56.266 14 3.042 21.41 5.983 16.47 6.535-4.94.554-15.802 5.99-24.134 12.083-13.87 10.142-14.098 13.6-2.683 40.918 34.635 82.894 127.587 143.974 213.924 140.57 39.79-1.567 42.636-3.193 46.667-26.66zm-165.96-93.79c13.69-18.664 34.324-45.39 45.854-59.39l20.964-25.452-37.37-4.39-37.373-4.39 31.215-11.945c33.744-12.912 47.37-38.908 29.94-58.9-9.795-11.238-29.185-23.63 5.673-6.66 23.983 9.003 39.13-9.3 40.04-48.38l.815-35.02 13.987 31.37c7.692 17.252 21.557 33.348 30.81 35.768 17.4 4.55 22.48 1.736 93.564-51.83 23.33-17.58 38.238-26.31 33.125-19.395-5.115 6.915-25.675 33.57-45.692 59.235l-36.396 46.664 37.208 4.39 37.207 4.39-31.5 12.048c-17.326 6.626-32.038 20.156-34.003 27.447-1.966 7.29-5.088.615-5.63-12.218-.538-12.832-2.39-23.332-4.11-23.332-1.72 0-20.81 17.684-42.42 39.296-21.614 21.613-39.297 40.702-39.297 42.422 0 10.89 44.877-5.798 63.933-14.126 8.307-3.63 23.017-39.46 28.283-40.18 3.17-.433 3.906 24.52 8.387 30.028 7.94 18.422 32.755 24.017-4.045 12.905-23.982-9.002-45.558 12.516-46.467 51.595l-.815 35.02-13.987-31.37c-23.632-53-39.808-50.01-137.1 25.34l-29.695 23zm109.74-136.864c21.612-21.612 39.295-40.702 39.295-42.42 0-12.22-47.13.81-64.02 17.698-16.887 16.89-29.917 64.018-17.698 64.018 1.72 0 20.81-17.683 42.42-39.296z\"/></svg>");

		} else
			fileWriter.write("-");

		fileWriter.write("</p></li></div>" + "</div>");

		// space
		fileWriter.write("<div class=\"space\"></div>");

		// Tabla

		fileWriter.write("<div class=\"center-table table-responsive\">" + "<table class=\"table table-hover\">"
				+ "<th class=\"testNumber\">Test Step</th>"
				+ "<th>Action</th>" + "<th>Value</th>"
				+ "<th>Comments</th>" + "<th>Status</th>" + "<th>TimeStamp</th>");

		fileWriter.flush();
		// start2 = start.getDate().getTime();
	}

	public void writeComponentHeader(int testNum, String description, String action, int componentNumber) {

		Date fecha = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
		date = formateador.format(fecha);

		if (fileHandlerClosed)
			return;
		fileWriter.write("<tr data-toggle=\"collapse\" data-target=\".component" + componentNumber + "\">" + "<td>"
				+ testNum + "</td>" + "<td>" + description.toUpperCase() + "</td>" + "<td>" + action + "<td>"
				+ "<td></td>" + "<td></td>" + "<td></td>" + "<td>" + date + "</td>" + "</tr>");

		this.componentNumber = componentNumber;

		fileWriter.flush();

	}

	public void writeCA(String customActionName, boolean status) {
		if (fileHandlerClosed)
			return;
		
		String stepDescription = Action.CUSTOM.toString() + ":" + customActionName;
		
		fileWriter.write("<tr>" + "<td></td>" + "<td></td>" + "<td></td>" + "<td></td>" + "<td>" + stepDescription
				+ "</td>" + (status == true ? "<td>" // SVG check success
						+ "<svg viewBox=\"0 0 10 10\" xmlns=\"http://www.w3.org/2000/svg\">"
						+ "<circle cx=\"5\" cy=\"5\" r=\"4.75\"></circle>"
						+ "<polyline points=\"2.7,5 4.2,6.7 7.5,3.6\"></polyline>" + "</svg>" + "</td>"
						: "<td>" // SVG Fail Icon
								+ "<svg class=\"redxsvg\" viewBox=\"0 0 10 10\" xmlns=\"http://www.w3.org/2000/svg\">"
								+ "<circle cx=\"5\" cy=\"5\" r=\"4.5\" style=\"stroke:none; fill:#d32;\"></circle>"
								+ "<line x1=\"3.3\" y1=\"3.3\" x2=\"6.7\" y2=\"6.7\"></line>"
								+ "<line x1=\"3.3\" y1=\"6.7\" x2=\"6.7\" y2=\"3.3\"></line>" + "</svg>" + "</td>")
				+ "<td></td>" + "<td></td>" + "</tr>");

	}

	
	//public void writeRow(String stepNumber, StepResult step, String comments, boolean pass, String value, String path) throws JSONException {
	public void writeRow(StepResult step) {
		
		if (fileHandlerClosed)
			return;
		
		String value = step.value().toString();
		String row = "";
		
		if (step.action().toString().equalsIgnoreCase(Action.TypeSecure.toString())
				|| step.action().toString().equalsIgnoreCase(Action.SecureRobotKeyboard.toString())) {
			value = "**********";
		} else if (step.action().toString().equalsIgnoreCase(Action.ExecuteSOAPUITest.toString())) {
			Pattern pattern = Pattern.compile("^Insert arguments for execute SOAP UI Test");
			Matcher m = pattern.matcher(value);
			value = m.replaceAll("");

			String TestSuiteName, TestName, projectPath = "";

			try {
				JSONObject jsonTS = new JSONObject(value);

				TestSuiteName = jsonTS.getString("TestSuiteName");
				TestName = jsonTS.getString("TestCaseName");
				projectPath = jsonTS.getString("projectPath");
				value = "Project Path: " + projectPath + "<br>Test Suite: " + TestSuiteName + "<br>Test Name: " + TestName;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}			
		}	
		
		try {
			Date fecha = new Date();
			SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
			date = formateador.format(fecha);

			String statusIcon = (step.status() == true ? 
					// SVG check success
					"<td>" 
						+ "<svg viewBox=\"0 0 10 10\" xmlns=\"http://www.w3.org/2000/svg\">"
						+ "<circle cx=\"5\" cy=\"5\" r=\"4.75\"></circle>"
						+ "<polyline points=\"2.7,5 4.2,6.7 7.5,3.6\"></polyline>" + "</svg>" 
					+ "</td>"
					:
					// SVG Fail Icon
					"<td>" 
						+ "<svg class=\"redxsvg\" viewBox=\"0 0 10 10\" xmlns=\"http://www.w3.org/2000/svg\">"
						+ "<circle cx=\"5\" cy=\"5\" r=\"4.5\" style=\"stroke:none; fill:#d32;\"></circle>"
						+ "<line x1=\"3.3\" y1=\"3.3\" x2=\"6.7\" y2=\"6.7\"></line>"
						+ "<line x1=\"3.3\" y1=\"6.7\" x2=\"6.7\" y2=\"3.3\"></line>" + "</svg>"	
					+ "</td>");
			
			String stepNotExecuted = "<td><svg viewBox=\"0 0 10 10\" xmlns=\"http://www.w3.org/2000/svg\"><circle cx=\"5\" cy=\"5\" r=\"4.75\" style=\"fill:#FCDC4D;\"></circle>"
								   + "<line style=\"stroke:#445566;\" fill=\"none\" stroke-width=\"1\" stroke-miterlimit=\"4\" x1=\"5\" y1=\"2\" x2=\"5\" y2=\"6\"/>"
								   + "<line style=\"stroke:#445566;\" fill=\"none\" stroke-width=\"1\" stroke-miterlimit=\"4\" x1=\"5.5\" y1=\"8\" x2=\"4.5\" y2=\"8\"/></svg></td>";
			
			if (step.comments() == null) {
				row = "<tr>"
						+ "<td>" + step.stepNumber() + "</td>"
						+ "<td>" + step.action() + "</td>"
						+ "<td>" + value + "</td>"
						+ "<td style=\"max-width:300px\"><p class=\"ajustar\">" + (step.comments() != null ? step.comments() : "Unhandled Exception found: Verify your Custom Action logic.") + "</p></td>" 
						+ statusIcon
						+ "<td>" + date + "</td>"
					+ "</tr>\n\r";
				
			} else if (step.comments().equalsIgnoreCase("Step not executed")) {
				if (String.valueOf(step.stepNumber()).contains(".")) {
					row = "<tr class=\"component" + componentNumber + "\"><td>" + step.stepNumber() + "</td>"
							+ "<td>" + step.action() + "</td>"
							+ "<td>" + value + "</td>"
							+ "<td style=\"max-width:300px\"><p class=\"ajustar\">" + (step.comments() != null || step.comments().equals("") ? step.comments() : "-") + "</p></td>" 
							+ stepNotExecuted
							+ "<td>" + date + "</td>"
						+ "</tr>\n\r";
				} else {
					row = "<tr><td>" + step.stepNumber() + "</td>"							
							+ "<td>" + step.action() + "</td>"
							+ "<td>" + value + "</td>"
							+ "<td style=\"max-width:300px\"><p class=\"ajustar\">" + (step.comments() != null || step.comments().equals("") ? step.comments() : "-") + "</p></td>"
							+ stepNotExecuted 								
							+ "<td>" + date + "</td>"
						+ "</tr>\n\r";
				}

			} else if (String.valueOf(step.stepNumber()).contains(".")) {
				row = "<tr class=\"component" + componentNumber + "\"><td>" + step.stepNumber() + "</td>"					
						+ "<td>" + step.action()+ "</td>"
						+ "<td>" + value + "</td>"
						+ "<td style=\"max-width:300px\"><p class=\"ajustar\">" + (step.comments() != null || step.comments().equals("") ? step.comments() : "-") + "</p></td>"
						+ statusIcon
						+ "<td>" + date + "</td>"
					+ "</tr>\n\r";

				stepsCounter++;
			} else {

				row = "<tr><td>" + step.stepNumber() + "</td>"
						+ "<td>" + step.action() + "</td>"
						+ "<td>" + value + "</td>"
						+ "<td style=\"max-width:300px\"><p class=\"ajustar\" >" + (step.comments() != null || step.comments().equals("") ? step.comments() : "-") + "</p></td>"
						+ statusIcon
						+ "<td>" + date + "</td>"
					+ "</tr>\n\r";
				stepsCounter++;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				fileWriter.write(row);
				fileWriter.flush();			
			} catch (Exception Ex) {
				System.out.println(Ex.getMessage());
			}
		}
	}

	public void close() {
		long end = System.currentTimeMillis();
		long millis = end - start;

		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
		String duration = hms;

		try {
			if (fileHandlerClosed) {
				return;
			}
			fileWriter.write("</table><br/><br/>");
			fileWriter.write("<center><b>Executed Steps: </b>" + stepsCounter + "<b> Test Duration: </b>" + duration);
			fileWriter.write("<br><br><br>");
			fileWriter.write("</div></body></html>");
			fileWriter.flush();
			CloseReport();
			fileHandlerClosed = true;
		} catch (Exception Ex) {
			fileWriter.flush();
			CloseReport();
			fileHandlerClosed = true;
		}
	}

}
