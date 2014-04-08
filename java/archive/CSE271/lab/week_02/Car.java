/*
 * @author Naoki Mizuno
 */
public class Car {
	// Data
	private String make;
	private int mileage;
	
	// Constructor
	// Initializes the make and the mileage to the value supplied as parameter 
	public Car(String theMake, int theMileage) {
		make = theMake;
		mileage = theMileage;
	}
	
	// Initializes the make to the value supplied as parameter and initialize mileage to zero
	public Car(String theMake) {
		make = theMake;
		mileage = 0;
	}
	
	// Methods
	// @return make of the Car
	public String getMake() {
		return make;
	}
	
	// @return mileage of the Car
	public int getMileage() {
		return mileage;
	}
	
	// adds the given distance to the current mileage of the Car.
	// The distance value can be positive or negative (for driving backwards)
	// but the mileage will always be increased
	public void drive(int distance) {
		mileage += distance > 0 ? distance : -distance;
	}
	
	// prints the make of the car followed by the phrase ": beep"
	public void honkHorn() {
		System.out.println(make + ": beep");
	}
	
	// @return single String that has the make and mileage value separated by a ": "
	public String toString() {
		return make + ": " + mileage;
	}
}
