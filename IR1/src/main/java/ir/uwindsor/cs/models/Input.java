package ir.uwindsor.cs.models;

public class Input {
	
	private String stringTItle;
	private String realClass;
	private double scoreOnICSE;
	private double scoreOnVLDB;
	private String predictClass;
	
	
	
	public Input(String stringTItle, String realClass) 
	{
		this.stringTItle = stringTItle;
		this.realClass = realClass;
	}
	public String getStringTItle() {
		return stringTItle;
	}
	public void setStringTItle(String stringTItle) {
		this.stringTItle = stringTItle;
	}
	public String getRealClass() {
		return realClass;
	}
	public void setRealClass(String realClass) {
		this.realClass = realClass;
	}
	public double getScoreOnICSE() {
		return scoreOnICSE;
	}
	public void setScoreOnICSE(double scoreOnICSE) {
		this.scoreOnICSE = scoreOnICSE;
	}
	public double getScoreOnVLDB() {
		return scoreOnVLDB;
	}
	public void setScoreOnVLDB(double scoreOnVLDB) {
		this.scoreOnVLDB = scoreOnVLDB;
	}
	public String getPredictClass() {
		return predictClass;
	}
	public void setPredictClass(String predictClass) {
		this.predictClass = predictClass;
	}
	
	
	
}
