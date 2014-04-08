import java.util.*;

public class mizunon_homework9 {
    public static void main(String[] args) {
        Array_Data();
        System.out.println("**********************************");
        Dice_Roll_Simulation();
        System.out.println("**********************************");
        if (Duplicate_Detection())
            System.out.println("Duplicate_Detection returned true");
        else
            System.out.println("Duplicate_Detection returned false");
        System.out.println("**********************************");
    }

    public static void Array_Data() {
        Scanner input = new Scanner(System.in);

        System.out.println("Testing readData(int[] x)");
        System.out.print("Input the size of array for part a (max: 100) : ");
        int[] test = new int[input.nextInt()];
        input.nextLine();
        
        printList(test, readData(test));

        System.out.println("Testing readData()");
        test = readData();
        printList(test, test.length);
    }

    public static int readData(int[] x) {
        Scanner input = new Scanner(System.in);

        if (x.length > 100) {
            System.out.println("Sorry, max is 100");
            return -1;
        }

        int i;
        for (i = 0; i < x.length; i++) {
            int input_int = input.nextInt();
            if (input_int == -999)
                break;
            x[i] = input_int;
        }

        return i;
    }

    public static int[] readData() {
        Scanner input = new Scanner(System.in);

        int[] list = new int[input.nextInt()];

        for (int i = 0; i < list.length; i++)
            list[i] = input.nextInt();

        return list;
    }

    public static void printList(int[] x, int n) {
        for (int i = 0; i < n; i++)
            System.out.format("x[%d] = %d\n", i, x[i]);
    }

    // **********************************************
    public static void Dice_Roll_Simulation() {
        int[] result = new int[11];
        for (int i = 0; i < 100000; i++) {
            result[(int)(Math.random() * 6 + 1) + (int)(Math.random() * 6 + 1) - 2]++;
        }

        for (int i = 0; i < result.length; i++)
            System.out.format("%2d occured %4d times\n", i + 2, result[i]);
    }

    public static boolean Duplicate_Detection() {
        Scanner input = new Scanner(System.in);

        System.out.print("Size of array: ");
        int[] array = new int[input.nextInt()];

        int length = readData(array);

        if (array.length < 2)
            return false;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j])
                    return true;
            }
        }
        return false;
    }
}
