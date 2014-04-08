public class DrawShapes {

	public static void main(String [] args) {
		ShapeDisplayer disp = new ShapeDisplayer();
				
		//Add valid triangles
		disp.addShape(new Triangle(10,10,10,10,"white"));
		disp.addShape(new Rectangle(20,20,50,50,"green"));
		disp.addShape(new Pentagon(25,25,40,40,"blue"));
		disp.addShape(new Triangle(80,259,20,40,"gray"));
		disp.addShape(new Rectangle(275,275,20,20,"red"));
		disp.addShape(new Pentagon(100, 100, 200, 150, "yellow"));

        // My shape
		disp.addShape(new Ellipse(100, 50, 200, 50, "gray"));

		//Should not display
		disp.addShape(new Rectangle(100,290,20,20,"red"));
		disp.addShape(new Pentagon(100,290,20,20,"red"));

		//Should display, but should not be displayed in pink (should be one
		//of the other colors).
		//disp.addShape(new Rectangle(250,50,20,20,"pink"));
	}

}
