package Utilities;

public class StepResult {
	public int stepNumber;
	public String action;
	public String value;
	public String comments = "";
	public boolean status = false;	
	
	public int stepNumber() {
		return this.stepNumber;
	}
	
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	
	public String action() {
		return this.action;
	}
	
	public void SetAction(String action) {
		this.action = action;
	}
	
	public String value() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String comments () {
		return this.comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public boolean status() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	public StepResult(int stepNumber, String action, String value) {
		this.stepNumber = stepNumber;
		this.action = action;
		this.value = value;
	}
	
	public StepResult (int stepNumber, String action, String value, String comments) {
		this.stepNumber = stepNumber;		
		this.action = action;
		this.value = value;
		this.comments = comments;		
	}
}
