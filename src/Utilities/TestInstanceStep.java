package Utilities;

public class TestInstanceStep extends ComponentEntity {

	
	public TestInstanceStep() {	
	}
	
	public int TestInstanceID;

	public int Iteration;
	
    public double StepNumber;

    public String Description;
    
    public String Action;

    public String Value; 

    public String LocatorType; 

    public String Locator;

    public String Status; 

    public String Duration;

    public String Comments;
    
    public String Input;
    
    public String Output;
    
	public String getInput() {
		return Input;
	}

	public void setInput(String input) {
		Input = input;
	}

	public String getOutput() {
		return Output;
	}

	public void setOutput(String output) {
		Output = output;
	}

	public int getTestInstanceID() {
		return TestInstanceID;
	}

	public void setTestInstanceID(int testInstanceID) {
		TestInstanceID = testInstanceID;
	}

	public int getIteration() {
		return Iteration;
	}

	public void setIteration(int iteration) {
		Iteration = iteration;
	}
	
	public double getStepNumber() {
		return StepNumber;
	}

	public void setStepNumber(double stepNumber) {
		StepNumber = stepNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getLocatorType() {
		return LocatorType;
	}

	public void setLocatorType(String locatorType) {
		LocatorType = locatorType;
	}

	public String getLocator() {
		return Locator;
	}

	public void setLocator(String locator) {
		Locator = locator;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}
}
