package ir.uwindsor.cs.models;

public class Word {
	private String word;
	private int count;
	private int countClassICSE;
	private int countClassVLDB;
	private int countDocClassICSE;
	private int countDocClassVLDB;
	private double logCondProbICSE;
	private double logCondProbVLDB;
	private double chiSquare;
	
	public Word(String word, int count) {
		super();
		this.word = word;
		this.count = count;
		this.countClassICSE = 0;
		this.countClassVLDB = 0;
		this.countDocClassICSE = 0;
		this.countDocClassVLDB = 0;
		this.logCondProbICSE = 0;
		this.logCondProbVLDB = 0;
		this.chiSquare = 0;
	}
	public void addCountDocICSE(int number) {
		this.countDocClassICSE = this.countDocClassICSE + number;
	}
	public void addCountDocVLDB(int number) {
		this.countDocClassVLDB = this.countDocClassVLDB + number;
	}
	public void addCountICSE(int number) {
		this.countClassICSE = this.countClassICSE + number;
	}
	public void addCountVLDB(int number) {
		this.countClassVLDB = this.countClassVLDB + number;
	}
	public void addCount(int number) {
		this.count = this.count + number;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getLogCondProbICSE() {
		return logCondProbICSE;
	}
	public void setLogCondProbICSE(double logCondProbICSE) {
		this.logCondProbICSE = logCondProbICSE;
	}
	public double getLogCondProbVLDB() {
		return logCondProbVLDB;
	}
	public void setLogCondProbVLDB(double logCondProbVLDB) {
		this.logCondProbVLDB = logCondProbVLDB;
	}
	public int getCountDocClassICSE() {
		return countDocClassICSE;
	}
	public void setCountDocClassICSE(int countDocClassICSE) {
		this.countDocClassICSE = countDocClassICSE;
	}
	public int getCountDocClassVLDB() {
		return countDocClassVLDB;
	}
	public void setCountDocClassVLDB(int countDocClassVLDB) {
		this.countDocClassVLDB = countDocClassVLDB;
	}
	public int getCountClassICSE() {
		return countClassICSE;
	}
	public void setCountClassICSE(int countClassICSE) {
		this.countClassICSE = countClassICSE;
	}
	public int getCountClassVLDB() {
		return countClassVLDB;
	}
	public void setCountClassVLDB(int countClassVLDB) {
		this.countClassVLDB = countClassVLDB;
	}
	public double getChiSquare() {
		return chiSquare;
	}
	public void setChiSquare(double chiSquare) {
		this.chiSquare = chiSquare;
	}
	
}
