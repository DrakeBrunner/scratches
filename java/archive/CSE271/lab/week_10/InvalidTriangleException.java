
public class InvalidTriangleException extends IllegalArgumentException {

	public InvalidTriangleException() {
		super("Bad triangle side values.");
	}
	
	public InvalidTriangleException(int side) {
		super("Bad triangle side valude: " + side);
	}
	
	public InvalidTriangleException(int side1, int side2) {
		super("Triangle inequality: " + side1 + " " + side2);
	}
}
