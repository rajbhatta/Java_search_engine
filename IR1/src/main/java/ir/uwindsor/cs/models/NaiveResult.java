/**
 * 
 */
package ir.uwindsor.cs.models;

/**
 * @author mypc
 *
 */
public class NaiveResult 
{
   private int totalCount;
   private double precision;
   private double f1Value;
   private double error;
   private double accuracy;
public int getTotalCount() {
	return totalCount;
}
public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}
public double getPrecision() {
	return precision;
}
public void setPrecision(double precision) {
	this.precision = precision;
}
public double getF1Value() {
	return f1Value;
}
public void setF1Value(double f1Value) {
	this.f1Value = f1Value;
}
public double getError() {
	return error;
}
public void setError(double error) {
	this.error = error;
}
public double getAccuracy() {
	return accuracy;
}
public void setAccuracy(double accuracy) {
	this.accuracy = accuracy;
}
   
   
   

}
