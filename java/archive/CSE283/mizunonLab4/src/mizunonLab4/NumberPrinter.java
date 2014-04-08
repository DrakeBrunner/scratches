package mizunonLab4;

public class NumberPrinter {

    public synchronized void printTenNumbers(int startingNumber) {

        for (int i = startingNumber; i < startingNumber + 10; i++) {

            System.out.print(i + " ");

            try {
                Thread.sleep((long) (Math.random() * 10));
            }
            catch (InterruptedException e) {
            }

        }

        System.out.println();
    }

    public synchronized void printString(String s) {
        System.out.println(s);
    }
}
