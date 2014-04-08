import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //GUI_Stuff g = new GUI_Stuff();

        Polygon test = new Polygon();
        System.out.println("Before adding points");
        System.out.println(test.toString());

        test.addPoint(20, 30);
        test.addPoint(10, 5);

        System.out.println("After adding points");
        System.out.println(test.toString());
        System.out.println("Is it closed?");
        System.out.println(test.getIsClosed());

        test.setIsClosed(true);

        System.out.println("Is it closed now?");
        System.out.println(test.getIsClosed());

        System.out.println("Now testing reading");
        Polygon newOne = Polygon.readPolygon(input);
        System.out.println(newOne);
        
        System.out.println(test);
    }
}
